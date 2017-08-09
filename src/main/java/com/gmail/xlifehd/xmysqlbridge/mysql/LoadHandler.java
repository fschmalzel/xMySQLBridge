package com.gmail.xlifehd.xmysqlbridge.mysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.gmail.xlifehd.xmysqlbridge.BukkitSerialization;
import com.gmail.xlifehd.xmysqlbridge.Main;

public class LoadHandler {
	
	private UUID uuid;
	private FileConfiguration config;
	private String mySQLPrefix;
	
	public LoadHandler ( UUID uuid ) {
		this.uuid = uuid;
		config = Main.getPlugin().getConfig();
		mySQLPrefix = config.getString("mysql.prefix");
	}
	
	public Double getHealth () {
		String tableName = config.getString("table.health.name");
		String query = "SELECT * FROM `" + mySQLPrefix + tableName + "` WHERE uuid = '" + uuid.toString() + "';";
		//DEBUG
		Main.getPlugin().getLogger().info(query);
		Connection con = Main.getPlugin().getMySQLHandler().getConnection();
		
		try {
			ResultSet rs = con.createStatement().executeQuery( query );
			
			if ( rs.next() ) {
				Double health =  rs.getDouble("health");
				return health;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Integer getHunger() {
		String tableName = config.getString("table.hunger.name");
		String query = "SELECT * FROM `" + mySQLPrefix + tableName + "` WHERE uuid = '" + uuid.toString() + "';";
		Connection con = Main.getPlugin().getMySQLHandler().getConnection();
		
		try {
			ResultSet rs = con.createStatement().executeQuery( query );
			
			if ( rs.next() ) {
				Integer hunger = rs.getInt("hunger");
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
		String query = "SELECT * FROM `" + mySQLPrefix + tableName + "` WHERE uuid = '" + uuid.toString() + "';";
		Connection con = Main.getPlugin().getMySQLHandler().getConnection();
		
		try {
			ResultSet rs = con.createStatement().executeQuery( query );

			if ( rs.next() ) {
				World world = Bukkit.getWorld(rs.getString("world"));
				Double x = rs.getDouble("x");
				Double y = rs.getDouble("y");
				Double z = rs.getDouble("z");
				Float yaw = rs.getFloat("yaw");
				Float pitch = rs.getFloat("pitch");
				Location loc = new Location(world, x, y, z, yaw, pitch);
				return loc;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Integer getExperience() {
		String tableName = config.getString("table.experience.name");
		String query = "SELECT * FROM `" + mySQLPrefix + tableName + "` WHERE uuid = '" + uuid.toString() + "';";
		Connection con = Main.getPlugin().getMySQLHandler().getConnection();
		
		try {
			ResultSet rs = con.createStatement().executeQuery( query );
			
			if ( rs.next() ) {
				Integer experience = rs.getInt("experience");
				return experience;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Double getMoney() {
		String tableName = config.getString("table.money.name");
		String query = "SELECT * FROM `" + mySQLPrefix + tableName + "` WHERE uuid = '" + uuid.toString() + "';";
		Connection con = Main.getPlugin().getMySQLHandler().getConnection();
		
		try {
			ResultSet rs = con.createStatement().executeQuery( query );
			
			if ( rs.next() ) {
				Double money = rs.getDouble("money");
				return money;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ItemStack[][] getInventory() {
		String tableName = config.getString("table.inventory.name");
		String query = "SELECT * FROM `" + mySQLPrefix + tableName + "` WHERE uuid = '" + uuid.toString() + "';";
		Connection con = Main.getPlugin().getMySQLHandler().getConnection();
		
		try {
			ResultSet rs = con.createStatement().executeQuery( query );
			
			if ( rs.next() ) {
				ItemStack[] inventory = BukkitSerialization.fromBase64( rs.getString("inventory") ).getContents();
				ItemStack[] armor = BukkitSerialization.itemStackArrayFromBase64( rs.getString("armor") );
				return new ItemStack[][] {inventory, armor};
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
		String query = "SELECT * FROM `" + mySQLPrefix + tableName + "` WHERE uuid = '" + uuid.toString() + "';";
		Connection con = Main.getPlugin().getMySQLHandler().getConnection();
		
		try {
			ResultSet rs = con.createStatement().executeQuery( query );
			
			if (rs.next() ) {
				Inventory enderchestInventory = BukkitSerialization.fromBase64(rs.getString("enderchest"));
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















