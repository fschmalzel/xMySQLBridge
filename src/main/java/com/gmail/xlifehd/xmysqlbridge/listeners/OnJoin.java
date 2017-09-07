package com.gmail.xlifehd.xmysqlbridge.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.gmail.xlifehd.xmysqlbridge.mysql.SetHandler;

public class OnJoin implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		SetHandler setHandler = new SetHandler(player);
		setHandler.init();
	}
	
}
