package com.gmail.xlifehd.xmysqlbridge.listeners.blocker;

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
		plugin.getLogger().info("PlayerSwapHandItemsEvent");
		
		if ( plugin.getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
}