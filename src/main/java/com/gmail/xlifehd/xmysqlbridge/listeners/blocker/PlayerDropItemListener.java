package com.gmail.xlifehd.xmysqlbridge.listeners.blocker;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class PlayerDropItemListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDropItem ( PlayerDropItemEvent e ) {
		
		//DEBUG
		//Main.getPlugin().getLogger().info("PlayerDropItemEvent");
		
		if ( Main.getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
		
	}
	
}
