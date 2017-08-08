package com.gmai.xlifehd.xmysqlbridge.listener;

import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.gmail.xlifehd.xmysqlbridge.Main;
import com.gmail.xlifehd.xmysqlbridge.MySQLLoadHandler;
import com.gmail.xlifehd.xmysqlbridge.MySQLSaveHandler;

public class OnJoin implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		//TODO Freeze him for about 1 sec, then load the data and unfreeze him if its finished and set some kind of flag somewhere that data can be saved if he leaves that flag should be made sure of being false
		FileConfiguration config = Main.getPlugin().getConfig();
		
		MySQLLoadHandler loadHandler = new MySQLLoadHandler(player);
		
		if ( config.getBoolean("table.health.enabled") ) {
			player.setHealth(loadHandler.getHealth());
		}
		
	}
	
}
