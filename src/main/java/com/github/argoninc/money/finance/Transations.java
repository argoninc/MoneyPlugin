package com.github.argoninc.money.finance;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.github.argoninc.money.Principal;

public class Transations {
	public static void transacao(Player player, int slot, boolean deposito) {
		String uuid = player.getUniqueId().toString();
		
		//value = valor que a pessoa deseja depositar/sacar
		int value = getValue(slot);
		
		//se value = 0 ele não faz nada
		if (value == 0) {
			
		//value é maior do que o saldo do player, não efetua transação
		} else if (value > getSaldoPlayer(uuid) && value != -1 && !deposito) {
			player.sendMessage(ChatColor.RED + "Voce nao tem essa quantidade.");
		
		//quando a transação for possivel
		} else {
			
			// value -1 é a opção TUDO/MAXIMO
			if (value == -1) {
				
				//compara deposito
				if (deposito) {
					depositar(player);
				} else {
					sacar(player);
				}
				
			//valores multiplos
			} else {
				if (deposito) {
					depositar(player, value);
				} else {
					sacar(player, value);
				}

			}

		}
	}

	
	private static void sacar(Player player, int value) {
		String uuid = player.getUniqueId().toString();
		
		//Conta a quantidade de slots que podera entrar as esmeraldas sacadas
		int slotsDisponiveis = countAvaiable(player.getInventory().getContents(), Material.EMERALD);
		
		//erro caso não tenha a quantidade de slots para sacar
		if (value > slotsDisponiveis) {
			player.sendMessage(
					ChatColor.RED + "Voce nao tem essa quantidade de espaco livre no inventario.");
		
		//caso de para sacar
		} else {
			
			//debita do saldo da pessoa a quantidade requisitada
			debit(player, value);
			
			//mensagem de sucesso
			player.sendMessage(ChatColor.GREEN + "Voce sacou " + value + " esmeralda" + plural(value) + "!");
			
			//add item no inventario
			add(player, Material.EMERALD, value);
			
			//mostra saldo
			mostrarSaldo(player, uuid);
		}
	}

	private static void sacar(Player player) {
		String uuid = player.getUniqueId().toString();
		
		//Conta a quantidade de slots que podera entrar as esmeraldas sacadas
		int slotsDisponiveis = countAvaiable(player.getInventory().getContents(), Material.EMERALD);
		
		//Se slots for maior que o saldo, logo caberá tudo no inventario
		if (slotsDisponiveis > getSaldoPlayer(uuid)) {
			
			//Valor a sacar vai ser todas esmeraldas do player
			int valorASacar = getSaldoPlayer(uuid);
			
			//Zera a conta do player
			Principal.banco.set(uuid, 0);
			
			//Mostra a mensagem de sucesso
			player.sendMessage(ChatColor.GREEN + "Voce sacou " + valorASacar + " esmeralda"+plural(valorASacar)+"!");
			
			//adiciona ao inventario as esmeraldas
			add(player, Material.EMERALD, valorASacar);
			
			//mostra o saldo
			mostrarSaldo(player, uuid);
			
		//se o numero de slots for maior que zero e não caberá tudo no inventario
		//sera sacado o que couber
		}else if (slotsDisponiveis > 0) {
			
			//valor a sacar será o tanto de slots disponiveis
			int valorASacar = slotsDisponiveis;
			
			//saldo é o saldo antigo - o valor a sacar
			int saldo = getSaldoPlayer(uuid) - valorASacar;
			
			//Set o saldo do banco
			Principal.banco.set(uuid, saldo);
			
			//mensagem de sucesso
			player.sendMessage(ChatColor.GREEN + "Voce sacou " + valorASacar + " esmeralda"+plural(valorASacar)+"!");
			
			//adiciona as esmeraldas no inventario do player
			add(player, Material.EMERALD, valorASacar);
			
			mostrarSaldo(player, uuid);
		}
	}


	private static void depositar(Player player) {
		String uuid = player.getUniqueId().toString();
		
		//
		int quantidadeEsmeraldas = count(player.getInventory().getContents(), Material.EMERALD);
		remove(player, Material.EMERALD, -1);
		Principal.banco.set(uuid, ((int) Principal.banco.get(uuid) + quantidadeEsmeraldas));
		player.sendMessage(
				ChatColor.GREEN + "Voce depositou todas suas esmeraldas! (" + quantidadeEsmeraldas + ")");
		player.sendMessage(ChatColor.GREEN + "Saldo: " + Principal.banco.get(uuid) + " esmeraldas.");
	}
	
	private static void depositar(Player player, int value) {
		String uuid = player.getUniqueId().toString();
		int quantidadeEsmeraldas = count(player.getInventory().getContents(), Material.EMERALD);

		if (quantidadeEsmeraldas < value) {
			player.sendMessage(ChatColor.RED + "Voce nao tem essa quantia.");
		} else {
			remove(player, Material.EMERALD, value);
			Principal.banco.set(uuid, ((int) Principal.banco.get(uuid) + value));

			String plural = "";
			if (value > 1) {
				plural += "s";
			}
			player.sendMessage(ChatColor.GREEN + "Voce depositou " + value + " esmeralda" + plural + "!");
			player.sendMessage(ChatColor.GREEN + "Saldo: " + Principal.banco.get(uuid) + " esmeraldas.");
		}
	}
	
	private static void mostrarSaldo(Player player, String uuid) {
		int saldo = getSaldoPlayer(uuid);
		player.sendMessage(ChatColor.GREEN + "Saldo: " + saldo + " esmeralda"+plural(saldo)+".");
	}
	
	private static void credit(Player p, int value) {
		String uuid = p.getUniqueId().toString();
		Principal.banco.set(uuid, (int) getSaldoPlayer(uuid) + value);
	}
	
	private static void debit(Player p, int value) {
		String uuid = p.getUniqueId().toString();
		Principal.banco.set(uuid, (int) getSaldoPlayer(uuid) - value);
	}
	
	private static int getSaldoPlayer(String uuid) {
		return (int) Principal.banco.get(uuid);
	}
	
	private static String plural(int value) {
		String plural = "";
		if (value != 1) {
			plural += "s";
		}
		return plural;
	}

	private static int getValue(int slot) {
		switch (slot) {
		case 0:
			return 1;
		case 1:
			return 5;
		case 2:
			return 10;
		case 3:
			return 25;
		case 4:
			return 50;
		case 5:
			return 100;
		case 6:
			return 500;
		case 7:
			return 1000;
		case 8:
			return -1;
		}
		return 0;
	}

	private static int count(ItemStack[] contents, Material m) {
		int i = 0;
		for (ItemStack itemStack : contents) {
			if (itemStack != null) {
				if (itemStack.getType().equals(m)) {
					i += itemStack.getAmount();
				}
			}

		}
		return i;
	}

	private static int countAvaiable(ItemStack[] contents, Material m) {
		int slots = 0;
		for (int i = 0; i < 36; i++) {
			ItemStack item = contents[i];

			if (item == null) {
				slots += 64;
			} else if (item.getType().equals(m)) {
				slots += 64 - item.getAmount();
			}
		}
		return slots;
	}

	private static void remove(Player player, Material m, int quantity) {
		ItemStack[] itens = player.getInventory().getContents();
		if (quantity == -1) {
			for (int i = 0; i < itens.length; i++) {
				if (itens[i] != null) {
					if (itens[i].getType().equals(Material.EMERALD)) {
						itens[i] = null;
					}
				}

			}
		} else {
			for (int i = 0; i < itens.length; i++) {
				if (itens[i] != null) {
					if (quantity == 0) {
						break;
					}
					if (itens[i].getType().equals(Material.EMERALD)) {
						if (itens[i].getAmount() <= quantity) {
							quantity -= itens[i].getAmount();
							itens[i] = null;
						} else if (itens[i].getAmount() > quantity) {
							itens[i].setAmount(itens[i].getAmount() - quantity);
							quantity = 0;
						}
					}
				}

			}
		}
		player.getInventory().setContents(itens);
		player.updateInventory();
	}

	private static void add(Player player, Material m, int quantidade) {
		ItemStack[] itens = player.getInventory().getContents();

		int esmeraldas = quantidade;

		for (int i = 0; i < 36; i++) {
			if (quantidade == 0) {
				break;
			}

			if (itens[i] == null) {
				if (esmeraldas > 64) {
					esmeraldas -= 64;
					itens[i] = new ItemStack(m, 64);
				} else {
					itens[i] = new ItemStack(m, esmeraldas);
					esmeraldas = 0;
				}
			} else if (itens[i].getType().equals(m)) {
				int avaiableSlots = 64 - itens[i].getAmount();
				int qtdAtual = itens[i].getAmount();

				if (avaiableSlots > 0) {
					if (avaiableSlots > esmeraldas) {
						itens[i] = new ItemStack(m, qtdAtual + esmeraldas);
						esmeraldas = 0;
					} else if (avaiableSlots == esmeraldas) {
						itens[i] = new ItemStack(m, 64);
						esmeraldas = 0;
					} else {
						itens[i] = new ItemStack(m, 64);
						esmeraldas -= avaiableSlots;
					}
				}
			}
		}

		player.getInventory().setContents(itens);
		player.updateInventory();
	}
}
