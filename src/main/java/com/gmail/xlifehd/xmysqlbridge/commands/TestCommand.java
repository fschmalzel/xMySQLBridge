package com.gmail.xlifehd.xmysqlbridge.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.xlifehd.xmysqlbridge.BukkitSerialization;
import com.gmail.xlifehd.xmysqlbridge.Main;

public class TestCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if ( sender instanceof Player ) {
			Player player = (Player) sender;
			String[] inventory = BukkitSerialization.playerInventoryToBase64(player.getInventory());
			String enderchest = BukkitSerialization.toBase64(player.getEnderChest());
			Main.getPlugin().getLogger().info(inventory[0]);
			Main.getPlugin().getLogger().info(inventory[1]);
			Main.getPlugin().getLogger().info(enderchest);
			Main.getPlugin().getLogger().info("Inventory: " + inventory[0].length() + " | Armor: " + inventory[1].length() + " | Enderchest: " + enderchest.length());
		} else {
			sender.sendMessage(Main.getPrefix("error") + "You have to be a player!");
		}
		return true;
	}


}
