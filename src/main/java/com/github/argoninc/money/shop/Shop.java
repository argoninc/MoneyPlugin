package com.github.argoninc.money.shop;

public class Shop {
	private String uuid;
	private Item[] itens;
	private String permissionKey;
	
	public Shop(String uuid, Item[] itens, String permissionKey) {
		this.uuid = uuid;
		this.itens = itens;
		this.permissionKey = permissionKey;
	}
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Item[] getItens() {
		return itens;
	}
	public void setItens(Item[] itens) {
		this.itens = itens;
	}
	public String getPermissionKey() {
		return permissionKey;
	}
	public void setPermissionKey(String permissionKey) {
		this.permissionKey = permissionKey;
	}

}
