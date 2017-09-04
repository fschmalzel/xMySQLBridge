package com.gmail.xlifehd.xmysqlbridge.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.xlifehd.xmysqlbridge.BukkitSerialization;
import com.gmail.xlifehd.xmysqlbridge.Main;

public class SaveHandler {
	
	private static String queryHealth =			"INSERT INTO %s (uuid, health) VALUES (?, ?) ON DUPLICATE KEY UPDATE health = VALUES(health);";
	private static String queryHunger =			"INSERT INTO %s (uuid, hunger, saturation) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE hunger = VALUES(hunger), saturation = VALUES(saturation);";
	private static String queryEffects =		"INSERT INTO %s (uuid, effects) VALUES (?, ?) ON DUPLICATE KEY UPDATE effects = VALUES(effects);";
	private static String queryLocation =		"INSERT INTO %s (uuid, world, x, y, z, yaw, pitch) VALUES (?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE " +
			"world = VALUES(world), x = VALUES(x), y = VALUES(y), z = VALUES(z), yaw = VALUES(yaw), pitch = VALUES(pitch);";
	private static String queryExperience =		"INSERT INTO %s (uuid, totalExp, level, exp) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE totalExp = VALUES(totalExp), level = VALUES(level), exp = VALUES(exp);";
	private static String queryInventory =		"INSERT INTO %s (uuid, inventory, armor, offhand) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE inventory = VALUES(inventory), armor = VALUES(armor), offhand = VALUES(offhand);";
	private static String queryEnderchest =		"INSERT INTO %s (uuid, enderchest) VALUES (?, ?) ON DUPLICATE KEY UPDATE enderchest = VALUES(enderchest);";
	private static String queryAdvancements =	"INSERT INTO %s (uuid, advancements) VALUES (?, ?) ON DUPLICATE KEY UPDATE advancements = VALUES(advancements);";
	
	private Player[] players;
	
	public SaveHandler (Player[] players) {
		this.players = players;
	}
	
	public void savePlayerDataAsync() {
		
		BukkitRunnable r = new BukkitRunnable() {
			
			public void run() {
				
				savePlayerData();
				
			}
		};
		
		r.runTaskAsynchronously(Main.getPlugin());

	}
	
	public void savePlayerData() {
		PreparedStatement updateHealth = null;
		PreparedStatement updateHunger = null;
		PreparedStatement updateEffects = null;
		PreparedStatement updateLocation = null;
		PreparedStatement updateExperience = null;
		PreparedStatement updateInventory = null;
		PreparedStatement updateEnderchest = null;
		PreparedStatement updateAdvancements = null;
		
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
			
			if ( config.getBoolean("table.inventory.enabled")) {
				queryInventory = String.format(queryInventory, "`" + mySQLPrefix + config.getString("table.inventory.name") + "`");
				updateInventory = con.prepareStatement(queryInventory);
			}
			
			if ( config.getBoolean("table.enderchest.enabled")) {
				queryEnderchest = String.format(queryEnderchest, "`" + mySQLPrefix + config.getString("table.enderchest.name") + "`");
				updateEnderchest = con.prepareStatement(queryEnderchest);
			}
			
			if ( config.getBoolean("table.advancements.enabled")) {
				queryAdvancements = String.format(queryAdvancements, "`" + mySQLPrefix + config.getString("table.advancements.name") + "`");
				updateAdvancements = con.prepareStatement(queryAdvancements);
			}
			
			for ( Player player : players ) {
				
				String uuid = player.getUniqueId().toString();
				
				if ( !Main.getPlugin().getxUtils().isFrozen(player.getUniqueId()) ) {
					
					if (updateHealth != null) {
						updateHealth.setString(1, uuid);
						updateHealth.setDouble(2, player.getHealth());

						updateHealth.executeUpdate();
					}
					
					if (updateHunger != null) {
						updateHunger.setString(1, uuid);
						updateHunger.setInt(2, player.getFoodLevel());
						updateHunger.setFloat(3, player.getSaturation());

						updateHunger.executeUpdate();
					}
					
					if (updateEffects != null) {
						String effects = BukkitSerialization.potionEffectsToBase64(player.getActivePotionEffects());
						
						updateEffects.setString(1, uuid);
						updateEffects.setString(2, effects);
						
						updateEffects.executeUpdate();
					}
					
					if (updateLocation != null) {
						Location loc = player.getLocation();

						updateLocation.setString(1, uuid);
						updateLocation.setString(2, loc.getWorld().getName());
						updateLocation.setDouble(3, loc.getX());
						updateLocation.setDouble(4, loc.getY());
						updateLocation.setDouble(5, loc.getZ());
						updateLocation.setFloat(6, loc.getYaw());
						updateLocation.setFloat(7, loc.getPitch());

						updateLocation.executeUpdate();
					}
					
					if (updateExperience != null) {
						updateExperience.setString(1, uuid);
						updateExperience.setInt(2, player.getTotalExperience());
						updateExperience.setInt(3, player.getLevel());
						updateExperience.setFloat(4, player.getExp());
						
						updateExperience.executeUpdate();
					}
					
					if (updateInventory != null) {
						String[] contents = BukkitSerialization.playerInventoryToBase64(player.getInventory());

						updateInventory.setString(1, uuid);
						updateInventory.setString(2, contents[0]);
						updateInventory.setString(3, contents[1]);
						updateInventory.setString(4, contents[2]);

						updateInventory.executeUpdate();
					}
					
					if (updateEnderchest != null) {
						String enderchestString = BukkitSerialization.itemStackArrayToBase64(player.getEnderChest().getStorageContents());

						updateEnderchest.setString(1, uuid);
						updateEnderchest.setString(2, enderchestString);

						updateEnderchest.executeUpdate();
					}
					
					if (updateAdvancements != null) {
						String advancements = BukkitSerialization.advancementsToBase64(player);
						
						updateAdvancements.setString(1, uuid);
						updateAdvancements.setString(2, advancements);
						
						updateAdvancements.executeUpdate();
					}
					
					con.commit();
				}
				
			}
			
			if ( updateHealth != null ) { updateHealth.close(); };
			if ( updateHunger != null ) { updateHunger.close(); };
			if ( updateEffects != null ) { updateEffects.close(); };
			if ( updateLocation != null ) { updateLocation.close(); };
			if ( updateExperience != null ) { updateExperience.close(); };
			if ( updateInventory != null ) { updateInventory.close(); };
			if ( updateEnderchest != null ) { updateEnderchest.close(); };
			if ( updateAdvancements != null ) { updateAdvancements.close(); };
			
			con.setAutoCommit(true);
			
			Main.getPlugin().getServer().getLogger().info("Data saved!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
