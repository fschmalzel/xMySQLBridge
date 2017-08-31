package com.gmail.xlifehd.xmysqlbridge.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class PlayerCommandPreprocessListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerCommandPreProcess ( PlayerCommandPreprocessEvent e) {
		
		Main plugin = Main.getPlugin();
		
		//DEBUG
		plugin.getLogger().info(Main.getPrefix("info") + "CommandPreProcessEvent");
		
		if ( plugin.getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
		
	}

}
