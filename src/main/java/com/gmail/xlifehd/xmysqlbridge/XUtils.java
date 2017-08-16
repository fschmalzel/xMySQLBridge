package com.gmail.xlifehd.xmysqlbridge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class XUtils {
	
	List<UUID> frozenPlayers;
	HashMap<UUID, GameMode> uuidToGameMode;
	
	public XUtils() {
		frozenPlayers = new ArrayList<UUID>();
		uuidToGameMode = new HashMap<UUID, GameMode>();
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
		UUID uuid = player.getUniqueId();
		//TODO Check if gamemode is synced
		//TODO Check why players are able to swim whilst being frozen
		player.setAllowFlight(frozen);
		player.setFlying(frozen);
		player.setCollidable(!frozen);
		player.setInvulnerable(frozen);
		//player.setGravity(!frozen);
		player.setGravity(true);
		if ( frozen ) {
			
			if ( !uuidToGameMode.containsKey(uuid) ) { uuidToGameMode.put(uuid, player.getGameMode()); }
			
			//player.setGameMode(GameMode.SPECTATOR);
			player.setWalkSpeed(0);
			player.setFlySpeed(0);
			
		} else {
			
			if ( uuidToGameMode.containsKey(uuid) ) {
				//player.setGameMode(uuidToGameMode.get(uuid));
				uuidToGameMode.remove(uuid);
			} else {
				//player.setGameMode(GameMode.SURVIVAL);
			}
			
			player.setWalkSpeed(0.2f);
			player.setFlySpeed(0.2f);
			
		}
	}
	
	
}
