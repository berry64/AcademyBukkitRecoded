package net.berry64.academybukkit;

import org.bukkit.block.Block;

public class DummyAbilityBreakBlockEvent extends AbilityBreakBlockEvent {
	public DummyAbilityBreakBlockEvent(SkillType skill, Block b) {
		super(null, skill, b);
	}
	public DummyAbilityBreakBlockEvent(Block b) {
		this(SkillType.UNKNOWN, b);
	}
	
	public AbilityBreakBlockEvent getAbilityBreakBlockEvent() {
		return (AbilityBreakBlockEvent)this;
	}
	
}
