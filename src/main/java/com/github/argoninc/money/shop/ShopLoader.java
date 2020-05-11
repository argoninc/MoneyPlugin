package com.github.argoninc.money.shop;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.github.argoninc.money.Principal;

public class ShopLoader {
	public static Shop[] getShops(){
		ArrayList<Shop> shops = new ArrayList<Shop>();
		
		JSONArray arrayShops = (JSONArray) Principal.shop.get("shops");
		
		Iterator<Object> i = arrayShops.iterator();
		
		while(i.hasNext()) {
			Shop s = null;
			
			
			String uuid = null;
			String permissionKey = null;
			Item[] itens = null;
			
			JSONObject json = (JSONObject) i.next();
			
			uuid = (String) getHas(json, "uuid");
			permissionKey = (String) getHas(json, "permissionKey");
			
			ArrayList<Item> itemArray = new ArrayList<Item>();
			
			JSONArray temp = json.getJSONArray("itens");
			Iterator<Object> it = temp.iterator();
			
			while(it.hasNext()) {
				Item item = null;
				
				String material = null;
				int amount = 1;
				String name = null;
				int price = 0;
				boolean buy = false;
				
				String[] enchantments = null;
				int[] levels = null;
				
				JSONObject temp2 = (JSONObject) it.next();
				
				material = (String) getHas(temp2, "material");
				amount = (int) getHas(temp2, "amount");
				price = (int) getHas(temp2, "price");
				name = (String) getHas(temp2, "name");
				buy = (boolean) getHas(temp2, "buy");
				
				enchantments = getArrayString(temp2, "enchantments");
				levels = convert(getArrayInt(temp2, "levels"));
				
				item = new Item(material, amount, enchantments, levels, name, price, buy);
				
				itemArray.add(item);
			}
			
			itens = itemArray.toArray(new Item[0]);
			
			s = new Shop(uuid, itens, permissionKey);
			
			shops.add(s);
			
		}
		
		return shops.toArray(new Shop[0]);
	}
	
	private static String[] getArrayString(JSONObject json, String string) {
		ArrayList<String> arr = new ArrayList<String>();
		
		if(!json.has(string)) {
			return null;
		}
		
		JSONArray temp = json.getJSONArray(string);
		
		Iterator<Object> i = temp.iterator();
		
		while(i.hasNext()) {
			String n = (String) i.next();
			arr.add(n);
		}
		
		if(arr.size()>0) {
			return arr.toArray(new String[0]);
		}
		return null;
		
	}
	
	private static Integer[] getArrayInt(JSONObject json, String string) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		
		if(!json.has(string)) {
			return null;
		}
		
		JSONArray temp = json.getJSONArray(string);
		
		Iterator<Object> i = temp.iterator();
		
		while(i.hasNext()) {
			Integer n = (int) i.next();
			arr.add(n);
		}
		
		if(arr.size()>0) {
			return arr.toArray(new Integer[0]);
		}
		return null;
		
	}
	
	private static int[] convert(Integer[] arr) {
		if(arr==null) {
			return null;
		}
		
		int[] ints = new int[arr.length];
		
		for (int i = 0; i < arr.length; i++) {
			ints[i] = arr[i].intValue();
		}
		
		return ints;
		
	}

	private static Object getHas(JSONObject json , String key) {
		if(json.has(key)) {
			return json.get(key);
		}
		return null;
	}
}
