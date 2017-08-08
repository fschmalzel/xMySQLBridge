package com.gmail.xlifehd.xmysqlbridge.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class SaveHandler {
	
	//TODO CREATE QUERYS
	private static String queryHealth =			"INSERT INTO ? (uuid, health) VALUES (?, ?) ON DUPLICATE KEY UPDATE health = VALUES(health);";
	private static String queryHunger =			"INSERT INTO ? (uuid, hunger) VALUES (?, ?) ON DUPLICATE KEY UPDATE hunger = VALUES(hunger);";
	private static String queryEffects =		"";
	private static String queryLocation =		"";
	private static String queryExperience =		"INSERT INTO ? (uuid, experience) VALUES (?, ?) ON DUPLICATE KEY UPDATE experience = VALUES(experience);";
	private static String queryMoney =			"";
	private static String queryInventory =		"";
	private static String queryEnderchest =		"";
	private static String queryAchievements =	"";
	
	private OfflinePlayer[] players;
	
	public SaveHandler (OfflinePlayer[] players) {
		this.players = players;
	}
	
	public void savePlayerData() {
		
		BukkitRunnable r = new BukkitRunnable() {
			
			public void run() {
				
				PreparedStatement updateHealth = null;
				PreparedStatement updateHunger = null;
				PreparedStatement updateEffects = null;
				PreparedStatement updateLocation = null;
				PreparedStatement updateExperience = null;
				PreparedStatement updateMoney = null;
				PreparedStatement updateInventory = null;
				PreparedStatement updateEnderchest = null;
				PreparedStatement updateAchievements = null;
				
				try {
					
					Connection con = Main.getPlugin().getMySQLHandler().getConnection();
					FileConfiguration config = Main.getPlugin().getConfig();
					con.setAutoCommit(false);
					
					if ( config.getBoolean("table.health.enabled"))			{ updateHealth =		con.prepareStatement(queryHealth); }
					if ( config.getBoolean("table.hunger.enabled"))			{ updateHunger =		con.prepareStatement(queryHunger); }
					if ( config.getBoolean("table.effects.enabled"))		{ updateEffects =		con.prepareStatement(queryEffects); }
					if ( config.getBoolean("table.location.enabled"))		{ updateLocation =		con.prepareStatement(queryLocation); }
					if ( config.getBoolean("table.experience.enabled"))		{ updateExperience =	con.prepareStatement(queryExperience); }
					if ( config.getBoolean("table.money.enabled"))			{ updateMoney =			con.prepareStatement(queryMoney); }
					if ( config.getBoolean("table.inventory.enabled"))		{ updateInventory =		con.prepareStatement(queryInventory); }
					if ( config.getBoolean("table.enderchest.enabled"))		{ updateEnderchest =	con.prepareStatement(queryEnderchest); }
					if ( config.getBoolean("table.achievements.enabled"))	{ updateAchievements =	con.prepareStatement(queryAchievements); }
					
					for ( OfflinePlayer player : players ) {
						
						String uuid = player.getUniqueId().toString();
						
						if ( updateHealth != null ) {
							updateHealth.setString(1, config.getString("mysql.prefix") + config.getString("table.health.name"));
							updateHealth.setString(2, uuid);
							updateHealth.setDouble(3, player.getPlayer().getHealth());
							updateHealth.executeUpdate();
						}
						
						if ( updateHunger != null ) {
							updateHealth.setString(1, config.getString("mysql.prefix") + config.getString("table.hunger.name"));
							updateHealth.setString(2, uuid);
							updateHealth.setInt(3, player.getPlayer().getFoodLevel());
							updateHunger.executeUpdate();
						}
						
						if ( updateEffects != null ) {
							//TODO SAVE EFFECTS
							updateEffects.executeUpdate();
						}
						
						if ( updateLocation != null ) {
							//TODO SAVE LOCATION
							updateLocation.executeUpdate();
						}
						
						if ( updateExperience != null ) {
							updateHealth.setString(1, config.getString("mysql.prefix") + config.getString("table.experience.name"));
							updateHealth.setString(2, uuid);
							updateHealth.setFloat(3, player.getPlayer().getExp());
							updateExperience.executeUpdate();
						}
						
						if ( updateMoney != null ) {
							//TODO SAVE MONEY
							updateMoney.executeUpdate();
						}
						
						if ( updateInventory != null ) {
							//TODO SAVE INVENTORY
							updateInventory.executeUpdate();
						}
						
						if ( updateEnderchest != null ) {
							//TODO SAVE ENDERCHEST
							updateEnderchest.executeUpdate();
						}
						
						if ( updateAchievements != null ) {
							//TODO SAVE ACHIEVEMENTS
							updateAchievements.executeUpdate();
						}
						
						con.commit();
						
					}
					
					updateHealth.close();
					updateHunger.close();
					updateEffects.close();
					updateLocation.close();
					updateExperience.close();
					updateMoney.close();
					updateInventory.close();
					updateEnderchest.close();
					updateAchievements.close();
					con.setAutoCommit(true);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		};
		
		r.runTaskAsynchronously(Main.getPlugin());

	}
	
}
