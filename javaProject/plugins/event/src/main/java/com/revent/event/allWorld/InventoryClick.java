package com.revent.event.allWorld;

import com.revent.CoreMain;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {

		if (!event.getView().getTitle().equals("Menu")) // check if it is the correct menu
			return;
		event.setCancelled(true);

		ItemStack item;
		try {//it can happen that there is a nullpointer somehow
			item = event.getCurrentItem();
		} catch (NullPointerException ex) {
			return;
		}
		//make sure that the item currently in the hand is a item that is in the list of world currently avaible
		if (!CoreMain.worldData.itemWorld.containsKey(item))
			return;
		//getting the player
		Player player = (Player) event.getWhoClicked();
		//close the inventory because we wont be needing it anymore
		player.closeInventory();
		//excecute the server command with the world name stored in the itemWorld hashmap
		player.performCommand("server " + CoreMain.worldData.itemWorld.get(item));
	}
}
