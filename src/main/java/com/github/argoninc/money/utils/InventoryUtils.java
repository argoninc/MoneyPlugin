package com.github.argoninc.money.utils;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryUtils {
	public static void autoFill(Inventory inv, ItemStack itemStack) {
		for (ItemStack item : inv) {
			if(item==null) {
				item = itemStack;
			}
		}
	}
}
