package com.github.argoninc.money.listener;

import java.util.ArrayList;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import com.github.argoninc.money.Principal;
import com.github.argoninc.money.command.Cooldown;
import com.github.argoninc.money.finance.Transations;
import com.github.argoninc.money.inventory.Banco;
import com.github.argoninc.money.inventory.Quantidade;
import com.github.argoninc.money.inventory.ShopInventory;
import com.github.argoninc.money.shop.Shop;
import com.github.argoninc.money.shop.ShopUtils;

public class VillagerListener implements Listener {
	public static ArrayList<Cooldown> cd = null;

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
		Player player = e.getPlayer();
		
		//Se for right click
		if (e.getRightClicked() != null) {
			
			Entity entity = e.getRightClicked();
			
			//Se não tiver em cooldown (1000ms)
			if (!isInCooldown(player, entity)) {
				String entityUUID = entity.getUniqueId().toString();

				// método para mostrar uuid da entidade
				if (entity.getType().equals(EntityType.VILLAGER) && player.isOp()
						&& (boolean) Principal.config.get("showVillagerUUID")) {
					System.out.println("Entidade: " + entityUUID);
				}

				// startar gui do banco
				String banqueiro = (String) Principal.config.get("bankUUID");
				if (entity.getType().equals(EntityType.VILLAGER) && entityUUID.equals(banqueiro)) {
					e.setCancelled(true);
					
					Inventory banco = new Banco(player).getInventory();
					openInventory(player, banco);
				}else if (entity.getType().equals(EntityType.VILLAGER) && ShopUtils.isShop(entityUUID)) {
					
					Villager villager = (Villager) entity;
					
					Shop s = ShopUtils.getShop(entityUUID);
					
					villager.setAdult();
					villager.setBreed(false);
					villager.setInvulnerable(true);
					villager.setVillagerLevel(5);
					villager.setProfession(Profession.valueOf(s.getPermissionKey()));
					villager.setRecipes(ShopInventory.getRecipes(s, true));
					
					
				}else if(entity.getType().equals(EntityType.VILLAGER)){
					Villager villager = (Villager) entity;
					villager.setProfession(Profession.NONE);
				}
				
			}
		}

	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		InventoryView iv = event.getView();
		Player player = (Player) event.getWhoClicked();
		
		//Se for a tela do banco
		if (iv.getTitle().startsWith("Banco de ")) {
			event.setCancelled(true);
			
			//SLOT absoluto, tela toda, sem repetir.
			int slot = event.getRawSlot();
			
			//Inicia o inventario de quantidade deposito/saque
			Quantidade qtd = null;
			
			//slot = 2 concreto vermelho: saque
			if (slot == 2) {
				
				//deposito: false
				qtd = new Quantidade(player, false);
				openInventory(player, qtd.getInventory());
				
			//slot = 6 concreto verde: deposito
			} else if (slot == 6) {
				
				//deposito: true
				qtd = new Quantidade(player, true);
				openInventory(player, qtd.getInventory());
			}
		}
		
		//Se for a tela de deposito
		if (iv.getTitle().startsWith("Deposito de ")) {
			event.setCancelled(true);
			
			//Starta a transação se o clique for no menu: true pq é deposito
			if (event.getRawSlot() < 9) {
				Transations.transacao(player, event.getRawSlot(), true);
			}
			
		}
		if (iv.getTitle().startsWith("Saque de ")) {
			event.setCancelled(true);

			//Starta a transação se o clique for no menu: false pq é saque
			if (event.getRawSlot() < 9) {
				Transations.transacao(player, event.getRawSlot(), false);
			}
		}

	}
	
	//Fecha um inventario e abre o outro
	private void openInventory(Player player, Inventory inventory) {
		player.closeInventory();
		player.openInventory(inventory);
	}
	
	
	//Método de cooldown
	public boolean isInCooldown(Player player, Entity entity) {
		for (int i = 0; i < cd.size(); i++) {
			Cooldown cooldown = cd.get(i);

			if (System.currentTimeMillis() - cooldown.getTime() > 1000) {
				cd.remove(cooldown);
			}
		}

		for (int i = 0; i < cd.size(); i++) {

			Cooldown cooldown = cd.get(i);

			if (cooldown.getPlayer().getUniqueId().equals(player.getUniqueId())
					&& cooldown.getEntity().getUniqueId().equals(entity.getUniqueId())) {
				return true;
			}
		}
		cd.add(new Cooldown(player, entity, System.currentTimeMillis()));
		return false;
	}

}
