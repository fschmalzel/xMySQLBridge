package com.gmail.xlifehd.xmysqlbridge.listeners.blocker;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class EntityPickupItemListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityPickupItem ( EntityPickupItemEvent e ) {
		
		Main plugin = Main.getPlugin();
		
		//DEBUG
		//plugin.getLogger().info("EntityPickupItemEvent");
		
		if ( e.getEntityType() == EntityType.PLAYER ) {
			if ( plugin.getxUtils().isFrozen( e.getEntity().getUniqueId() ) ) {
				e.setCancelled(true);
			}
		}
		
	}
	
}
