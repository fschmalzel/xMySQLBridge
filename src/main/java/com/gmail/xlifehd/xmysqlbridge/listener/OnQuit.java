package com.gmail.xlifehd.xmysqlbridge.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.gmail.xlifehd.xmysqlbridge.XUtils;
import com.gmail.xlifehd.xmysqlbridge.mysql.SaveHandler;

public class OnQuit implements Listener {
	
	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		Player[] players = {player};
		
		if ( !XUtils.isFrozen(player) ) {
			SaveHandler saveHandler = new SaveHandler(players);
			saveHandler.savePlayerData();
		}
		
	}

}
