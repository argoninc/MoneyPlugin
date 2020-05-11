package com.github.argoninc.money.shop;

public class Enchant {
	private String key;
	private int level;
	public Enchant(String key, int level) {
		this.key = key;
		this.level = level;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
}
