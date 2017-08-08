package com.gmai.xlifehd.xmysqlbridge.listener;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.gmail.xlifehd.xmysqlbridge.mysql.SaveHandler;

public class OnQuit implements Listener {
	
	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event) {
		OfflinePlayer[] players = {(OfflinePlayer) event.getPlayer()};
		
		//TODO Check for some kind of flag set locally on the server
		SaveHandler saveHandler = new SaveHandler(players);
		saveHandler.savePlayerData();
	}

}
