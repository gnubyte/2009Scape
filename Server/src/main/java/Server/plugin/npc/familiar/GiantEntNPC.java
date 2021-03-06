package plugin.npc.familiar;

import plugin.skill.summoning.familiar.Familiar;
import plugin.skill.summoning.familiar.FamiliarSpecial;
import core.game.node.entity.combat.equipment.WeaponInterface;
import core.plugin.InitializablePlugin;
import core.game.node.entity.player.Player;

/**
 * Represents the Giant Ent familiar.
 * @author Aero
 */
@InitializablePlugin
public class GiantEntNPC extends Familiar {

	/**
	 * Constructs a new {@code GiantEntNPC} {@code Object}.
	 */
	public GiantEntNPC() {
		this(null, 6800);
	}

	/**
	 * Constructs a new {@code GiantEntNPC} {@code Object}.
	 * @param owner The owner.
	 * @param id The id.
	 */
	public GiantEntNPC(Player owner, int id) {
		super(owner, id, 4900, 12013, 6, WeaponInterface.STYLE_CONTROLLED);
	}

	@Override
	public Familiar construct(Player owner, int id) {
		return new GiantEntNPC(owner, id);
	}

	@Override
	protected boolean specialMove(FamiliarSpecial special) {
		return false;
	}

	@Override
	public int[] getIds() {
		return new int[] { 6800, 6801 };
	}

}
