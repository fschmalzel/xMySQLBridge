package com.gmai.xlifehd.xmysqlbridge.listener;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.gmail.xlifehd.xmysqlbridge.MySQLSaveHandler;

public class OnQuit implements Listener {
	
	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event) {
		OfflinePlayer player = (OfflinePlayer) event.getPlayer();
		OfflinePlayer[] players = {player};
		
		//TODO Check for some kind of flag set locally on the server
		MySQLSaveHandler saveHandler = new MySQLSaveHandler(players);
		saveHandler.savePlayerData();
	}

}
