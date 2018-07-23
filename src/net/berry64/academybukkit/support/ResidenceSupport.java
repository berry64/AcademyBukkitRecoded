package net.berry64.academybukkit.support;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.bekvon.bukkit.residence.api.ResidenceApi;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;

import net.berry64.academybukkit.AbilityBreakBlockEvent;

public class ResidenceSupport implements Listener{
	ResidenceApi resApi = null;
	public ResidenceSupport() {
		resApi = com.bekvon.bukkit.residence.Residence.getInstance().getAPI();
	}
	
	@SuppressWarnings({ "static-access", "deprecation" })
	@EventHandler
	public void onAbilityBlockBreak(AbilityBreakBlockEvent evt) {
		ClaimedResidence res = resApi.getResidenceManager().getByLoc(evt.getBlock().getLocation());
		if(res != null) {
			if((evt.getBlock().getType() == Material.CHEST || evt.getBlock().getType() == Material.ENDER_CHEST)
					&& !res.getPermissions().playerHas(evt.getPlayer(), "container", true)) {
				evt.setCancelled(true);
			}
			if(!res.getPermissions().playerHas(evt.getPlayer(), "build", true)) {
				evt.setCancelled(true);
			}
		}
	}
}
