package com.revent.command.allWorld;

import io.github.jorelali.commandapi.api.ResultingCommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

class Broadcast {

	static ResultingCommandExecutor executor = (sender, args) -> {
		String message = (String) args[0];

		// send all player the broadcast message 3 times
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.sendMessage(
					"[" + ChatColor.DARK_RED + "BROADCAST" + ChatColor.RESET + "] " + ChatColor.RED + message);
			player.sendMessage(
					"[" + ChatColor.DARK_RED + "BROADCAST" + ChatColor.RESET + "] " + ChatColor.RED + message);
			player.sendMessage(
					"[" + ChatColor.DARK_RED + "BROADCAST" + ChatColor.RESET + "] " + ChatColor.RED + message);
		}
		return 1;
	};

}
