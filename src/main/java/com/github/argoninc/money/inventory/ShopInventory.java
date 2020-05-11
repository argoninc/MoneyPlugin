package com.github.argoninc.money.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.argoninc.money.shop.Item;
import com.github.argoninc.money.shop.Shop;
import com.github.argoninc.money.shop.ShopUtils;

import net.md_5.bungee.api.ChatColor;

public class ShopInventory {
	private Inventory iv = null;
	
	public ShopInventory(Shop s, Player p) {
		
		iv = Bukkit.createInventory(null, size(s.getItens().length), "SHOP");
		
		//ShopUtils.debug(s);
		
		for (Item i : s.getItens()) {
			ItemStack is = i.getItemStack();
			ItemMeta im = is.getItemMeta();
			
			String message = ChatColor.RED+"VENDER POR ";
			if(i.isBuy()) {
				message = ChatColor.GREEN+"COMPRAR POR ";
			}
			
			im.setDisplayName(message+i.getPrice()+" ESMERALDA"+plural(i.getPrice()));
			is.setItemMeta(im);
			
			iv.addItem(is);
		}
		
	}

	private int size(int l) {
		if(l<=9) {
			return 9;
		}else if(l<=18) {
			return 18;
		}else if(l<=27) {
			return 27;
		}else if(l<=36) {
			return 36;
		}else if(l<=45) {
			return 45;
		}
		return 54;
	}

	public Inventory getInventory() {
		return iv;
	}
	
	private static String plural(int value) {
		String plural = "";
		if (value != 1) {
			plural += "S";
		}
		return plural;
	}
	
}
