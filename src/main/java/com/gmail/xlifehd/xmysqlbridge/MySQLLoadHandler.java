package com.gmail.xlifehd.xmysqlbridge;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;

public class MySQLLoadHandler {
	
	private OfflinePlayer player;
	private UUID uuid;
	private FileConfiguration config;
	private String mySQLPrefix;
	
	public MySQLLoadHandler ( OfflinePlayer player ) {
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
	
}
