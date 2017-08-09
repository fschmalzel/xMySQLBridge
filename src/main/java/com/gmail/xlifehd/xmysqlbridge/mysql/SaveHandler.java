package com.gmail.xlifehd.xmysqlbridge.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.xlifehd.xmysqlbridge.BukkitSerialization;
import com.gmail.xlifehd.xmysqlbridge.Main;

public class SaveHandler {
	
	//TODO CREATE QUERYS
	private static String queryHealth =			"INSERT INTO %s (uuid, health) VALUES (?, ?) ON DUPLICATE KEY UPDATE health = VALUES(health);";
	private static String queryHunger =			"INSERT INTO %s (uuid, hunger) VALUES (?, ?) ON DUPLICATE KEY UPDATE hunger = VALUES(hunger);";
	private static String queryEffects =		"";
	private static String queryLocation =		"INSERT INTO %s (uuid, world, x, y, z, yaw, pitch) VALUES (?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE " +
			"world = VALUES(world), x = VALUES(x), y = VALUES(y), z = VALUES(z), yaw = VALUES(yaw), pitch = VALUES(pitch);";
	private static String queryExperience =		"INSERT INTO %s (uuid, experience) VALUES (?, ?) ON DUPLICATE KEY UPDATE experience = VALUES(experience);";
	private static String queryMoney =			"INSERT INTO %s (uuid, money) VALUES (?, ?) ON DUPLICATE KEY UPDATE money = VALUES(money);";
	private static String queryInventory =		"INSERT INTO %s (uuid, inventory, armor) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE inventory = VALUES(inventory), armor = VALUES(armor);";
	private static String queryEnderchest =		"INSERT INTO %s (uuid, enderchest) VALUES (?, ?) ON DUPLICATE KEY UPDATE enderchest = VALUES(enderchest);";
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
					
					String mySQLPrefix = config.getString("mysql.prefix");
					
					if ( config.getBoolean("table.health.enabled")) {
						queryHealth = String.format(queryHealth, "`" + mySQLPrefix + config.getString("table.health.name") + "`");
						updateHealth = con.prepareStatement(queryHealth);
					}
					
					if ( config.getBoolean("table.hunger.enabled")) {
						queryHunger = String.format(queryHunger, "`" + mySQLPrefix + config.getString("table.hunger.name") + "`");
						updateHunger = con.prepareStatement(queryHunger);
					}
					
					if ( config.getBoolean("table.effects.enabled")) {
						queryEffects = String.format(queryEffects, "`" + mySQLPrefix + config.getString("table.effects.name") + "`");
						updateEffects = con.prepareStatement(queryEffects);
					}
					
					if ( config.getBoolean("table.location.enabled")) {
						queryLocation = String.format(queryLocation, "`" + mySQLPrefix + config.getString("table.location.name") + "`");
						updateLocation = con.prepareStatement(queryLocation);
					}
					
					if ( config.getBoolean("table.experience.enabled")) {
						queryExperience = String.format(queryExperience, "`" + mySQLPrefix + config.getString("table.experience.name") + "`");
						updateExperience = con.prepareStatement(queryExperience);
					}
					
					if ( config.getBoolean("table.money.enabled")) {
						queryMoney = String.format(queryMoney, "`" + mySQLPrefix + config.getString("table.money.name") + "`");
						updateMoney = con.prepareStatement(queryMoney);
					}
					
					if ( config.getBoolean("table.inventory.enabled")) {
						queryInventory = String.format(queryInventory, "`" + mySQLPrefix + config.getString("table.inventory.name") + "`");
						updateInventory = con.prepareStatement(queryInventory);
					}
					
					if ( config.getBoolean("table.enderchest.enabled")) {
						queryEnderchest = String.format(queryEnderchest, "`" + mySQLPrefix + config.getString("table.enderchest.name") + "`");
						updateEnderchest = con.prepareStatement(queryEnderchest);
					}
					
					if ( config.getBoolean("table.achievements.enabled")) {
						queryAchievements = String.format(queryAchievements, "`" + mySQLPrefix + config.getString("table.achievements.name") + "`");
						updateAchievements = con.prepareStatement(queryAchievements);
					}
					
					for ( OfflinePlayer player : players ) {
						
						String uuid = player.getUniqueId().toString();
						
						if ( updateHealth != null ) {
							updateHealth.setString(1, uuid);
							updateHealth.setDouble(2, player.getPlayer().getHealth());
							//DEBUG
							Main.getPlugin().getLogger().info(updateHealth.toString());
							updateHealth.executeUpdate();
						}
						
						if ( updateHunger != null ) {
							updateHunger.setString(1, uuid);
							updateHunger.setInt(2, player.getPlayer().getFoodLevel());
							//DEBUG
							Main.getPlugin().getLogger().info(updateHunger.toString());
							updateHunger.executeUpdate();
						}
						
						if ( updateEffects != null ) {
							//TODO SAVE EFFECTS
							updateEffects.executeUpdate();
						}
						
						if ( updateLocation != null ) {
							Location loc = player.getPlayer().getLocation();
							
							updateLocation.setString(1, uuid);
							updateLocation.setString(2, loc.getWorld().getName());
							updateLocation.setDouble(3, loc.getX());
							updateLocation.setDouble(4, loc.getY());
							updateLocation.setDouble(5, loc.getZ());
							updateLocation.setFloat (6, loc.getYaw());
							updateLocation.setFloat (7, loc.getPitch());
							//DEBUG
							Main.getPlugin().getLogger().info(updateLocation.toString());
							updateLocation.executeUpdate();
						}
						
						if ( updateExperience != null ) {
							updateExperience.setString(1, uuid);
							updateExperience.setInt(2, player.getPlayer().getTotalExperience());
							//DEBUG
							Main.getPlugin().getLogger().info(updateExperience.toString());
							updateExperience.executeUpdate();
						}
						
						if ( updateMoney != null ) {
							updateMoney.setString(1, uuid);
							updateMoney.setDouble(2, Main.getEconomy().getBalance(player));
							updateMoney.executeUpdate();
						}
						
						if ( updateInventory != null ) {
							String[] inventoryString = BukkitSerialization.playerInventoryToBase64(player.getPlayer().getInventory());
							updateInventory.setString(1, uuid);
							updateInventory.setString(2, inventoryString[0]);
							updateInventory.setString(3, inventoryString[1]);
							updateInventory.executeUpdate();
						}
						
						if ( updateEnderchest != null ) {
							String enderchestString = BukkitSerialization.itemStackArrayToBase64(player.getPlayer().getEnderChest().getStorageContents());
							updateEnderchest.setString(1, uuid);
							updateEnderchest.setString(2, enderchestString);
							updateEnderchest.executeUpdate();
						}
						
						if ( updateAchievements != null ) {
							//TODO SAVE ACHIEVEMENTS
							updateAchievements.executeUpdate();
						}
						
						con.commit();
						
					}
					
					//TODO Check if it exists
					if ( updateHealth != null ) { updateHealth.close(); };
					if ( updateHunger != null ) { updateHunger.close(); };
					if ( updateEffects != null ) { updateEffects.close(); };
					if ( updateLocation != null ) { updateLocation.close(); };
					if ( updateExperience != null ) { updateExperience.close(); };
					if ( updateMoney != null ) { updateMoney.close(); };
					if ( updateInventory != null ) { updateInventory.close(); };
					if ( updateEnderchest != null ) { updateEnderchest.close(); };
					if ( updateAchievements != null ) { updateAchievements.close(); };
					con.setAutoCommit(true);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		};
		
		r.runTaskAsynchronously(Main.getPlugin());

	}
	
}
