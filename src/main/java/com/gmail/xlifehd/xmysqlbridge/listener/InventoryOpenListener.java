package com.gmail.xlifehd.xmysqlbridge.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class InventoryOpenListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInventoryOpen ( InventoryOpenEvent e ) {
		
		Main plugin = Main.getPlugin();
		
		//DEBUG
		plugin.getLogger().info(Main.getPrefix("info") + "019 InventoryOpenEvent");
		
		if ( plugin.getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
		
	}
	
}