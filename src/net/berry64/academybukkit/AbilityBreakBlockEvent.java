/**
 * 
 */
package net.berry64.academybukkit;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author berry64
 *
 */
public class AbilityBreakBlockEvent extends Event implements Cancellable{
	public enum SkillType{
		UNKNOWN,
		ELECTROMASTER_RAILGUN
		//TODO Waiting for mod support
	}
	
	private Player player;
	private Block block;
	private SkillType skilltype = SkillType.UNKNOWN;
	
	public AbilityBreakBlockEvent(Player p, SkillType skill, Block b) {
		player = p;
		block = b;
		skilltype = skill;
	}
	public AbilityBreakBlockEvent(Player p, Block b) {
		this(p, SkillType.UNKNOWN, b);
	}
	
	public Player getPlayer() {
		return player;
	}
	public Block getBlock() {
		return block;
	}
	public SkillType getSkillType() {
		return skilltype;
	}
	
	private boolean cancer = false;
	@Override
	public boolean isCancelled() {
		return cancer;
	}
	@Override
	public void setCancelled(boolean isCancelled) {
		cancer = isCancelled;
	}

	private static HandlerList HANDLERS = new HandlerList();
	
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}
	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
}
