package com.gmail.xlifehd.xmysqlbridge.listeners.blocker;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class PlayerCommandPreprocessListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerCommandPreProcess ( PlayerCommandPreprocessEvent e) {
		
		//DEBUG
		//Main.getPlugin().getLogger().info("CommandPreProcessEvent");
		
		if ( Main.getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
		
	}

}
