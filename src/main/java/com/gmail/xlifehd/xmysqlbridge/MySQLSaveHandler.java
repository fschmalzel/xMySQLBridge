package com.gmail.xlifehd.xmysqlbridge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class MySQLSaveHandler {
	
	//TODO Create SQL Query
	private static String stringHealth =		"";
	private static String stringHunger =		"";
	private static String stringEffects =		"";
	private static String stringPosition =		"";
	private static String stringExperience =	"";
	private static String stringMoney =			"";
	private static String stringInventory =		"";
	private static String stringEnderchest =	"";
	private static String stringAchievments =	"";
	
	public OfflinePlayer[] players;
	
	public MySQLSaveHandler (OfflinePlayer[] players) {
		this.players = players;
	}
	
	
	public void savePlayerData() {
		
		BukkitRunnable r = new BukkitRunnable() {
			
			public void run() {
				
				PreparedStatement updateHealth = null;
				PreparedStatement updateHunger = null;
				PreparedStatement updateEffects = null;
				PreparedStatement updatePosition = null;
				PreparedStatement updateExperience = null;
				PreparedStatement updateMoney = null;
				PreparedStatement updateInventory = null;
				PreparedStatement updateEnderchest = null;
				PreparedStatement updateAchievments = null;
				
				try {
					
					Main.getPlugin().getMySQLHandler().openConnection();
					Connection con = Main.getPlugin().getMySQLHandler().getConnection();
					FileConfiguration config = Main.getPlugin().getConfig();
					con.setAutoCommit(false);
					
					if ( config.getBoolean("table.health.enabled"))			{ updateHealth =		con.prepareStatement(stringHealth); }
					if ( config.getBoolean("table.hunger.enabled"))			{ updateHunger =		con.prepareStatement(stringHunger); }
					if ( config.getBoolean("table.effects.enabled"))		{ updateEffects =		con.prepareStatement(stringEffects); }
					if ( config.getBoolean("table.position.enabled"))		{ updatePosition =		con.prepareStatement(stringPosition); }
					if ( config.getBoolean("table.experience.enabled"))		{ updateExperience =	con.prepareStatement(stringExperience); }
					if ( config.getBoolean("table.money.enabled"))			{ updateMoney =			con.prepareStatement(stringMoney); }
					if ( config.getBoolean("table.inventory.enabled"))		{ updateInventory =		con.prepareStatement(stringInventory); }
					if ( config.getBoolean("table.enderchest.enabled"))		{ updateEnderchest =	con.prepareStatement(stringEnderchest); }
					if ( config.getBoolean("table.achievments.enabled"))	{ updateAchievments =	con.prepareStatement(stringAchievments); }
					
					for ( OfflinePlayer player : players ) {
						
						UUID uuid = player.getUniqueId();
						
						//TODO Replace variables in preparedstatements
						if ( updateHealth != null ) {
							
							
							updateHealth.executeUpdate();
						}
						
						if ( updateHunger != null ) {
							
							updateHunger.executeUpdate();
						}
						
						if ( updateEffects != null ) {
							
							updateEffects.executeUpdate();
						}
						
						if ( updatePosition != null ) {
							
							updatePosition.executeUpdate();
						}
						
						if ( updateExperience != null ) {
							
							
							updateExperience.executeUpdate();
						}
						
						if ( updateMoney != null ) {
							
							
							updateMoney.executeUpdate();
						}
						
						if ( updateInventory != null ) {
							
							updateInventory.executeUpdate();
						}
						
						if ( updateEnderchest != null ) {
							
							updateEnderchest.executeUpdate();
						}
						
						if ( updateAchievments != null ) {
							
							updateAchievments.executeUpdate();
						}
						
						con.commit();
						
					}
					
					updateHealth.close();
					updateHunger.close();
					updateEffects.close();
					updatePosition.close();
					updateExperience.close();
					updateMoney.close();
					updateInventory.close();
					updateEnderchest.close();
					updateAchievments.close();
					con.setAutoCommit(true);
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		};
		
		r.runTaskAsynchronously(Main.getPlugin());

	}
	
}
