package com.gmail.xlifehd.xmysqlbridge.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class TestCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if ( sender instanceof Player ) {
			Player player = (Player) sender;
			
			Main.getPlugin().getLogger().info(String.valueOf(player.getExp()));
			Main.getPlugin().getLogger().info(String.valueOf(player.getExpToLevel()));
			Main.getPlugin().getLogger().info(String.valueOf(player.getTotalExperience()));
			
		} else {
			sender.sendMessage(Main.getPrefix("error") + "You have to be a player!");
		}
		return true;
	}

}
