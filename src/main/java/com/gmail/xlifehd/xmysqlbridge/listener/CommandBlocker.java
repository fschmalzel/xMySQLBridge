package com.gmail.xlifehd.xmysqlbridge.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class CommandBlocker implements Listener{
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommand(PlayerCommandPreprocessEvent e) {
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
		
	}
	
}
