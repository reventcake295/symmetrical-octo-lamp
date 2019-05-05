package com.revent.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.revent.CoreMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.revent.returnstate.ReturnError;
import com.revent.types.ErrorType;

public class ChatManager {

	private final CoreMain plugin;

	public ChatManager(CoreMain coreMain) {
		plugin = coreMain;
	}

	public void chatMessage(Player player, String message, boolean fake, String faker) {

		// test if the config has the list whit blocked words
		if (!plugin.getConfig().contains("Blocked-Words")) {

			// create the list of blocked words and store it in the config

			List<String> BlockedWords = new ArrayList<>();
			BlockedWords.add("kanker");
			plugin.getConfig().set("Blocked-Words", BlockedWords);
			plugin.saveConfig();
		}

		// test if the message contains words from the blocked list

		String messageLower = message.toLowerCase();
		List<String> blockedWords = plugin.getConfig().getStringList("Blocked-Words");

		for (String blockedWord : blockedWords) {
			if (messageLower.contains(blockedWord)) {
                ReturnError.sendErrorToPlayer(false, ErrorType.badWord, player);
                return;
			}
		}

		ArrayList<Player> WorldPlayers = new ArrayList<>();
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (all.getWorld() == player.getWorld()) {
				WorldPlayers.add(all);
			} // get the players that are in the same world as the player that
		} // send the message

		for (Player worldPlayer : WorldPlayers) {
			worldPlayer.sendMessage("<" + player.getDisplayName() + "> " + message);
		}
		Logger logger = Bukkit.getLogger();
		if (!fake) { // if it was send by the chatChannel class
			
			logger.info("[" + player.getWorld().getName() + "]<" + player.getDisplayName() + ">" + message);

        } else { // if it was send by the excecutor command class
			logger.info("{FAKED BY:" + faker + "}[" + player.getWorld().getName() + "]<" + player.getDisplayName() + ">" + message);
        }
	}
}
