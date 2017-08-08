package com.gmail.xlifehd.xmysqlbridge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

public class MySQLSaveHandler {
	
	//TODO Create SQL Query, don't forget config	
	private static String queryHealth =			"INSERT INTO ? (uuid, health) VALUES ('?', ?) ON DUPLICATE KEY UPDATE health = VALUES(health);";
	private static String queryHunger =			"";
	private static String queryEffects =		"";
	private static String queryPosition =		"";
	private static String queryExperience =		"";
	private static String queryMoney =			"";
	private static String queryInventory =		"";
	private static String queryEnderchest =		"";
	private static String queryAchievments =	"";
	
	private OfflinePlayer[] players;
	
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
					
					Connection con = Main.getPlugin().getMySQLHandler().getConnection();
					FileConfiguration config = Main.getPlugin().getConfig();
					con.setAutoCommit(false);
					
					if ( config.getBoolean("table.health.enabled"))			{ updateHealth =		con.prepareStatement(queryHealth); }
					if ( config.getBoolean("table.hunger.enabled"))			{ updateHunger =		con.prepareStatement(queryHunger); }
					if ( config.getBoolean("table.effects.enabled"))		{ updateEffects =		con.prepareStatement(queryEffects); }
					if ( config.getBoolean("table.position.enabled"))		{ updatePosition =		con.prepareStatement(queryPosition); }
					if ( config.getBoolean("table.experience.enabled"))		{ updateExperience =	con.prepareStatement(queryExperience); }
					if ( config.getBoolean("table.money.enabled"))			{ updateMoney =			con.prepareStatement(queryMoney); }
					if ( config.getBoolean("table.inventory.enabled"))		{ updateInventory =		con.prepareStatement(queryInventory); }
					if ( config.getBoolean("table.enderchest.enabled"))		{ updateEnderchest =	con.prepareStatement(queryEnderchest); }
					if ( config.getBoolean("table.achievments.enabled"))	{ updateAchievments =	con.prepareStatement(queryAchievments); }
					
					for ( OfflinePlayer player : players ) {
						
						String uuid = player.getUniqueId().toString();
						
						//TODO Replace variables in preparedstatements
						if ( updateHealth != null ) {
							updateHealth.setString(1, config.getString("mysql.prefix") + config.getString("table.health.name"));
							updateHealth.setString(2, uuid);
							updateHealth.setDouble(3, player.getPlayer().getHealth());
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
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		};
		
		r.runTaskAsynchronously(Main.getPlugin());

	}
	
}
