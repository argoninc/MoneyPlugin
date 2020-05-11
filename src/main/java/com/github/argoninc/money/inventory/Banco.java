package com.github.argoninc.money.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.github.argoninc.money.Principal;
import com.github.argoninc.money.utils.InventoryUtils;
import com.github.argoninc.money.utils.ItemStackUtils;

public class Banco {
	private Inventory banco;
	
	public Banco(Player player) {
		String uuid = player.getUniqueId().toString();
		banco = Bukkit.createInventory(null, 9, "Banco de " + player.getName());

		ItemStack saldo = new ItemStack(Material.EMERALD, 1);
		banco.setItem(4, ItemStackUtils.rename(saldo, ChatColor.DARK_GREEN + "Saldo: " + Principal.banco.get(uuid) + " esmeraldas."));

		ItemStack sacar = new ItemStack(Material.RED_CONCRETE, 1);
		banco.setItem(2, ItemStackUtils.rename(sacar, ChatColor.RED + "Sacar"));

		ItemStack depositar = new ItemStack(Material.GREEN_CONCRETE, 1);
		banco.setItem(6, ItemStackUtils.rename(depositar, ChatColor.GREEN + "Depositar"));
		
		banco = InventoryUtils.autoFill(banco, ItemStackUtils.rename(new ItemStack(Material.IRON_BARS, 1), "-"));
	}
	
	public Inventory getInventory() {
		return banco;
	}
}
