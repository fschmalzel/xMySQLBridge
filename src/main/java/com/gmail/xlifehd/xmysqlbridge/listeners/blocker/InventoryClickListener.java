package com.gmail.xlifehd.xmysqlbridge.listeners.blocker;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class InventoryClickListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInventoryClick ( InventoryClickEvent e ) {
		
		//DEBUG
		//Main.getPlugin();.getLogger().info("InventoryClickEvent");
		
		if ( Main.getxUtils().isFrozen(e.getWhoClicked().getUniqueId())) {
			e.setCancelled(true);
		}
		
	}
	
}
