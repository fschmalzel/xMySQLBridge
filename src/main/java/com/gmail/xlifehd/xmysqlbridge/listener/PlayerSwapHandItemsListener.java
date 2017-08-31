package com.gmail.xlifehd.xmysqlbridge.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class PlayerSwapHandItemsListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerSwapHandItems ( PlayerSwapHandItemsEvent e ) {
		
		Main plugin = Main.getPlugin();
		
		//DEBUG
		plugin.getLogger().info(Main.getPrefix("info") + "005 SwapHandItemsEvent");
		
		if ( plugin.getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
}
