package com.gmail.xlifehd.xmysqlbridge.mysql;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitTask;

import com.gmail.xlifehd.xmysqlbridge.Main;

public class SetHandler {
	
	private Player player;
	private BukkitTask unfreezeTask;
	
	public SetHandler ( Player player ) {
		
		this.player = player;
		
	}
	
	public void init() {
		
		UUID uuid = player.getUniqueId();
		Main.getxUtils().freezePlayer(player);
		
		if (!Main.getSync().isSaving(uuid)) {
			start(Main.getPlugin().getConfig().getLong("loadDelayinTicks"));
		} else {
			Main.getSync().registerSetHandler(this, uuid);
		}
		
		unfreezeTask = Main.getPlugin().getServer().getScheduler().runTaskLater(Main.getPlugin(), unfreezeRunnable, 20*30);
		
	}
	
	public void start(long delay) {
		Main.getPlugin().getServer().getScheduler().runTaskLaterAsynchronously(Main.getPlugin(), setRunnable, delay);
	}
	
	private Runnable unfreezeRunnable = new Runnable() {
		
		public void run() {
			Main.getxUtils().unfreezePlayer(player);
		}
		
	};
	
	private Runnable setRunnable = new Runnable() {
		
		public void run() {
			
			player.sendMessage(Main.getPrefix("info") + "Loading Data! Please wait a second!");
			
			FileConfiguration config = Main.getPlugin().getConfig();
			LoadHandler loadHandler = new LoadHandler(player.getUniqueId());
			
			if ( config.getBoolean("table.health.enabled") ) {
				Double health = loadHandler.getHealth();
				if ( health != null ) {
					player.setHealth(health);
				}
			}
			
			if ( config.getBoolean("table.hunger.enabled") ) {
				Number[] data = loadHandler.getHunger();
				if ( data != null ) {
					player.setFoodLevel(data[0].intValue());
					player.setSaturation(data[1].floatValue());
				}
			}
			
			if ( config.getBoolean("table.effects.enabled") ) {
				Collection<PotionEffect> newEffects = loadHandler.getEffects();
				Collection<PotionEffect> oldEffects = player.getActivePotionEffects();
				
				//Remove old potion effects
				for ( Iterator<PotionEffect> i = oldEffects.iterator(); i.hasNext(); ) {
					PotionEffect effect = i.next();
					player.removePotionEffect(effect.getType());
				}
				
				//Set new potion effects
				player.addPotionEffects(newEffects);
			}
			
			if ( config.getBoolean("table.location.enabled") ) {
				Location loc = loadHandler.getLocation();
				if ( loc != null ) {
					player.teleport(loc);
				}
			}
			
			if ( config.getBoolean("table.experience.enabled") ) {
				Number[] data = loadHandler.getExperience();
				if ( data != null ) {
					player.setTotalExperience(data[0].intValue());
					player.setLevel(data[1].intValue());
					player.setExp(data[2].floatValue());
				}
			}
			
			if ( config.getBoolean("table.inventory.enabled") ) {
				ItemStack[][] contents = loadHandler.getInventory();
				
				if ( contents != null ) {
					PlayerInventory playerInventory = player.getInventory();
					playerInventory.setStorageContents(contents[0]);
					playerInventory.setArmorContents(contents[1]);
					playerInventory.setItemInOffHand(contents[2][0]);
				}
			}
			
			if ( config.getBoolean("table.enderchest.enabled") ) {
				ItemStack[] enderchest = loadHandler.getEnderchest();
				if ( enderchest != null ) {
					player.getEnderChest().setStorageContents(enderchest);
				}
			}
			
			if ( config.getBoolean("table.advancements.enabled") ) {
				
				HashMap<Advancement, AdvancementProgress> hashMap = loadHandler.getAdvancements();
				
				//Iterate through all Advancements
				for ( Iterator<Advancement> i = hashMap.keySet().iterator(); i.hasNext(); ) {
					
					Advancement adv = i.next();
					
					//Check if the advancement is present on this server
					if ( Bukkit.getAdvancement(adv.getKey()) != null ) {
						
						AdvancementProgress advProg = player.getAdvancementProgress(adv);
						AdvancementProgress newAdvProg = hashMap.get(adv);
						
						//Check if the advancement is already completed
						if ( !(advProg.isDone() && newAdvProg.isDone()) ) {
							
							Collection<String> newAwardedCriteriaList = newAdvProg.getAwardedCriteria();
							Collection<String> awardedCriteria = advProg.getAwardedCriteria();
							
							//Iterate through all awardedcriteria
							for ( Iterator<String> j = newAwardedCriteriaList.iterator(); j.hasNext(); ) {
								
								String newAwardedCriteria = j.next();
								
								//Check if the criteria has already been awarded
								if ( !awardedCriteria.contains(newAwardedCriteria) ) {
									advProg.awardCriteria(newAwardedCriteria);
								}
								
							}
							
							Collection<String> newRemainingCriteriaList = newAdvProg.getRemainingCriteria();
							
							//Iterate through all remaining criteria
							for ( Iterator<String> j = newRemainingCriteriaList.iterator(); j.hasNext(); ) {
								
								String remainingCriteria = j.next();
								
								//Check if the criteria has been awarded
								if ( awardedCriteria.contains(remainingCriteria) ) {
									advProg.revokeCriteria(remainingCriteria);
								}
								
							}
							
						}
						
					}
					
				}
				
			}//Advancement end
			
			unfreezeTask.cancel();
			Main.getxUtils().unfreezePlayer(player);
			Main.getPlugin().getServer().getLogger().info("Data loaded!");
			player.sendMessage(Main.getPrefix("info") + "Data loaded!");
			
		}//Run end
		
	};
	
	
}
