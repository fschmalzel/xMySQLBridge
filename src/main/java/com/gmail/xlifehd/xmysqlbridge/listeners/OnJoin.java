package com.gmail.xlifehd.xmysqlbridge.listeners;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.Location;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitTask;

import com.gmail.xlifehd.xmysqlbridge.BukkitSerialization;
import com.gmail.xlifehd.xmysqlbridge.Main;
import com.gmail.xlifehd.xmysqlbridge.mysql.LoadHandler;

public class OnJoin implements Listener {
	
	
	//TODO Maybe change to onAsyncPlayerPreLoginEvent
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		Main.getPlugin().getxUtils().freezePlayer( player );
		
		final Runnable safeUnfreeze = new Runnable() {
			
			public void run() {
				Main.getPlugin().getxUtils().unfreezePlayer(player);
			}
			
		};
		
		final BukkitTask task = Main.getPlugin().getServer().getScheduler().runTaskLater(Main.getPlugin(), safeUnfreeze, 20*30); //30 secs
		
		Runnable r = new Runnable() {
			
			public void run() {
				FileConfiguration config = Main.getPlugin().getConfig();
				LoadHandler loadHandler = new LoadHandler(player.getUniqueId());
				
				if ( config.getBoolean("table.health.enabled") ) {
					Double health = loadHandler.getHealth();
					if ( health != null ) {
						player.setHealth(health);
					}
				}
				
				if ( config.getBoolean("table.hunger.enabled") ) {
					Number[] data = loadHandler.getHunger();
					if ( data != null ) {
						player.setFoodLevel(data[0].intValue());
						player.setSaturation(data[1].floatValue());
					}
				}
				
				if ( config.getBoolean("table.effects.enabled") ) {
					Collection<PotionEffect> newEffects = loadHandler.getEffects();
					Collection<PotionEffect> oldEffects = player.getActivePotionEffects();
					
					//Remove old potion effects
					for ( Iterator<PotionEffect> i = oldEffects.iterator(); i.hasNext(); ) {
						PotionEffect effect = i.next();
						player.removePotionEffect(effect.getType());
					}
					
					//Set new potion effects
					player.addPotionEffects(newEffects);
				}
				
				if ( config.getBoolean("table.location.enabled") ) {
					Location loc = loadHandler.getLocation();
					if ( loc != null ) {
						player.teleport(loc);
					}
				}
				
				if ( config.getBoolean("table.experience.enabled") ) {
					Number[] data = loadHandler.getExperience();
					if ( data != null ) {
						player.setTotalExperience(data[0].intValue());
						player.setLevel(data[1].intValue());
						player.setExp(data[2].floatValue());
					}
				}
				
				if ( config.getBoolean("table.inventory.enabled") ) {
					ItemStack[][] contents = loadHandler.getInventory();
					
					if ( contents != null ) {
						PlayerInventory playerInventory = player.getInventory();
						playerInventory.setStorageContents(contents[0]);
						playerInventory.setArmorContents(contents[1]);
						playerInventory.setItemInOffHand(contents[2][0]);
					}
				}
				
				if ( config.getBoolean("table.enderchest.enabled") ) {
					ItemStack[] enderchest = loadHandler.getEnderchest();
					if ( enderchest != null ) {
						player.getEnderChest().setStorageContents(enderchest);
					}
				}
				
				if ( config.getBoolean("table.advancements.enabled") ) {
					//TODO Set advancements
					HashMap<Advancement, AdvancementProgress> hashMap = loadHandler.getAdvancements();
					
					
					
					
				}
				task.cancel();
				Main.getPlugin().getServer().getScheduler().runTask(Main.getPlugin(), safeUnfreeze);
				
			}//Run end
			
		};//Runnable end
		

		
		Main.getPlugin().getServer().getScheduler().runTaskLaterAsynchronously(Main.getPlugin(), r, Main.getPlugin().getConfig().getLong("loadDelayinTicks"));
		
	}
	
}
