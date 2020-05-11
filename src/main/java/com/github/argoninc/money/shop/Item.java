package com.github.argoninc.money.shop;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {
	private boolean buy;
	private String material;
	private int amount;
	private Enchant[] enchants;
	private int price;
	private String name;
	
	private ItemStack is;
	
	
	public String getMaterial() {
		return material;
	}
	
	public boolean isBuy() {
		return buy;
	}
	
	public int getPrice() {
		return price;
	}

	public ItemStack getItemStack() {
		return is;
	}

	public void setMaterial(String material) {
		this.material = material;
	}



	public int getAmount() {
		return amount;
	}



	public void setAmount(int amount) {
		this.amount = amount;
	}



	public Enchant[] getEnchants() {
		return enchants;
	}



	public void setEnchants(Enchant[] enchants) {
		this.enchants = enchants;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Item(String material, int amount, Enchant[] enchants, String name, int price, boolean buy) {
		this.material = material;
		this.amount = amount;
		this.enchants = enchants;
		this.name = name;
		this.price = price;
		this.buy = buy;
		
		ItemStack i = new ItemStack(Material.getMaterial(material), amount);
		
		ItemMeta im = i.getItemMeta();
		
		if(name!=null) {
			im.setDisplayName(name);
		}
		
		if(enchants!=null) {
			for (int j = 0; j < enchants.length; j++) {
				Enchantment enchantment = EnchantmentWrapper.getByKey(NamespacedKey.minecraft(enchants[j].getKey()));
				im.addEnchant(enchantment, enchants[j].getLevel(), false);
			}
		}
		
		i.setItemMeta(im);
		
		this.is = i;
	}
	
	
}
