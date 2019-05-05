package com.revent.util;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryUtils {

	// removes itemStack from inventory
	// returns the amount of items it could not remove
	public static int removeItem(Inventory inventory, ItemStack itemStack, Boolean force) {
		if (itemStack.getAmount() <= 0)
			return 0;
		ItemStack[] contents = inventory.getContents();
		int amount = itemStack.getAmount();
        for (ItemStack is : contents) {
            if (is != null) {
                if (is.isSimilar(itemStack)) {
                    if (is.getAmount() > amount) {
                        is.setAmount(is.getAmount() - amount);
                        inventory.setContents(contents);
                        return 0;
                    } else if (is.getAmount() == amount) {
                        is.setType(Material.AIR);
                        inventory.setContents(contents);
                        return 0;
                    } else {
                    	if (force) {
							amount -= is.getAmount();
							is.setType(Material.AIR);
						} else {
                    		return amount - is.getAmount();
						}
                    }
                }
            }
        }
		inventory.setContents(contents);
		return amount;
	}

	// takes an ItemStack and splits it up into multiple ItemStacks with correct
	// stack sizes
	// then adds those items to the given inventory
	public static int addItem(Inventory inventory, ItemStack itemStack) {
		if (itemStack.getAmount() <= 0)
			return 0;
		ArrayList<ItemStack> itemStacksAdding = new ArrayList<>();

		// break up the itemStack into multiple ItemStacks with correct stack size
		int fullStacks = itemStack.getAmount() / itemStack.getMaxStackSize();
		int partialStack = itemStack.getAmount() % itemStack.getMaxStackSize();
		for (int i = 0; i < fullStacks; i++) {
			ItemStack is = itemStack.clone();
			is.setAmount(is.getMaxStackSize());
			itemStacksAdding.add(is);
		}
		
		ItemStack is = itemStack.clone();
		is.setAmount(partialStack);
		if (partialStack > 0)
			itemStacksAdding.add(is);

		// try adding all items from itemStacksAdding and return number of ones you
		// could not add
		int amount = 0;
		for (ItemStack addItem : itemStacksAdding) {
			HashMap<Integer, ItemStack> noAdd = inventory.addItem(addItem);
			amount += noAdd.size();
		}
		return amount;
	}

	public static void addArrayItems(Player player,ArrayList<ItemStack> items, Boolean dropOnGround) {
		//TODO: make this function

		// use addItem in for loop for this to make it compacter
		// if an number other than 0 is returned drop it on the ground in front of the player
	}
}
