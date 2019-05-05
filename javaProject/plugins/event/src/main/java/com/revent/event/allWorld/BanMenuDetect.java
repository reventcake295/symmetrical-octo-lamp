package com.revent.event.allWorld;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BanMenuDetect implements Listener {

	@EventHandler
	public void onPlayerClickOnItem(InventoryClickEvent event) {
		if (event.getRawSlot() == event.getSlot() && event.getView().getTitle().startsWith("Ban")) {
			Player Player = (Player) event.getWhoClicked();
			if (event.getView().getTitle().startsWith("Ban player:")) {
				String target = event.getView().getTitle().replaceAll("Ban player: ", "");
				switch (event.getSlot()) {//ban a player based on player name
				case 0:
					Player.performCommand("ban " +target + " -s U bent voorgoed verbannen van de server!");
					break;

				case 1:
					Player.performCommand("tempban " + target + " -s (tijd) U bent verbannen voor pvp hacks");
					break;

				case 2:
					Player.performCommand("tempban " + target + " -s (tijd) verbannen voor x-ray!");
					break;

				case 3:
					Player.performCommand("tempmute " + target + " -s (tijd) te veel berichten");
					break;

				case 4:
					Player.performCommand("tempban " + target + " -s (tijd) verbannen vanwege oplichting van mesen");
					break;

				case 5:
					Player.performCommand(
							"tempban " + target + " -s (tijd) verbannen voor verkeerde gebouwen!");
					break;

				case 6:
					Player.performCommand("ban " + target
							+ " -s verbannen voor foute skin of naam! ban appeal op de forums nadat je dat verandert hebt!");
					break;

				case 7:
					Player.performCommand("tempmute " + target + " -s (tijd) gemute voor schelden!");
					break;

				case 8:
					Player.performCommand("tempban " + target + " -s (tijd) verbannen voor onbekend!");
					break;

				case 9:
					Player.performCommand("tempban " + target + " -s 1h verbannen voor 1 uur!");
					break;

				case 10:
					Player.performCommand("tempban " + target + " -s 4h verbannen voor 4 uur!");
					break;

				case 11:
					Player.performCommand("tempban " + target + " -s 8h verbannen voor 8 uur!");
					break;

				case 12:
					Player.performCommand("tempban " + target + " -s 1d verbannen voor 1 dag!");
					break;

				case 13:
					Player.performCommand("tempban " + target + " 7d verbannen voor een week!");
					break;

				case 14:
					Player.performCommand("tempban " + target + " -s 1mo verbannen voor 1 maand!");
					break;

				case 15:
					Player.performCommand("tempban " + target + " -s 2mo verbannen voor 2 maanden!");
					break;

				case 16:
					Player.performCommand("tempban " + target + " -s 6mo verbannen voor 6 maanden!");
					break;

				case 17:
					Player.performCommand("tempban " + target + " -s 1y verbannen voor 1 jaar!");
					break;
				}

			} else if (event.getView().getTitle().startsWith("Ban ip:")) {
				
				Player target = Bukkit.getPlayer(event.getView().getTitle().replaceAll("Ban ip: ", ""));
				switch (event.getSlot()) {//ban a player based on ip
				case 0:
					Player.performCommand("banip " +target + " -s U bent voorgoed verbannen van de server!");
					break;

				case 1:
					Player.performCommand("tempbanip " + target + " -s (tijd) U bent verbannen voor pvp hacks");
					break;

				case 2:
					Player.performCommand("tempbanip " + target + " -s (tijd) verbannen voor x-ray!");
					break;

				case 3:
					Player.performCommand("tempmuteip " + target + " -s (tijd) te veel berichten");
					break;

				case 4:
					Player.performCommand("tempbanip " + target + " -s (tijd) verbannen vanwege oplichting van mesen");
					break;

				case 5:
					Player.performCommand(
							"tempbanip " + target + " -s (tijd) verbannen voor verkeerde gebouwen!");
					break;

				case 6:
					Player.performCommand("banip " + target
							+ " -s verbannen voor foute skin of naam! ban appeal op de forums nadat je dat verandert hebt!");
					break;

				case 7:
					Player.performCommand("tempmuteip " + target + " -s (tijd) gemute voor schelden!");
					break;

				case 8:
					Player.performCommand("tempbanip " + target + " -s (tijd) verbannen voor onbekend!");
					break;

				case 9:
					Player.performCommand("tempbanip " + target + " -s 1h verbannen voor 1 uur!");
					break;

				case 10:
					Player.performCommand("tempbanip " + target + " -s 4h verbannen voor 4 uur!");
					break;

				case 11:
					Player.performCommand("tempbanip " + target + " -s 8h verbannen voor 8 uur!");
					break;

				case 12:
					Player.performCommand("tempbanip " + target + " -s 1d verbannen voor 1 dag!");
					break;

				case 13:
					Player.performCommand("tempbanip " + target + " 7d verbannen voor een week!");
					break;

				case 14:
					Player.performCommand("tempbanip " + target + " -s 1mo verbannen voor 1 maand!");
					break;

				case 15:
					Player.performCommand("tempbanip " + target + " -s 2mo verbannen voor 2 maanden!");
					break;

				case 16:
					Player.performCommand("tempbanip " + target + " -s 6mo verbannen voor 6 maanden!");
					break;

				case 17:
					Player.performCommand("tempbanip " + target + " -s 1y verbannen voor 1 jaar!");
					break;		
					}
			} else
				return;

			event.setCancelled(true);
			Player.closeInventory();
		}
	}
}
