package com.revent.event.multiWorld;

import com.revent.CoreMain;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class KitSelect implements Listener {

	@EventHandler
	public void onPlayerClickOnItem(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		if (!(CoreMain.worldData.kitWorld.contains(event.getWhoClicked().getWorld().getName()) &&
				CoreMain.worldData.kitsName.get(player.getWorld().getName()).containsKey(event.getCurrentItem()) &&
				event.getRawSlot() == event.getSlot() && event.getView().getTitle().endsWith(" kits") &&
				player.hasPermission(CoreMain.worldData.kitsPermission.get(player.getWorld().getName()).get(event.getCurrentItem()))))
			return;
		event.setCancelled(true);
		player.closeInventory();
		player.performCommand("kit " + CoreMain.worldData.kitsName.get(player.getWorld().getName()).get(event.getCurrentItem()));
	}
}
