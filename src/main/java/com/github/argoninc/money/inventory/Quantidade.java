package com.github.argoninc.money.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.github.argoninc.money.utils.ItemStackUtils;

public class Quantidade{
	private Inventory qtd;
	
	public Quantidade(Player player, boolean deposito){
		String title = "Saque de ";
		if(deposito) {
			title = "Deposito de ";
		}
		
		qtd = Bukkit.createInventory(null, 9, title + player.getName());

		qtd.setItem(0, ItemStackUtils.rename(new ItemStack(Material.LIGHT_BLUE_CONCRETE_POWDER, 1), ChatColor.YELLOW + "1"));
		qtd.setItem(1, ItemStackUtils.rename(new ItemStack(Material.LIGHT_BLUE_CONCRETE, 1), ChatColor.YELLOW + "5"));
		qtd.setItem(2, ItemStackUtils.rename(new ItemStack(Material.BLUE_CONCRETE_POWDER, 1), ChatColor.YELLOW + "10"));
		qtd.setItem(3, ItemStackUtils.rename(new ItemStack(Material.BLUE_CONCRETE, 1), ChatColor.YELLOW + "25"));
		qtd.setItem(4, ItemStackUtils.rename(new ItemStack(Material.LIME_CONCRETE_POWDER, 1), ChatColor.YELLOW + "64"));
		qtd.setItem(5, ItemStackUtils.rename(new ItemStack(Material.LIME_CONCRETE, 1), ChatColor.YELLOW + "100"));
		qtd.setItem(6, ItemStackUtils.rename(new ItemStack(Material.GREEN_CONCRETE_POWDER, 1), ChatColor.YELLOW + "500"));
		qtd.setItem(7, ItemStackUtils.rename(new ItemStack(Material.GREEN_CONCRETE, 1), ChatColor.YELLOW + "1000"));
		if(deposito) {
			qtd.setItem(8, ItemStackUtils.rename(new ItemStack(Material.RED_CONCRETE, 1), ChatColor.RED + "TUDO"));
		}else {
			qtd.setItem(8, ItemStackUtils.rename(new ItemStack(Material.RED_CONCRETE, 1), ChatColor.RED + "MAXIMO"));
		}
	}
	
	public Inventory getInventory() {
		return qtd;
	}
	
	
}
