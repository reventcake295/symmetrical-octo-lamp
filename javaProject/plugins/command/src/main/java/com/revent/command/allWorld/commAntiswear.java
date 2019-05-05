package com.revent.command.allWorld;

import java.util.ArrayList;
import java.util.List;

import com.revent.CoreMain;
import io.github.jorelali.commandapi.api.ResultingCommandExecutor;
import org.bukkit.ChatColor;

import org.bukkit.configuration.file.FileConfiguration;

class commAntiswear {

	static ResultingCommandExecutor executorAdd = (sender, args) -> {
		FileConfiguration config = configInit();
		//add a word to the list
		List<String> BlockedWords = config.getStringList("Blocked-Words");
		BlockedWords.add(((String)args[0]).toLowerCase());
		config.set("Blocked-Words", BlockedWords);
		CoreMain.saveConfigFile();
		sender.sendMessage(ChatColor.GREEN + "woord toegevoegd");
		return 1;
	};

	static ResultingCommandExecutor executorRemove = (sender, args) -> {
		FileConfiguration config = configInit();
		//remove a word from the list
		List<String> BlockedWords1 = config.getStringList("Blocked-Words");
		BlockedWords1.remove(((String)args[1]).toLowerCase());
		config.set("Blocked-Words", BlockedWords1);
		CoreMain.saveConfigFile();
		sender.sendMessage(ChatColor.GREEN + "woord verwijderd");
		return 1;
	};

	private static FileConfiguration configInit() {
		FileConfiguration config = CoreMain.getConfigFile();
		// create the blocked words list in the config if it does not exist
		if (!config.contains("Blocked-Words")) {
			List<String> BlockedWords = new ArrayList<>();
			BlockedWords.add("kanker");
			config.set("Blocked-Words", BlockedWords);
			CoreMain.saveConfigFile();
		}
		//return the config file so the class can use it
		return config;
	}
}

