package core.game.interaction

import core.game.node.item.Item
import core.game.system.SystemLogger
import core.plugin.Plugin

class ItemOnBankBooth : UseWithHandler(0) {

    override fun handle(event: NodeUsageEvent?): Boolean {
        event ?: return false
        val player = event.player
        player.pulseManager.run(object : MovementPulse(player, event.usedWith, DestinationFlag.OBJECT) {
            override fun pulse(): Boolean {
                return handleNote(event)
            }
        }, "movement")
        return true
    }

    fun handleNote(event: NodeUsageEvent?): Boolean {
        event ?: return false
        val item = event.usedItem
        val player = event.player
        if (item.noteChange != item.id) {
            if (item.definition.isUnnoted) {
                val amount = player.inventory.getAmount(item.id)
                player.inventory.remove(Item(item.id, amount))
                player.inventory.add(Item(item.noteChange, amount))
            } else {
                var amount = item.amount
                if (amount > player.inventory.freeSlots()) {
                    SystemLogger.log("Adjusting amount of item.")
                    amount = player.inventory.freeSlots()
                }
                SystemLogger.log("Removing $amount of item.")
                player.inventory.remove(Item(item.id, amount))
                player.inventory.add(Item(item.noteChange, amount))
            }
            return true
        } else {
            player.sendMessage("This item can't be noted.")
            return true
        }
    }

    override fun newInstance(arg: Any?): Plugin<Any> {
        return this
    }

    override fun nodeAllowed(nodeId: Int): Boolean {
        return true
    }
}