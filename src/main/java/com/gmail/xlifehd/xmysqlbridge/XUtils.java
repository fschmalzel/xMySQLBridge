package com.gmail.xlifehd.xmysqlbridge;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class XUtils {
	
	List<UUID> frozenPlayers;
	
	public XUtils() {
		frozenPlayers = new ArrayList<UUID>();
	}
	
	public void freezePlayer( Player player ) {
		UUID uuid = player.getUniqueId();
		if ( !frozenPlayers.contains(uuid) ) {
			frozenPlayers.add(uuid);
			setFreeze(player, true);
		}
	}
	
	public void unfreezePlayer( Player player ) {
		UUID uuid = player.getUniqueId();
		if ( frozenPlayers.contains(uuid) ) {
			frozenPlayers.remove(uuid);
			setFreeze(player, false);
		}
	}
	
	public boolean isFrozen( UUID uuid ) {
		return frozenPlayers.contains(uuid);
	}
	
	private void setFreeze(Player player, boolean frozen) {
		player.setAllowFlight(frozen);
		player.setFlying(frozen);
		player.setCollidable(!frozen);
		player.setInvulnerable(frozen);
		player.setGravity(!frozen);
		if ( frozen ) {
			player.setGameMode(GameMode.SPECTATOR);
			player.setWalkSpeed(0);
			player.setFlySpeed(0);
		} else {
			player.setGameMode(GameMode.SURVIVAL);
			player.setWalkSpeed(1);
			player.setFlySpeed(1);
		}
	}
	
	
}
