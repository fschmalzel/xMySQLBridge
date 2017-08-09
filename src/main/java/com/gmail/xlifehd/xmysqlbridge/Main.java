package com.gmail.xlifehd.xmysqlbridge;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmai.xlifehd.xmysqlbridge.listener.OnJoin;
import com.gmai.xlifehd.xmysqlbridge.listener.OnQuit;
import com.gmail.xlifehd.xmysqlbridge.commands.TestCommand;
import com.gmail.xlifehd.xmysqlbridge.mysql.GeneralHandler;
import com.gmail.xlifehd.xmysqlbridge.mysql.SaveHandler;

import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
	
	private static Main instance;
	
	private FileConfiguration config;
	
	private GeneralHandler mySQLHandler;
	
	private static Economy econ = null;
	
	private static String pluginPrefix = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "xMySQL" + ChatColor.GREEN + ChatColor.BOLD + "Bridge" + ChatColor.RESET + ChatColor.DARK_GRAY + "] ";
	private static String errorPrefix = pluginPrefix + ChatColor.RED;
	private static String infoPrefix = pluginPrefix + ChatColor.WHITE;
	
	@Override
	public void onEnable() {
		
		instance = this;
		
		setupConfig();
		
		if ( config.getBoolean("table.money.enabled") ) {
			if (!setupEconomy() ) {
				getLogger().severe(errorPrefix + "Disabled due to no Vault dependency found!");
				getServer().getPluginManager().disablePlugin(this);
				return;
			}
		}
		
		mySQLHandler = new GeneralHandler(config);
		
		//TODO Readup on advanced events
		//Register Events
		getServer().getPluginManager().registerEvents(new OnJoin(), this);
		getServer().getPluginManager().registerEvents(new OnQuit(), this);
		
		//DEBUG
		//Register Commands
		this.getCommand("xmbr").setExecutor(new TestCommand());
	}
	
	@Override
	public void onDisable() {
		Collection<? extends Player> playerCollection = Bukkit.getOnlinePlayers();
		OfflinePlayer[] players = (OfflinePlayer[]) playerCollection.toArray();
		SaveHandler saveHandler = new SaveHandler(players);
		saveHandler.savePlayerData();
	}
	
	private void setupConfig() {
		config = getConfig();
		
		config.options().header("xMySQLBridge Config by xLifeHD@gmail.com");
		config.addDefault("CfgVersion", 1);
		
		config.addDefault("mysql.host", "127.0.0.1");
		config.addDefault("mysql.port", 3306);
		config.addDefault("mysql.database", "minecraft");
		config.addDefault("mysql.username", "admin");
		config.addDefault("mysql.password", "foobar");
		config.addDefault("mysql.prefix", "xbr_");
		
		//TODO Create savetask
		config.addDefault("savetask.enabled", true);
		config.addDefault("savetask.timer", 180);
		
		String[] tables = {"health", "hunger", "effects", "location", "experience", "money", "inventory", "enderchest", "achievements"};
		
		for ( String table: tables ) {
			config.addDefault("table." + table + ".enabled", false);
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

	public GeneralHandler getMySQLHandler() {
		return mySQLHandler;
	}
	
}



















