package net.berry64.academybukkit.support;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotArea;

import net.berry64.academybukkit.AbilityBreakBlockEvent;

public class PlotSquaredSupport implements Listener {
	PlotAPI plotapi;
	public PlotSquaredSupport() {
		plotapi = new PlotAPI();
	}
	
	@EventHandler
	public void onAbilitBreakBlock(AbilityBreakBlockEvent evt) {
		for(PlotArea pa : plotapi.getPlotAreas(evt.getPlayer().getWorld())) {
			if(pa.contains(evt.getBlock().getX(), evt.getBlock().getZ())) {
				Plot plot = plotapi.getPlot(evt.getBlock().getLocation());
				if(plot != null) {
					if(!plot.isAdded(evt.getPlayer().getUniqueId())) {
						evt.setCancelled(true);
					}
				} else {
					evt.setCancelled(true);
				}
				break;
			}
		}
	}
}
