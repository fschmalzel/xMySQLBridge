package com.gmail.xlifehd.xmysqlbridge;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
import java.util.UUID;

//import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class XUtils {
	
	private List<UUID> frozenPlayers;
//	private HashMap<UUID, GameMode> uuidToGameMode;
	
	public XUtils() {
		
		frozenPlayers = new ArrayList<UUID>();
//		uuidToGameMode = new HashMap<UUID, GameMode>();
		
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
//		UUID uuid = player.getUniqueId();
		
		//TODO Check if gamemode is synced
		if ( frozen ) {
			
			//if ( !uuidToGameMode.containsKey(uuid) ) { uuidToGameMode.put(uuid, player.getGameMode()); }
			
			player.setCollidable(false);
			player.setInvulnerable(true);
			player.setAllowFlight(true);
			player.setFlying(true);
			player.setWalkSpeed(0);
			player.setFlySpeed(0);
			
		} else {
			
//			if ( uuidToGameMode.containsKey(uuid) ) {
//				//DEBUG
//				//player.setGameMode(uuidToGameMode.get(uuid));
//				GameMode gm = uuidToGameMode.get(uuid);
//				
//				switch ( gm ) {
//				case CREATIVE:
//				case SPECTATOR:
//					player.setAllowFlight(true);
//					player.setFlying(true);
//					break;
//				case ADVENTURE:
//				case SURVIVAL:
//				default:
//					player.setAllowFlight(false);
//					player.setFlying(false);
//					break;
//				}
//				
//				uuidToGameMode.remove(uuid);
//				
//			} else {
//				
//				player.setGameMode(GameMode.SURVIVAL);
//				player.setAllowFlight(false);
//				player.setFlying(false);
//				
//			}
			
			player.setCollidable(true);
			player.setInvulnerable(false);
			player.setAllowFlight(false);
			player.setFlying(false);
			player.setWalkSpeed(0.2f);
			player.setFlySpeed(0.2f);
			
		}
		
	}
	
}
