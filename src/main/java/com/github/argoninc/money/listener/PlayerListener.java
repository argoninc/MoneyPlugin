package com.github.argoninc.money.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.github.argoninc.money.Principal;

import net.md_5.bungee.api.ChatColor;

public class PlayerListener implements Listener{
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		String uuid = player.getUniqueId().toString();
		
		if(!Principal.banco.has(uuid)) {
			Principal.banco.set(uuid,0);
		}
	}
}
