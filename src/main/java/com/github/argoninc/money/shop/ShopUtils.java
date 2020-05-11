package com.github.argoninc.money.shop;

import com.github.argoninc.money.Principal;

public class ShopUtils {
	public static boolean isShop(String entityUUID) {
		for (Shop s : Principal.shopList) {
			if(s.getUuid().equals(entityUUID)) {
				return true;
			}
		}
		return false;
	}
	
	public static Shop getShop(String entityUUID) {
		for (Shop s : Principal.shopList) {
			if(s.getUuid().equals(entityUUID)) {
				return s;
			}
		}
		return null;
		
	}
	
	public static void debug(Shop s) {
			System.out.println("----");
			System.out.println("UUID: "+s.getUuid());
			System.out.println("permissionKey: "+s.getPermissionKey());			
			System.out.println("itens: ");
			
			if(s.getItens()==null) {
				System.out.println("null");
			}else {
				for (int i = 0; i < s.getItens().length; i++) {
					System.out.println("["+i+"] ---- ");
					System.out.println("material: "+s.getItens()[i].getMaterial());
					System.out.println("amount: "+s.getItens()[i].getAmount());
					System.out.println("name: "+s.getItens()[i].getCustomName());
					System.out.println("price: "+s.getItens()[i].getPrice());
					System.out.println("amount_bonus: "+s.getItens()[i].getAmountBonus());
					System.out.println("price_bonus: "+s.getItens()[i].getPriceBonus());
					System.out.println("enchantments: ");
					
					if(s.getItens()[i].getEnchants()==null) {
						System.out.println("null");
					}else {
						for (int j = 0; j < s.getItens()[i].getEnchants().length; j++) {
							System.out.println("["+j+"] "+s.getItens()[i].getEnchants()[j].getKey()+" "+s.getItens()[i].getEnchants()[j].getLevel());
						}
					}
				}
			}
			
			System.out.println("----");
		
	}
}
