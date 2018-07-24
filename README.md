# AcademyBukkit

Bukkit plugin that allows server-side changes to the behaviors of AcademyCraft

Visit [AcademyCraft's Website](http://ac.li-dev.cn/) or [AcademyCraft Github Page](https://www.github.com/LambdaInnovation/AcademyCraft/) for further information about the mod.

API Usage
====
# Events
## AbilityBreakBlockEvent
Called whenever an ability breaks a block
### Player getPlayer()
returns the player that broke the block
### Block getBlock()
returns the Block that was broken with the ability
### AbilityBreakBlockEvent.SkillType getSkillType()
returns the skill that was used to break the block (currently always returns SkillType.UNKNOWN, awaiting support from mod)

This plugin is developed by berry64
