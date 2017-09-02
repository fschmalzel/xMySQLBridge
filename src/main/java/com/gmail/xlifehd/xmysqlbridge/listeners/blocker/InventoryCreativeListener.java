package com.gmail.xlifehd.xmysqlbridge.listeners.blocker;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCreativeEvent;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class InventoryCreativeListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInventoryCreative ( InventoryCreativeEvent e ) {
		
		Main plugin = Main.getPlugin();
		
		//DEBUG
		plugin.getLogger().info("InventoryCreativeEvent");
		
		if ( plugin.getxUtils().isFrozen(e.getWhoClicked().getUniqueId())) {
			e.setCancelled(true);
		}
		
	}
	
}
