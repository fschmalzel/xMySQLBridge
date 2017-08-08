package com.gmai.xlifehd.xmysqlbridge.listener;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.gmail.xlifehd.xmysqlbridge.Main;
import com.gmail.xlifehd.xmysqlbridge.mysql.LoadHandler;

public class OnJoin implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		//TODO Freeze him for about 1 sec, then load the data and unfreeze him if its finished and set some kind of flag somewhere that data can be saved if he leaves that flag should be made sure of being false
		FileConfiguration config = Main.getPlugin().getConfig();
		
		LoadHandler loadHandler = new LoadHandler(player);
		
		if ( config.getBoolean("table.health.enabled") ) {
			player.setHealth(loadHandler.getHealth());
		}
		
		if ( config.getBoolean("table.hunger.enabled") ) {
			player.setFoodLevel(loadHandler.getHunger());
		}
		
		if ( config.getBoolean("table.effects.enabled") ) {
			//TODO SET EFFECTS
			//loadHandler.getEffects();
		}
		
		if ( config.getBoolean("table.location.enabled") ) {
			//TODO SET LOCATION
			//Location loc = loadHandler.getLocation();
			//player.setLocation(loadHandler.getLocation());
		}
		
		if ( config.getBoolean("table.experience.enabled") ) {
			player.setExp(loadHandler.getExperience());
		}
		
		if ( config.getBoolean("table.money.enabled") ) {
			//TODO SET MONEY
		}
		
		if ( config.getBoolean("table.inventory.enabled") ) {
			//TODO SET INVENTORY
		}
		
		if ( config.getBoolean("table.enderchest.enabled") ) {
			//TODO SET ENDERCHEST INVENTORY
		}
		
		if ( config.getBoolean("table.achievements.enabled") ) {
			//TODO ACHIEVEMENTS
		}
		
	}
	
}
