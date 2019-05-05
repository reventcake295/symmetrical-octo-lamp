package com.revent.event.multiWorld;

import com.revent.managers.composterManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.material.Dispenser;

import java.util.Arrays;
import java.util.List;

public class composter implements Listener {

	private final List<String> worlds = Arrays.asList("skyblock", "");//the worlds this function is allowed to work

	@SuppressWarnings("deprecation")
	@EventHandler
	public void ItemSieve(PlayerInteractEvent event) {
		//make sure that everythng is correct

		if (!(worlds.contains(event.getPlayer().getWorld().getName()) &&
				event.getAction() == Action.RIGHT_CLICK_BLOCK) &&
				event.getClickedBlock().getLocation().add(0, 1, 0).getBlock().getType().equals(Material.CHEST) &&
				event.getClickedBlock().getType().equals(Material.CAULDRON))
			return;

		int x = event.getClickedBlock().getLocation().getBlockX();
		int z = event.getClickedBlock().getLocation().getBlockZ();

		//make sure we have direct acces to skylight
		for (int y = event.getClickedBlock().getLocation().getBlockY(); y < 256; y++) {
			Location loc = new Location(event.getPlayer().getWorld(), x, y, z);
			if (!(loc.getBlock().getType().isTransparent())) {
				return;
			}
		}
		//try to get the inv chest
		BlockState state = event.getClickedBlock().getLocation().add(0, 1, 0).getBlock().getState();
		if (!(state instanceof Chest))
			return;
		Inventory invChest = ((Chest) state).getBlockInventory();

		//try to get the item
		try {
			event.getItem();
		} catch (NullPointerException E){
			return;
		}
		ItemStack item = new ItemStack(event.getItem().getType(), 1);

		//call the method for the composter
		composterManager.cauldron(item, invChest, event.getPlayer().getInventory());
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void DispenserEvent(BlockDispenseEvent event) {
		//make sure we are in one of the correct worlds and we the block is a dropper
		if (!(worlds.contains(event.getBlock().getWorld().getName()) && event.getBlock().getType().equals(Material.DROPPER)))
			return;

		//get the blockface of the dropper
		Block DispenserBlock = event.getBlock();
		if (!(event.getBlock().getState().getData() instanceof Dispenser))
			return;
		BlockFace Blockface = ((Dispenser) event.getBlock().getState().getData()).getFacing();

		//make sure the block above is a emerald block
		if (!(event.getBlock().getRelative(BlockFace.UP).getType().equals(Material.EMERALD_BLOCK)))
			return;

		//make sure the base structure of the composter is bieng faced
		if (!(DispenserBlock.getRelative(Blockface).getType().equals(Material.CAULDRON) && DispenserBlock.getRelative(Blockface).getRelative(BlockFace.UP).getType().equals(Material.CHEST)))
			return;



		//make sure there is clear skylight acces
		int x = DispenserBlock.getRelative(Blockface).getLocation().getBlockX();
		int z = DispenserBlock.getRelative(Blockface).getLocation().getBlockZ();

		for (int y = DispenserBlock.getRelative(Blockface).getLocation().getBlockY(); y < 256; y++) {
			Material loc = new Location(event.getBlock().getWorld(), x, y, z).getBlock().getType();
			if (!(loc.isTransparent())) {
				return;
			}
		}
		//get the chest from the composter
		BlockState state1 = DispenserBlock.getRelative(Blockface).getRelative(BlockFace.UP).getState();
		if (!(state1 instanceof Chest))
			return;
		Inventory invChest = ((Chest) state1).getBlockInventory();

		//try to get the item it can be that there is no item
		try {
			event.getItem();
		} catch (NullPointerException ex){
			return;
		}

		ItemStack item = new ItemStack(event.getItem().getType(), 1);

		//get the inventory of the dropper
		BlockState state = DispenserBlock.getState();
		if (!(state instanceof Container))
			return;
		Inventory invDropper = ((Container) state).getInventory();

		//now we are almost certain that there will be a output
		event.setCancelled(true);

		//call the method for the composter
		composterManager.cauldron(item, invChest, invDropper);
	}
}
