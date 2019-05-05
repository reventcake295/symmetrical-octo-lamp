package com.revent.event.multiWorld;

import com.revent.managers.sieveManager;
import com.revent.util.InventoryUtils;
import com.revent.util.MaterialGroups;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dispenser;

import java.util.Arrays;
import java.util.List;

import static java.lang.Boolean.FALSE;

public class sieve implements Listener {

	private final List<String> worlds = Arrays.asList("skyblock", "");

	@EventHandler
	public void DispenserEvent(BlockDispenseEvent event) {
		//make sure everything is correct
		if (!(worlds.contains(event.getBlock().getWorld().getName()) &&
				event.getBlock().getType().equals(Material.DROPPER) &&
				event.getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.DIAMOND_BLOCK)))
			return;


		//get the block face
		Block dropperBlock = event.getBlock();
		if (!(event.getBlock().getState().getData() instanceof Dispenser))
			return;
		BlockFace Blockface = ((Dispenser) event.getBlock().getState().getData()).getFacing();

		if (!(dropperBlock.getRelative(Blockface).getType().equals(Material.IRON_TRAPDOOR) &&
				dropperBlock.getRelative(Blockface).getRelative(BlockFace.DOWN).getType().equals(Material.CHEST)))
			return;

		//get the item
		try {
			event.getItem();
		} catch (NullPointerException ex) {
			return;
		}
		ItemStack item = event.getItem();

		//make sure the item is one of the possible items
		if (!(sieveManager.ItemsIron.contains(item.getType().toString())))
			return;
		//get the resulting inventory
		BlockState resultChestState = dropperBlock.getRelative(Blockface).getRelative(BlockFace.DOWN).getState();
		if (!(resultChestState instanceof Chest))
			return;
		Inventory resultInventory = ((Chest) resultChestState).getBlockInventory();

		//get the item and remove it from the inventory
		ItemStack item1 = new ItemStack(item.getType(), 1);
		InventoryUtils.removeItem(((Dropper)event.getBlock().getState()).getInventory(), item1, FALSE);

		//set the item to air
		event.setItem(new ItemStack(Material.AIR));

		//call the sieve manager
		sieveManager.sieve(item1, resultInventory, dropperBlock.getRelative(Blockface));

	}

	@EventHandler
	public void ItemSieve(PlayerInteractEvent event) {
		//make sure that every thing is correct
		if (!(worlds.contains(event.getPlayer().getWorld().getName()) &&
				event.getAction() == Action.RIGHT_CLICK_BLOCK &&
				event.getClickedBlock().getLocation().subtract(0, 1, 0).getBlock().getType().equals(Material.CHEST) &&
				MaterialGroups.TRAPDOOR.contains(event.getClickedBlock().getType().toString())))
			return;

		//get the item
		String itemString;
		try {
			itemString = event.getItem().getType().toString();
		} catch (NullPointerException ex) {
			return;
		}

		//make sure that for the sieve used the item in the correct range is detected
		if (MaterialGroups.WOODTRAPDOOR.contains(event.getClickedBlock().getType().toString())) {
			if (!(sieveManager.ItemsWood.contains(itemString)))
				return;
		} else if (!(sieveManager.ItemsIron.contains(itemString)))
				return;

		//get the inventory of the chest
		BlockState state = event.getClickedBlock().getLocation().subtract(0, 1, 0).getBlock().getState();
		if (!(state instanceof Chest))
			return;
		Inventory invChest = ((Chest) state).getBlockInventory();

		//get the item used and then remove the item from the inventory
		ItemStack item = event.getItem();
		InventoryUtils.removeItem(event.getPlayer().getInventory(), item, FALSE);

		//cancel the event because we know for certain that we have output
		event.setCancelled(true);

		//call to the sieve method
		sieveManager.sieve(item, invChest, event.getClickedBlock());
	}
}
