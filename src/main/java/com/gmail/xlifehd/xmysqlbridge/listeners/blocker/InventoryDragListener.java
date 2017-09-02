package com.gmail.xlifehd.xmysqlbridge.listeners.blocker;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class InventoryDragListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInventoryDrag ( InventoryDragEvent e ) {
		
		Main plugin = Main.getPlugin();
		
		//DEBUG
		plugin.getLogger().info("InventoryDragEvent");
		
		if ( plugin.getxUtils().isFrozen(e.getWhoClicked().getUniqueId())) {
			e.setCancelled(true);
		}
		
	}
	
}
