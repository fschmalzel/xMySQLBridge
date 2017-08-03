package com.gmail.xlifehd.xmysqlbridge;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
	
	private static Main instance;
	
	private FileConfiguration config;
	
	private static Economy econ = null;
	
	private static String pluginPrefix = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "xMySQL" + ChatColor.GREEN + ChatColor.BOLD + "Bridge" + ChatColor.RESET + ChatColor.DARK_GRAY + "] ";
	private static String errorPrefix = pluginPrefix + ChatColor.RED;
	private static String infoPrefix = pluginPrefix + ChatColor.WHITE;
	
	@Override
	public void onEnable() {
		//TODO GET DATA
		instance = this;

		setupConfig();
		setupMySQL();
		
		if ( config.getBoolean("table.money.enabled") ) {
			if (!setupEconomy() ) {
				getLogger().severe(errorPrefix + "Disabled due to no Vault dependency found!");
				getServer().getPluginManager().disablePlugin(this);
				return;
			}
		}
		
	}
	
	
	@Override
	public void onDisable() {
		//TODO SAVE DATA
	}
	
	private void setupMySQL() {
		
		
		
		setupTables();
	}
	
	private void setupTables() {
		
	}
	
	private void setupConfig() {
		config = this.getConfig();
		
		config.options().header("xMySQLBridge Config by xLifeHD@gmail.com");
		config.addDefault("CfgVersion", 1);
		
		config.addDefault("mysql.host", "127.0.0.1");
		config.addDefault("mysql.port", 3306);
		config.addDefault("mysql.database", "minecraft");
		config.addDefault("mysql.prefix", "xbr_");
		config.addDefault("mysql.user", "admin");
		config.addDefault("mysql.password", "foobar");
		
		config.addDefault("savetask.enabled", true);
		config.addDefault("savetask.timer", 180);
		
		String[] tables = {"health", "hunger", "effects", "position", "experience", "money", "inventory", "enderchest"};
		
		for ( String table: tables ) {
			config.addDefault("table." + table + ".enabled", true);
			config.addDefault("table." + table + ".name", table);
			
		}
		
		config.options().copyHeader(true);
		config.options().copyDefaults(true);
		saveConfig();
	}
	
	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if ( rsp == null ) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
		
	}
	
	public static Main getPlugin() {
		return instance;
	}
	
	public FileConfiguration getConfig() {
		return config;
	}
	
	public static Economy getEconomy() {
		return econ;
	}
	
	public static String getPrefix(String str) {
		if ( str.equalsIgnoreCase("info") ) {
			return infoPrefix;
		} else if ( str.equalsIgnoreCase("error") ) {
			return errorPrefix;
		} else {
			return pluginPrefix;
		}
	}
	
}



















