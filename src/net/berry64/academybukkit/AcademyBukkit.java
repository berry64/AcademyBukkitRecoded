package net.berry64.academybukkit;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.berry64.academybukkit.support.*;

/**
 * @author berry64
 */
public final class AcademyBukkit extends JavaPlugin {
    
	private static CoreProtectHook coreProtectHook = null;
	
    public static void init(){

        Class<?> adapter = null;
		try {
			adapter = Class.forName("cn.academy.support.bukkit.BukkitAdapter");
		} catch (ClassNotFoundException e1) {
			instance.getLogger().log(java.util.logging.Level.SEVERE, "Cannot Load Plugin because AcademyCraft is not loaded");
			Bukkit.getServer().getPluginManager().disablePlugin(instance);
		}
        try {
        	if (adapter != null)
			adapter.getMethod("init", Class.class).
			    invoke(null, AcademyBukkit.class);
	        dummyUuid = (UUID) adapter.getField("dummy").get(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    static UUID dummyUuid = null;
    private static World world = null;
    private static Player player = null;
    
    // Assumes world is valid
    public static void setWorld(String world_) {
        world = Bukkit.getWorld(world_);
    }
    
    // Assumes world is set
    public static void setPlayer(UUID uuid_) {
    	if(uuid_ != dummyUuid) {
            player = Bukkit.getServer().getPlayer(uuid_);
    	}
    }
    
    public static boolean checkBlockDestroy(int x, int y, int z) {
        if (world == null)
            return true;
        Block block = world.getBlockAt(x, y, z);
        if(player != null) {
        	AbilityBreakBlockEvent evt = new AbilityBreakBlockEvent(player, block);
        	Bukkit.getPluginManager().callEvent(evt);
        	boolean result = evt.isCancelled();
        	if(coreProtectHook != null && !result) {
        		coreProtectHook.logPlayerBlockBreak(block, player);
        	}
        	return result;
        } else {
        	DummyAbilityBreakBlockEvent evt = new DummyAbilityBreakBlockEvent(block);
        	Bukkit.getPluginManager().callEvent(evt);
        	return evt.isCancelled();
        }
    }
    
    private static AcademyBukkit instance;
    
    @Override
    public void onEnable() {
    	instance = this;
    	init();
    	
    	org.bukkit.plugin.PluginManager manager = getServer().getPluginManager();
    	if(manager.getPlugin("Residence") != null) {
    		manager.registerEvents(new ResidenceSupport(), this);
    		getLogger().info("Residence Support Activated");
    	}
    	
    	if(manager.getPlugin("PlotSquared") != null) {
    		manager.registerEvents(new PlotSquaredSupport(), this);
    		getLogger().info("PlotSquared Support Activated");
    	}
    	
    	if(manager.getPlugin("CoreProtect") != null) {
    		coreProtectHook = new CoreProtectHook();
    		getLogger().info("Successfully Hooked into CoreProtect");
    	}
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
   		if(cmd.getName().equalsIgnoreCase("academybukkit")) {
   			sender.sendMessage("This Server is running AcademyBukkit v1.0.1 by berry64");
   			try {
				sender.sendMessage("The AcademyCraft mod Version is: "+((String)Class.forName("cn.academy.core.AcademyCraft").getField("VERSION").get(null)));
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException
					| ClassNotFoundException e) {
				sender.sendMessage("The AcademyCraft mod is not found! see log for details");
				e.printStackTrace();
			}
   			return true;
   		}
    	return false;
    }
}
