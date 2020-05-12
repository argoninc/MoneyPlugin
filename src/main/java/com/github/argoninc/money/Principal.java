package com.github.argoninc.money;

import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONArray;

import com.github.argoninc.job.user.Job;
import com.github.argoninc.job.user.UserJob;
import com.github.argoninc.money.command.Cooldown;
import com.github.argoninc.money.listener.PlayerListener;
import com.github.argoninc.money.listener.VillagerListener;
import com.github.argoninc.money.shop.Shop;
import com.github.argoninc.money.shop.ShopLoader;
import com.github.rillis.dao.DB;

public class Principal extends JavaPlugin{
	public static DB banco = null;
	public static DB config = null;
	public static DB shop = null;
	
	public static Shop[] shopList = null;
	@Override
	public void onEnable() {
		//Job implement Init
		Job.init();
		UserJob.init();
		
		
		banco = new DB("argoninc/money.json");
		config = new DB("argoninc/money.cfg");
		shop = new DB("argoninc/shop.cfg");
		
		VillagerListener.cd = new ArrayList<Cooldown>();
		
		startVar(config, "showVillagerUUID", false);
		startVar(config, "bankUUID", "");
		

		startVar(shop, "shops", new JSONArray());
		shopList = ShopLoader.getShops();
		
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
