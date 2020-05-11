package com.github.argoninc.money.shop;

public class Shop {
	private String uuid;
	private Item[] itens;
	private String permissionKey;
	private String name;
	private String type;
	
	public Shop(String uuid, Item[] itens, String permissionKey, String name, String type) {
		this.uuid = uuid;
		this.itens = itens;
		this.permissionKey = permissionKey;
		this.name = name;
		this.type = type;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	public Item[] getItens() {
		return itens;
	}
	public String getPermissionKey() {
		return permissionKey;
	}

}
