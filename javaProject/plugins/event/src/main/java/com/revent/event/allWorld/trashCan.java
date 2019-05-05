package com.revent.event.allWorld;

import org.bukkit.Bukkit;
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

public class trashCan implements Listener {

	@EventHandler
	public static void playerTrashCan(PlayerInteractEvent event) {//for if the player wants to trash something

		// check of de stuff correct is
		if (!(event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType().equals(Material.WALL_SIGN)))
			return;

		//get the clicked sign
		Block signBlock = event.getClickedBlock();

		// check of the sign a [prullenbak] or a [trash can] is
		Sign sign = (Sign) signBlock.getState();
		if (!(sign.getLine(1).equals("[prullenbak]"))) {
			// make de inventory and open it to the player
			event.getPlayer().openInventory(Bukkit.createInventory(null, 36, "prullenbak"));

		} else if (sign.getLine(1).equals("[trash can]")) {
			// make de inventory and open it to the player
			event.getPlayer().openInventory(Bukkit.createInventory(null, 36, "trash can"));
		}
	}

	@EventHandler
	public static void autotrashCan(BlockDispenseEvent event) {//for an automatic trash can
		//make sure we are dealing with a dropper
		if (!(event.getBlock().getType().equals(Material.DROPPER)))
			return;

		//get the direction the block is facing
		Block DispenserBlock = event.getBlock();
		if (!(event.getBlock().getState().getData() instanceof Dispenser))
			return;
		BlockFace Blockface = ((Dispenser) event.getBlock().getState().getData()).getFacing();

		//make sure the block faced is a sign
		if (event.getBlock().getRelative(Blockface).getType().equals(Material.WALL_SIGN)) {
			//get the sign and make sure it is either a [prullenbak] or a [trashcan]
			Sign sign = (Sign) event.getBlock().getRelative(Blockface).getState();
			if (!(sign.getLine(1).equals("[prullenbak]") || sign.getLine(1).equals("[trash can]")))
				return;
			//try to get the item can be that nothing is dispensed
			try {
				event.getItem();
			} catch (NullPointerException Ex) {
				return;//if nothing is there then why continue?
			}
			//get the final required data
			ItemStack item = new ItemStack(event.getItem().getType(), 1);
			BlockState state = DispenserBlock.getState();
			if (!(state instanceof Container))
				return;
			Container dropper = (Container) state;
			Inventory invDropper = dropper.getInventory();

			//cancel the event and remove the item
			event.setCancelled(true);
			invDropper.removeItem(item);
		}
	}
}
