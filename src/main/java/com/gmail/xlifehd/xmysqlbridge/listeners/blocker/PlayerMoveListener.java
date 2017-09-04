package com.gmail.xlifehd.xmysqlbridge.listeners.blocker;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class PlayerMoveListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerMove( PlayerMoveEvent e ) {
		
		Main plugin = Main.getPlugin();
		
		//DEBUG
		//plugin.getLogger().info("PlayerMoveEvent");
		
		if ( plugin.getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
		
	}
	
}
