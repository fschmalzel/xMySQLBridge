package com.gmail.xlifehd.xmysqlbridge.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class PlayerItemHeldListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerItemHeld ( PlayerItemHeldEvent e ) {
		
		Main plugin = Main.getPlugin();
		
		//DEBUG
		plugin.getLogger().info(Main.getPrefix("info") + "009 ItemHeldEvent");
		
		if ( plugin.getxUtils().isFrozen( e.getPlayer().getUniqueId() ) ) {
			e.setCancelled(true);
		}
		
	}
	
}
