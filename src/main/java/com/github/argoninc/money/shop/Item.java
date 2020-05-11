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
	private String[] enchantments;
	private int[] levels;
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



	public String[] getEnchantments() {
		return enchantments;
	}



	public void setEnchantments(String[] enchantments) {
		this.enchantments = enchantments;
	}



	public int[] getLevels() {
		return levels;
	}



	public void setLevels(int[] levels) {
		this.levels = levels;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Item(String material, int amount, String[] enchantments, int[] levels, String name, int price, boolean buy) {
		this.material = material;
		this.amount = amount;
		this.enchantments = enchantments;
		this.levels = levels;
		this.name = name;
		this.price = price;
		this.buy = buy;
		
		ItemStack i = new ItemStack(Material.getMaterial(material), amount);
		
		ItemMeta im = i.getItemMeta();
		
		if(name!=null) {
			im.setDisplayName(name);
		}
		
		if(enchantments!=null) {
			for (int j = 0; j < enchantments.length; j++) {
				Enchantment enchantment = EnchantmentWrapper.getByKey(NamespacedKey.minecraft(enchantments[j]));
				im.addEnchant(enchantment, levels[j], false);
			}
		}
		
		i.setItemMeta(im);
		
		this.is = i;
	}
	
	
}
