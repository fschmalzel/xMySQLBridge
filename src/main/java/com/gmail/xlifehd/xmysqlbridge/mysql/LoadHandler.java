package com.gmail.xlifehd.xmysqlbridge.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class LoadHandler {
	
	private OfflinePlayer player;
	private UUID uuid;
	private FileConfiguration config;
	private String mySQLPrefix;
	
	public LoadHandler ( OfflinePlayer player ) {
		this.player = player;
		uuid = player.getUniqueId();
		config = Main.getPlugin().getConfig();
		mySQLPrefix = config.getString("mysql.prefix");
	}
	
	public double getHealth () {
		String tableName = config.getString("table.health.name");
		String query = "SELECT * FROM " + mySQLPrefix + tableName + " WHERE uuid = " + uuid.toString() + ";";
		Connection con = Main.getPlugin().getMySQLHandler().getConnection();
		
		ResultSet rs;
		
		try {
			rs = con.createStatement().executeQuery( query );
			return rs.getDouble("health");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return player.getPlayer().getHealth();
	}
	
	public int getHunger() {
		String tableName = config.getString("table.hunger.name");
		String query = "SELECT * FROM " + mySQLPrefix + tableName + " WHERE uuid = " + uuid.toString() + ";";
		Connection con = Main.getPlugin().getMySQLHandler().getConnection();
		
		ResultSet rs;
		
		try {
			rs = con.createStatement().executeQuery( query );
			return rs.getInt("hunger");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return player.getPlayer().getFoodLevel();
	}
	
	public void getEffects() {
		//TODO LOAD EFFECTS
	}
	
	public Location getLocation() {
		//TODO LOAD LOCATION
		return null;
	}
	
	public float getExperience() {
		String tableName = config.getString("table.experience.name");
		String query = "SELECT * FROM " + mySQLPrefix + tableName + " WHERE uuid = " + uuid.toString() + ";";
		Connection con = Main.getPlugin().getMySQLHandler().getConnection();
		
		ResultSet rs;
		
		try {
			rs = con.createStatement().executeQuery( query );
			return rs.getFloat("experience");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return player.getPlayer().getExp();
	}
	
	public double getMoney() {
		//TODO LOAD MONEY
		return 0;
	}
	
	public void getInventory() {
		//TODO LOAD INVENTORY
	}
	
	public void getEnderchest() {
		//TODO LOAD ENDERCHEST
	}
	
	public void getAchievements() {
		//TODO LOAD ACHIEVEMENTS
	}
	
}
