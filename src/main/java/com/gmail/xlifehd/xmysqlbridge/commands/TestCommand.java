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
			
			Main.getPlugin().getLogger().info(String.valueOf(player.getTotalExperience()));
			Main.getPlugin().getLogger().info(String.valueOf(player.getLevel()));
			Main.getPlugin().getLogger().info(String.valueOf(player.getExp()));
			
		} else {
			sender.sendMessage(Main.getPrefix("error") + "You have to be a player!");
		}
		return true;
	}

}
