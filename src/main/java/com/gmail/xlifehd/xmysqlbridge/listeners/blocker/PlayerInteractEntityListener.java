package com.gmail.xlifehd.xmysqlbridge.listeners.blocker;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class PlayerInteractEntityListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteractEntity ( PlayerInteractEntityEvent e ) {
		
		Main plugin = Main.getPlugin();
		
		//DEBUG
		//plugin.getLogger().info("InteractEntityEvent");
		
		if ( plugin.getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
		
	}
	
}
