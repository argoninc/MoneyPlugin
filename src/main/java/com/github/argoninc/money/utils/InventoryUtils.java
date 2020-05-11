package com.github.argoninc.money.utils;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryUtils {
	public static Inventory autoFill(Inventory banco, ItemStack itemStack) {
		Inventory retorno = banco;
		
		ItemStack[] arr = retorno.getContents();
		
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]==null) {
				retorno.setItem(i, itemStack);
			}
		}
		
		return retorno;
	}
}
