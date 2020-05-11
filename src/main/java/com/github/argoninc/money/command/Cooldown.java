package com.github.argoninc.money.command;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Cooldown {
	private Player player;
	private Entity entity;
	private long time;
	public Cooldown(Player player, Entity entity, long time) {
		this.player = player;
		this.entity = entity;
		this.time = time;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
}
