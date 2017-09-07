package com.gmail.xlifehd.xmysqlbridge.listeners.blocker;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class PlayerInteractListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteract ( PlayerInteractEvent e ) {
		
		//DEBUG
		//Main.getPlugin().getLogger().info("PlayerInteractEvent");
		
		if ( Main.getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
		
	}
	
}
