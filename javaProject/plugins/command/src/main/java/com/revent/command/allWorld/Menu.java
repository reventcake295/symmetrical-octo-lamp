package com.revent.command.allWorld;

import com.revent.CoreMain;
import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.ResultingCommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.revent.returnstate.ReturnError;
import com.revent.returnstate.ReturnSucces;
import com.revent.types.ErrorType;
import com.revent.types.SuccesType;
import com.revent.util.ItemStackUtil;


class Menu {

	static ResultingCommandExecutor executor = (sender, args) -> {
		// check if sender is player
		if (!(sender instanceof Player)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
			return 0;
		}

		// getting and setting all needed data
		Player player = (Player) sender;
		Inventory inv = Bukkit.createInventory(null, 54, "Menu");
		String lang = player.getLocale();

		//building the menu TODO: await ui design of grijzejagerwill7
		inv.setItem(0, CoreMain.worldData.worldItem.get("hub"));
		inv.setItem(1, CoreMain.worldData.worldItem.get("skyblock"));
		inv.setItem(2, CoreMain.worldData.worldItem.get("minetopia"));
		inv.setItem(3, CoreMain.worldData.worldItem.get("prison"));
		inv.setItem(4, CoreMain.worldData.worldItem.get("kitpvp"));
		inv.setItem(5, CoreMain.worldData.worldItem.get("factions"));
		inv.setItem(6, CoreMain.worldData.worldItem.get("dungeoncrawler"));
		inv.setItem(7, CoreMain.worldData.worldItem.get("creative"));
		inv.setItem(8, CoreMain.worldData.worldItem.get("islandsurvival"));

		// if player has perm set the item for builderWorld
		if (player.hasPermission("revent.builder.BuilderWorldTeleport")) {
			inv.setItem(53, CoreMain.worldData.worldItem.get("buidlerworld"));
		}
		player.openInventory(inv);
		return 1;
	};

	
}
