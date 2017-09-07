package com.gmail.xlifehd.xmysqlbridge.listeners.blocker;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class PlayerItemHeldListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerItemHeld ( PlayerItemHeldEvent e ) {
		
		//DEBUG
		//Main.getPlugin().getLogger().info("PlayerItemHeldEvent");
		
		if ( Main.getxUtils().isFrozen( e.getPlayer().getUniqueId() ) ) {
			e.setCancelled(true);
		}
		
	}
	
}
