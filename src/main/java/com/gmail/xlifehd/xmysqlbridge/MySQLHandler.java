package com.gmail.xlifehd.xmysqlbridge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

public class MySQLHandler {
	
	private Connection connection;
	private String host, database, username, password;
	private int port;
	
	public MySQLHandler( FileConfiguration config ) {
		
		host = config.getString("mysql.host");
		port = config.getInt("mysql.port");
		database = config.getString("mysql.database");
		username = config.getString("mysql.username");
		password = config.getString("mysql.password");
		
	}
	
	public void openConnection() throws SQLException, ClassNotFoundException {
		if ( connection != null && !connection.isClosed() ) {
			return;
		}
		
		synchronized (this) {
			if ( connection != null && !connection.isClosed() ) {
				return;
			}
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password);
			
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	
}
