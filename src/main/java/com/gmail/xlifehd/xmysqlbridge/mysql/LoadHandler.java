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
	
	public Number[] getExperience() {
		String tableName = config.getString("table.experience.name");
		String query = "SELECT * FROM `" + mySQLPrefix + tableName + "` WHERE uuid = '" + uuid.toString() + "';";
		Connection con = Main.getPlugin().getMySQLHandler().getConnection();
		
		try {
			ResultSet rs = con.createStatement().executeQuery( query );
			
			if ( rs.next() ) {
				Integer totalExp = rs.getInt("totalExp");
				Integer level = rs.getInt("level");
				Float exp = rs.getFloat("exp");
				
				//DEBUG
				Main.getPlugin().getLogger().info(((Number) (totalExp)).toString());
				Main.getPlugin().getLogger().info(((Number) (level)).toString());
				Main.getPlugin().getLogger().info(((Number) (exp)).toString());
				//DEBUG
				Main.getPlugin().getLogger().info( String.valueOf(( (Number) (totalExp) ).intValue() ));
				Main.getPlugin().getLogger().info( String.valueOf(( (Number) (level) ).intValue() ));
				Main.getPlugin().getLogger().info( String.valueOf(( (Number) (exp) ).floatValue() ));
				
				return new Number[] {(Number) (totalExp), (Number) (level), (Number) (exp)};
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
				ItemStack[] inventory = BukkitSerialization.itemStackArrayFromBase64( rs.getString("inventory") );
				ItemStack[] armor = BukkitSerialization.itemStackArrayFromBase64( rs.getString("armor") );
				ItemStack[] offhand = BukkitSerialization.itemStackArrayFromBase64( rs.getString("offhand") );
				return new ItemStack[][] {inventory, armor, offhand};
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ItemStack[] getEnderchest() {
		String tableName = config.getString("table.enderchest.name");
		String query = "SELECT * FROM `" + mySQLPrefix + tableName + "` WHERE uuid = '" + uuid.toString() + "';";
		Connection con = Main.getPlugin().getMySQLHandler().getConnection();
		
		try {
			ResultSet rs = con.createStatement().executeQuery( query );
			
			if (rs.next() ) {
				ItemStack[] enderchest = BukkitSerialization.itemStackArrayFromBase64(rs.getString("enderchest"));
				return enderchest;
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















