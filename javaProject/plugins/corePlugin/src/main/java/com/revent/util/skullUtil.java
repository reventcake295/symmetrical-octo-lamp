package com.revent.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class skullUtil {

	/**
	 * create a player head
	 * @param playerUUIDString the UUID of the player of wich the head is wanted
	 * @return player head
	 */
	public static ItemStack getSkull(String playerUUIDString) {
		ItemStack head = new ItemStack(Material.PLAYER_HEAD);

		UUID playerUUID = UUID.fromString(playerUUIDString);

		SkullMeta headMeta = (SkullMeta) head.getItemMeta();

		headMeta.setOwningPlayer(Bukkit.getOfflinePlayer(playerUUID));

		head.setItemMeta(headMeta);

		return head;
	}
}
