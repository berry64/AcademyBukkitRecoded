package net.berry64.academybukkit.support;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;

public class CoreProtectHook {
	CoreProtectAPI api = null;
	
	public CoreProtectHook() {
		api = ((CoreProtect)Bukkit.getServer().getPluginManager().getPlugin("CoreProtect")).getAPI();
	}
	
	@SuppressWarnings("deprecation")
	public boolean logPlayerBlockBreak(Block broken, Player p) {
		return api.logRemoval(p.getName()+"#Ability", broken.getLocation(), broken.getType(), broken.getData());
	}
}
