package com.github.argoninc.money.inventory;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import com.github.argoninc.money.shop.Item;
import com.github.argoninc.money.shop.Shop;

public class ShopInventory {
	public static List<MerchantRecipe> getRecipes(Shop s, boolean discount) {
		List<MerchantRecipe> rec = new ArrayList<MerchantRecipe>();
		
		for (Item i : s.getItens()) {
			MerchantRecipe r = null; 
			ItemStack esm = null;
			ItemStack esm2 = null;
			
			ItemStack item = i.getItemStack();
			
			int price = i.getPrice();
			if(discount) {
				if(i.getAmountBonus()>0) {
					item.setAmount(i.getAmountBonus());
				}
				
				if(i.getPriceBonus()>0) {
					price = i.getPriceBonus();
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
		return rec;
	}
}