package com.github.argoninc.money.shop;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.github.argoninc.job.user.Job;
import com.github.argoninc.money.Principal;

public class ShopLoader {
	public static Shop[] getShops(){
		ArrayList<Shop> shops = new ArrayList<Shop>();
		
		JSONArray arrayShops = (JSONArray) Principal.shop.get("shops");
		
		Iterator<Object> i = arrayShops.iterator();
		
		while(i.hasNext()) {
			Shop s = null;
			
			String name = null;
			String type = null;
			String uuid = null;
			String permissionKey = null;
			Item[] itens = null;
			
			JSONObject json = (JSONObject) i.next();
			
			uuid = (String) getHas(json, "uuid");
			name = (String) getHas(json, "name");
			type = (String) getHas(json, "type");
			permissionKey = (String) getHas(json, "permissionKey");
			
			ArrayList<Item> itemArray = new ArrayList<Item>();
			
			JSONArray temp = json.getJSONArray("itens");
			Iterator<Object> it = temp.iterator();
			
			while(it.hasNext()) {
				Item item = null;
				
				String material = null;
				int amount = 1;
				int price = 0;
				boolean buy = false;
				
				String custom_name = null;
				
				int price_bonus = 0;
				int amount_bonus = 0;
				
				Enchant[] enchants = null;
				int[] levels = null;
				
				Job[] canBuy = null;
				Job[] bonusJob = null;
 				
				JSONObject temp2 = (JSONObject) it.next();
				
				material = (String) getHas(temp2, "material");
				amount = getHasInt(temp2, "amount");
				price = getHasInt(temp2, "price");
				price_bonus = getHasInt(temp2, "price_bonus");
				amount_bonus = getHasInt(temp2, "amount_bonus");
				custom_name = (String) getHas(temp2, "name");
				buy = (boolean) getHas(temp2, "buy");
				
				
				//enchant
				ArrayList<Enchant> enchantsList = new ArrayList<Enchant>();
				
				JSONArray temp3 = (JSONArray) getHas(temp2, "enchants");
				if(temp3!=null) {
					Iterator<Object> ite = temp3.iterator();
					
					while(ite.hasNext()) {
						JSONObject jso = (JSONObject) ite.next();
						enchantsList.add(new Enchant(jso.getString("key"),jso.getInt("level")));
					}
					
					enchants = enchantsList.toArray(new Enchant[0]);
				}
				
				
				//bonusjob
				ArrayList<Job> jobList = new ArrayList<Job>();
				
				temp3 = (JSONArray) getHas(temp2, "bonusJob");
				if(temp3!=null) {
					Iterator<Object> ite = temp3.iterator();
					
					while(ite.hasNext()) {
						jobList.add(Job.getJobFromKey((String) ite.next()));
					}
					
					bonusJob = jobList.toArray(new Job[0]);
				}
				
				//canbuy
				jobList = new ArrayList<Job>();
				
				temp3 = (JSONArray) getHas(temp2, "canBuy");
				if(temp3!=null) {
					Iterator<Object> ite = temp3.iterator();
					
					while(ite.hasNext()) {
						jobList.add(Job.getJobFromKey((String) ite.next()));
					}
					
					canBuy = jobList.toArray(new Job[0]);
				}
				
				levels = convert(getArrayInt(temp2, "levels"));
				
				item = new Item(material, amount, enchants, custom_name, price, buy, price_bonus, amount_bonus, bonusJob, canBuy);
				
				itemArray.add(item);
			}
			
			itens = itemArray.toArray(new Item[0]);
			
			s = new Shop(uuid, itens, permissionKey, name, type);
			
			shops.add(s);
			
		}
		
		return shops.toArray(new Shop[0]);
	}
	
	private static int getHasInt(JSONObject json, String key) {
		if(json.has(key)) {
			return (int) json.get(key);
		}
		return 0;
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
