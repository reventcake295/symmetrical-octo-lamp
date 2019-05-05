package com.revent.event.hub;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemClick implements Listener {

	@EventHandler
	public void onPlayerClicks(PlayerInteractEvent event) {
		//make sure the player is in the hub
		if (!(event.getPlayer().getWorld().getName().equals("world")))
			return;
		//get the required data
		Player player = event.getPlayer();
		Action action = event.getAction();

		// if the item is compass excecute command menu
		if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
			if (player.getInventory().getItemInMainHand().getType().equals(Material.COMPASS)) {
				player.performCommand("menu");
			}
		}

		if (!(player.hasPermission("revent.builder"))) {
			//only builder and up are allowed to interact with the hub
			event.setCancelled(true);
		}
	}
}
