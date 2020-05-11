package com.github.argoninc.money;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.argoninc.money.command.Cooldown;
import com.github.argoninc.money.listener.PlayerListener;
import com.github.argoninc.money.listener.VillagerListener;
import com.github.rillis.dao.DB;

public class Principal extends JavaPlugin{
	public static DB banco = null;
	public static DB config = null;
	@Override
	public void onEnable() {
		banco = new DB("argoninc/money.json");
		config = new DB("argoninc/money.cfg");
		
		VillagerListener.cd = new ArrayList<Cooldown>();
		
		startVar(config, "showVillagerUUID", false);
		startVar(config, "bankUUID", "");
		
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		getServer().getPluginManager().registerEvents(new VillagerListener(), this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	private void startVar(DB db, String var, Object value) {
		if(!db.has(var)) {
			db.set(var, value);
		}
	}
	
	
	
	
	
}
