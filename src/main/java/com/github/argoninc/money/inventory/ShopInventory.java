package com.github.argoninc.money.inventory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import com.github.argoninc.job.user.Job;
import com.github.argoninc.job.user.UserJob;
import com.github.argoninc.money.shop.Item;
import com.github.argoninc.money.shop.Shop;

public class ShopInventory {
	public static List<MerchantRecipe> getRecipes(Shop s, Player p) {
		List<MerchantRecipe> rec = new ArrayList<MerchantRecipe>();
		String uuid = p.getUniqueId().toString();
		
		for (Item i : s.getItens()) {
			
			//checar se canbuy
			UserJob u = UserJob.getUser(uuid);
			
			boolean canBuy = true;
			if(i.getCanBuy()!=null) {
				canBuy = false;
				for (Job j : i.getCanBuy()) {
					if(u.hasJob()) {
						if(u.getJob().getKey().equals(j.getKey())) {
							canBuy = true;
						}
					}
				}
			}
			
			if(canBuy) {
				MerchantRecipe r = null; 
				ItemStack esm = null;
				ItemStack esm2 = null;
				
				ItemStack item = i.getItemStack();
				
				int price = i.getPrice();
				
				//desconto
				if(i.getBonusJob()!=null) {
					for (Job j : i.getBonusJob()) {
						if(u.hasJob()) {
							if(u.getJob().getKey().equals(j.getKey())) {
								price = i.getPriceBonus();
								item.setAmount(i.getAmountBonus());
							}
						}
					}
				}
				
				
				if(price>64) {
					esm = new ItemStack(Material.EMERALD, 64);
					esm2 = new ItemStack(Material.EMERALD, price-64);
				}else {
					esm = new ItemStack(Material.EMERALD, price);
				}
				
				if(i.isBuy()) {
					 r = new MerchantRecipe(item, 0, 8000, false, 0, 0);
					 r.addIngredient(esm);
					 if(esm2 != null) {
						 r.addIngredient(esm2);
					 }
				}else {
					 r = new MerchantRecipe(esm, 0, 8000, false, 0, 0);
					 r.addIngredient(item);
				}
				
				rec.add(r);
			}
		}
		return rec;
	}
}