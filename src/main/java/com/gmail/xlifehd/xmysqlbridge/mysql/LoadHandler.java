package com.gmail.xlifehd.xmysqlbridge.mysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.gmail.xlifehd.xmysqlbridge.BukkitSerialization;
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
	
	public Double getHealth () {
		String tableName = config.getString("table.health.name");
		String query = "SELECT * FROM " + mySQLPrefix + tableName + " WHERE uuid = " + uuid.toString() + ";";
		Connection con = Main.getPlugin().getMySQLHandler().getConnection();
		
		ResultSet rs;
		
		try {
			rs = con.createStatement().executeQuery( query );
			Double health =  rs.getDouble("health");
			if ( health != null ) {
				return health;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Integer getHunger() {
		String tableName = config.getString("table.hunger.name");
		String query = "SELECT * FROM " + mySQLPrefix + tableName + " WHERE uuid = " + uuid.toString() + ";";
		Connection con = Main.getPlugin().getMySQLHandler().getConnection();
		
		ResultSet rs;
		
		try {
			rs = con.createStatement().executeQuery( query );
			Integer hunger = rs.getInt("hunger");
			if ( hunger != null ) {
				return hunger;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void getEffects() {
		//TODO LOAD EFFECTS
	}
	
	public Location getLocation() {
		String tableName = config.getString("table.location.name");
		String query = "SELECT * FROM " + mySQLPrefix + tableName + " WHERE uuid = " + uuid.toString() + ";";
		Connection con = Main.getPlugin().getMySQLHandler().getConnection();
		
		ResultSet rs;
		
		try {
			rs = con.createStatement().executeQuery( query );
			String worldString = rs.getString("world");
			Double x = rs.getDouble("x");
			Double y = rs.getDouble("y");
			Double z = rs.getDouble("z");
			Float yaw = rs.getFloat("yaw");
			Float pitch = rs.getFloat("pitch");
			if ( worldString != null && x != null && y != null && z != null && yaw != null & pitch != null ) {
				World world = Bukkit.getWorld(worldString);
				Location loc = new Location(world, x, y, z, yaw, pitch);
				return loc;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Float getExperience() {
		String tableName = config.getString("table.experience.name");
		String query = "SELECT * FROM " + mySQLPrefix + tableName + " WHERE uuid = " + uuid.toString() + ";";
		Connection con = Main.getPlugin().getMySQLHandler().getConnection();
		
		ResultSet rs;
		
		try {
			rs = con.createStatement().executeQuery( query );
			Float experience = rs.getFloat("experience");
			if ( experience != null ) {
				return experience;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Double getMoney() {
		String tableName = config.getString("table.money.name");
		String query = "SELECT * FROM " + mySQLPrefix + tableName + " WHERE uuid = " + uuid.toString() + ";";
		Connection con = Main.getPlugin().getMySQLHandler().getConnection();
		
		ResultSet rs;
		
		try {
			rs = con.createStatement().executeQuery( query );
			Double money = rs.getDouble("money");
			if ( money != null ) {
				return money;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public PlayerInventory getInventory() {
		String tableName = config.getString("table.inventory.name");
		String query = "SELECT * FROM " + mySQLPrefix + tableName + " WHERE uuid = " + uuid.toString() + ";";
		Connection con = Main.getPlugin().getMySQLHandler().getConnection();
		
		ResultSet rs;
		
		try {
			rs = con.createStatement().executeQuery( query );
			String inventoryString = rs.getString("inventory");
			String armorString = rs.getString("armor");
			
			if ( inventoryString != null && armorString != null) {
				Inventory inventory = BukkitSerialization.fromBase64(inventoryString);
				ItemStack[] armor = BukkitSerialization.itemStackArrayFromBase64(armorString);
				PlayerInventory playerInventory = (PlayerInventory) inventory;
				playerInventory.setArmorContents(armor);
				return playerInventory;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Inventory getEnderchest() {
		String tableName = config.getString("table.enderchest.name");
		String query = "SELECT * FROM " + mySQLPrefix + tableName + " WHERE uuid = " + uuid.toString() + ";";
		Connection con = Main.getPlugin().getMySQLHandler().getConnection();
		
		ResultSet rs;
		
		try {
			rs = con.createStatement().executeQuery( query );
			String enderchestString = rs.getString("enderchest");
			if ( enderchestString != null ) {
				Inventory enderchestInventory = BukkitSerialization.fromBase64(enderchestString);
				return enderchestInventory;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void getAchievements() {
		//TODO LOAD ACHIEVEMENTS
	}
	
}















