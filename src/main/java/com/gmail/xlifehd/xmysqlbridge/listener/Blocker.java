package com.gmail.xlifehd.xmysqlbridge.listener;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerBucketEvent;
import org.bukkit.event.player.PlayerChangedMainHandEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class Blocker implements Listener{
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommand(PlayerCommandPreprocessEvent e) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "000 CommandPreProcessEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerMove( PlayerMoveEvent e ) {
		//Main.getPlugin().getLogger().info(Main.getPrefix("info") + "001 MoveEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			//DEBUG
			//e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDropItem ( PlayerDropItemEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "002 DropItemEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerEditBook ( PlayerEditBookEvent e) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "003 EditBookEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityPickupItem ( EntityPickupItemEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "004 PickupItemEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(((OfflinePlayer) e).getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerShearEntity ( PlayerShearEntityEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "006 ShearEntityEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerPortal ( PlayerPortalEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "007 PortalEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerItemConsume ( PlayerItemConsumeEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "010 ItemConsumeEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteract ( PlayerInteractEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "011 InteractEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteractEntity ( PlayerInteractEntityEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "012 InteractEntityEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteractAtEntity ( PlayerInteractAtEntityEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "013 InteractAtEntityEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerBucket ( PlayerBucketEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "015 BucketEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerBedEnter ( PlayerBedEnterEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "016 BedEnterEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerArmorStandManipulate ( PlayerArmorStandManipulateEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "017 ArmorStandManipulateEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerAnimation ( PlayerAnimationEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "018 AnimationEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInventoryInteract ( InventoryInteractEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "020 InventoryInteractEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getWhoClicked().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInventoryDrag ( InventoryDragEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "021 InventoryDragEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getWhoClicked().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInventoryClick ( InventoryClickEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "022 InventoryClickEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getWhoClicked().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInventoryCreative ( InventoryCreativeEvent e ) {
		Main.getPlugin().getLogger().info(Main.getPrefix("info") + "023 InventoryCreativeEvent");
		if ( Main.getPlugin().getxUtils().isFrozen(e.getWhoClicked().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
}
