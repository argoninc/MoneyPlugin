package com.github.argoninc.money.shop;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.argoninc.job.user.Job;

public class Item {
	private boolean buy;
	private String material;
	private int amount;
	private int amount_bonus;
	private Enchant[] enchants;
	private int price;
	private int price_bonus;
	private String custom_name;
	
	private Job[] bonusJob;
	private Job[] canBuy;
	
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
	
	public Job[] getCanBuy() {
		return canBuy;
	}
	
	public Job[] getBonusJob() {
		return bonusJob;
	}
	
	public int getPriceBonus() {
		return price_bonus;
	}

	public ItemStack getItemStack() {
		return is;
	}

	public int getAmount() {
		return amount;
	}

	public int getAmountBonus() {
		return amount_bonus;
	}
	
	public Enchant[] getEnchants() {
		return enchants;
	}

	public String getCustomName() {
		return custom_name;
	}
	
	public Item(String material, int amount, Enchant[] enchants, String custom_name, int price, boolean buy, int price_bonus, int amount_bonus, Job[] bonusJob, Job[] canBuy) {
		this.material = material;
		this.amount = amount;
		this.enchants = enchants;
		this.custom_name = custom_name;
		this.price = price;
		this.buy = buy;
		this.price_bonus = price_bonus;
		this.amount_bonus = amount_bonus;
		this.canBuy = canBuy;
		this.bonusJob = bonusJob;
		
		
		ItemStack i = new ItemStack(Material.getMaterial(material), amount);
		
		ItemMeta im = i.getItemMeta();
		
		if(custom_name!=null) {
			im.setDisplayName(custom_name);
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
