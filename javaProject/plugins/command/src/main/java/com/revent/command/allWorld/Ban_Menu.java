package com.revent.command.allWorld;

import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.ResultingCommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.revent.returnstate.ReturnError;
import com.revent.types.ErrorType;
import com.revent.util.ItemStackUtil;

class Ban_Menu {

	static ResultingCommandExecutor executorIp = (sender, args) -> {
		// check if sender is player
		if (!(sender instanceof Player)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
			return 0;
		}
		//get the required data
		Player player = (Player) sender;
		//create and open the inventory whit the predifined ban options
		Inventory inv = BanMenu(Bukkit.createInventory(null, 18, "Ban ip: " + args[1]));
		player.openInventory(inv);
		return 1;
		
	};

	static ResultingCommandExecutor executorPlayer = (sender, args) -> {
		// check if sender is player
		if (!(sender instanceof Player)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
			return 0;
		}
		//get the required data
		Player player = (Player) sender;
		//create and open the inventory with the predifined ban options
		Inventory inv = BanMenu(Bukkit.createInventory(null, 18, "Ban player: " + args[1]));
		player.openInventory(inv);
		return 1;

	};

	private static Inventory BanMenu(Inventory inv) {
		//top row for all kins of hacking
		inv.setItem(0, new ItemStack(ItemStackUtil.nameItem(Material.BEDROCK, "perm ban", "perm ban de hacker")));
		inv.setItem(1, new ItemStack(ItemStackUtil.nameItem(Material.DIAMOND_SWORD, "PVP", "voor alle pvp hackers")));
		inv.setItem(2, new ItemStack(ItemStackUtil.nameItem(Material.DIAMOND_ORE, "X-RAY", "ban de X-ray hacker")));
		inv.setItem(3, new ItemStack(ItemStackUtil.nameItem(Material.ARROW, "spam", "veel berichten in chat sturen achter elkaar")));
		inv.setItem(4, new ItemStack(ItemStackUtil.nameItem(Material.EMERALD, "scam", "mensen oplichten")));
		inv.setItem(5, new ItemStack(ItemStackUtil.nameItem(Material.BRICK, "inapropiate builds", "niet toegestane gebouwen")));
		inv.setItem(6, new ItemStack(ItemStackUtil.nameItem(Material.SIGN, "inapropiate skin/IGN", "verkeerde skin of naam")));
		inv.setItem(7, new ItemStack(ItemStackUtil.nameItem(Material.BARRIER, "schelden", "slecht woord gebruik")));
		inv.setItem(8, new ItemStack(ItemStackUtil.nameItem(Material.RED_BED, "other", "overig")));

		//bottom row for the time bans whitout the known reason
		inv.setItem(9, new ItemStack(ItemStackUtil.nameItem(Material.GREEN_WOOL, "1 uur ban", "green")));
		inv.setItem(10, new ItemStack(ItemStackUtil.nameItem(Material.LIME_WOOL, "4 uur ban", "lime")));
		inv.setItem(11, new ItemStack(ItemStackUtil.nameItem(Material.YELLOW_WOOL, "8 uur ban", "yellow")));
		inv.setItem(12, new ItemStack(ItemStackUtil.nameItem(Material.ORANGE_WOOL, "1 dag ban", "orange")));
		inv.setItem(13, new ItemStack(ItemStackUtil.nameItem(Material.BLUE_WOOL, "1 week ban", "blue")));
		inv.setItem(14, new ItemStack(ItemStackUtil.nameItem(Material.BROWN_WOOL, "1 month ban", "brown")));
		inv.setItem(15, new ItemStack(ItemStackUtil.nameItem(Material.GRAY_WOOL, "2 month ban", "dark brown")));
		inv.setItem(16, new ItemStack(ItemStackUtil.nameItem(Material.RED_WOOL, "6 month ban", "red")));
		inv.setItem(17, new ItemStack(ItemStackUtil.nameItem(Material.MAGENTA_WOOL, "1 jaar ban", "dark red")));
		return inv;
	}
}
