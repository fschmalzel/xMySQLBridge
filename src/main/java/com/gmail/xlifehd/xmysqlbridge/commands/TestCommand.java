package com.gmail.xlifehd.xmysqlbridge.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.gmail.xlifehd.xmysqlbridge.BukkitSerialization;
import com.gmail.xlifehd.xmysqlbridge.Main;

public class TestCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if ( sender instanceof Player ) {
			Player player = (Player) sender;
			
			Main.getPlugin().getLogger().info(BukkitSerialization.itemStackArrayToBase64(player.getInventory().getExtraContents()));
			Main.getPlugin().getLogger().info(BukkitSerialization.itemStackArrayToBase64(player.getInventory().getContents()));
			Main.getPlugin().getLogger().info(BukkitSerialization.itemStackArrayToBase64(player.getInventory().getStorageContents()));
			Main.getPlugin().getLogger().info(BukkitSerialization.itemStackArrayToBase64(player.getInventory().getArmorContents()));
			Main.getPlugin().getLogger().info(BukkitSerialization.itemStackArrayToBase64(new ItemStack[] {player.getInventory().getItemInOffHand()}));
			
		} else {
			sender.sendMessage(Main.getPrefix("error") + "You have to be a player!");
		}
		return true;
	}

}
