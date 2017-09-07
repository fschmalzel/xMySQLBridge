package com.gmail.xlifehd.xmysqlbridge;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.gmail.xlifehd.xmysqlbridge.mysql.SetHandler;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

public class Sync implements PluginMessageListener {
	
	private List<UUID> savingList;
	private HashMap<UUID, SetHandler> setList;
	
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		
		if (!channel.equals("BungeeCord")) {
			return;
		}
		
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		String subchannel = in.readUTF();
		
		//Debug
		Main.getPlugin().getLogger().info(subchannel);
		
		if (!subchannel.equals(Main.getChannel())) {
			return;
		}
		
		try {
			
			short len = in.readShort();
			byte[] msgbytes = new byte[len];
			in.readFully(msgbytes);
			
			DataInputStream msgin = new DataInputStream( new ByteArrayInputStream(msgbytes));
			//0 = saving, 1 = saved
			Short state = msgin.readShort();
			UUID uuid = player.getUniqueId();
			
			switch ( state ) {
			case 0:
				if (!savingList.contains(uuid)) {
					savingList.add(uuid);
				}
				break;
			case 1:
				if (savingList.contains(uuid)) {
					savingList.remove(uuid);
					
					if (setList.containsKey(uuid)) {
						setList.get(uuid).start(0);
						setList.remove(uuid);
					}
					
				}
				break;
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean isSaving(UUID uuid) {
		return savingList.contains(uuid);
	}
	
	public void registerSetHandler(SetHandler sethandler, UUID uuid) {
		setList.put(uuid, sethandler);
	}
	
	
}
