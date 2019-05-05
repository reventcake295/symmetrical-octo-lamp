package com.revent.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStackUtil {

	@SuppressWarnings({ "unchecked" })
	public static ItemStack nameItem(Material item, String name, String lore) {
		ItemStack itemStack = new ItemStack(item);
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(name);
		List<String> ListLore = new ArrayList();
		ListLore.add(lore);
		meta.setLore(ListLore);

		itemStack.setItemMeta(meta);

		return itemStack;
	}
}
