package com.github.argoninc.money.shop;

public class Shop {
	private String uuid;
	private Item[] itens;
	private String permissionKey;
	private String name;
	
	public Shop(String uuid, Item[] itens, String permissionKey, String name) {
		this.uuid = uuid;
		this.itens = itens;
		this.permissionKey = permissionKey;
		this.name = name;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public String getName() {
		return name;
	}
	public Item[] getItens() {
		return itens;
	}
	public String getPermissionKey() {
		return permissionKey;
	}

}
