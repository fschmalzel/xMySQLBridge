package com.gmai.xlifehd.xmysqlbridge.listener;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;

import com.gmail.xlifehd.xmysqlbridge.Main;
import com.gmail.xlifehd.xmysqlbridge.mysql.LoadHandler;

public class OnJoin implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		//TODO Freeze him for about 1 sec, then load the data and unfreeze him if its finished and set some kind of flag somewhere that data can be saved if he leaves that flag should be made sure of being false
		FileConfiguration config = Main.getPlugin().getConfig();
		
		LoadHandler loadHandler = new LoadHandler(player.getUniqueId());
		
		if ( config.getBoolean("table.health.enabled") ) {
			Double health = loadHandler.getHealth();
			if ( health != null ) {
				player.setHealth(health);
			}
		}
		
		if ( config.getBoolean("table.hunger.enabled") ) {
			Integer hunger = loadHandler.getHunger();
			if ( hunger != null ) {
				player.setFoodLevel(hunger);
			}
		}
		
		if ( config.getBoolean("table.effects.enabled") ) {
			//TODO SET EFFECTS
			//loadHandler.getEffects();
		}
		
		if ( config.getBoolean("table.location.enabled") ) {
			Location loc = loadHandler.getLocation();
			if ( loc != null ) {
				player.teleport(loc);
			}
		}
		
		if ( config.getBoolean("table.experience.enabled") ) {
			Float exp = loadHandler.getExperience();
			if ( exp != null ) {
				player.setExp(exp);
			}
		}
		
		if ( config.getBoolean("table.money.enabled") ) {
			Double money = loadHandler.getMoney();
			if ( money != null ) {
				double difference = money - Main.getEconomy().getBalance(player);
				if ( difference > 0 ) {
					Main.getEconomy().depositPlayer(player, difference);
				} else {
					Main.getEconomy().withdrawPlayer(player, -difference);
				}
			}
		}
		
		if ( config.getBoolean("table.inventory.enabled") ) {
			PlayerInventory playerInventory = loadHandler.getInventory();
			if ( playerInventory != null ) {
				player.getInventory().setStorageContents(playerInventory.getStorageContents());
				player.getInventory().setArmorContents(playerInventory.getArmorContents());
			}
		}
		
		if ( config.getBoolean("table.enderchest.enabled") ) {
			Inventory enderchest = loadHandler.getEnderchest();
			if ( enderchest != null ) {
				player.getEnderChest().setStorageContents(enderchest.getStorageContents());
			}
		}
		
		if ( config.getBoolean("table.achievements.enabled") ) {
			//TODO ACHIEVEMENTS
		}
		
	}
	
}
