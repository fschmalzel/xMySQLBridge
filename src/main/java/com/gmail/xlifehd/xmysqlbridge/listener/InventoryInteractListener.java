package com.gmail.xlifehd.xmysqlbridge.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryInteractEvent;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class InventoryInteractListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInventoryInteract ( InventoryInteractEvent e ) {
		
		Main plugin = Main.getPlugin();
		
		//DEBUG
		plugin.getLogger().info(Main.getPrefix("info") + "InventoryInteractEvent");
		
		if ( plugin.getxUtils().isFrozen(e.getWhoClicked().getUniqueId())) {
			e.setCancelled(true);
		}
		
	}
	
}
