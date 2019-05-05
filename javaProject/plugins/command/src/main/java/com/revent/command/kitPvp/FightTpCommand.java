package com.revent.command.kitPvp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.ResultingCommandExecutor;
import io.github.jorelali.commandapi.api.arguments.DynamicSuggestedStringArgument;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.revent.returnstate.ReturnError;
import com.revent.types.ErrorType;
import org.bukkit.event.player.PlayerTeleportEvent;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

class FightTpCommand {
	private static ArrayList<String> startNamesArray = new ArrayList<>();

	private static HashMap<String, Location> startLocs = new HashMap<>();

	static DynamicSuggestedStringArgument startNames = new DynamicSuggestedStringArgument(() -> {
		//Returns a String[]
		return startNamesArray.toArray(new String[startNamesArray.size()]);
	});

	FightTpCommand() {
		//starting location init
		World kitPvp = Bukkit.getWorld("kitPvp");
		startingLocation("hoi", new Location(kitPvp, 100, 100, 100), FALSE);
		startingLocation("secret", new Location(kitPvp, 101, 100, 101), TRUE);//TODO: implement all the locations wanted
	}

	private static void startingLocation(String name, Location loc, Boolean secret) {
		if (!secret) {
			//if not a secret location add the name to the public list
			startNamesArray.add(name);
		}
		startLocs.put(name, loc);
	}
	static ResultingCommandExecutor executorName = (sender, args) -> {
		// check if sender is player
		if (!(sender instanceof Player)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
			return 0;
		}

		//get the required data
		Player player = (Player) sender;
		//make sure the player is in the correct world
		if (!(player.getWorld().getName().equals("kitPvp"))) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.wrongCMD, player.getLocale()));
			return 0;
		}
		//making sure the name is avaible
		String name = (String) args[0];
		if (!startLocs.containsKey(name)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.wrongCMD, player.getLocale()));
			return 0;
		}
		player.teleport(startLocs.get(name), PlayerTeleportEvent.TeleportCause.COMMAND);
		return 1;
	};

	static ResultingCommandExecutor executorRandom = (sender, args) -> {
		// check if sender is player
		if (!(sender instanceof Player)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
			return 0;
		}
		//get the required data
		Player player = (Player) sender;
		//make sure the player is in the correct world
		if (!(player.getWorld().getName().equals("kitPvp"))) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.wrongCMD, player.getLocale()));
			return 0;
		}

		//get a random location from the HashMap
		int random = new Random().nextInt(startLocs.size());
		Object[] values = startLocs.values().toArray();
		Location randomLoc = (Location) values[random];
		player.teleport(randomLoc, PlayerTeleportEvent.TeleportCause.COMMAND);
		return 1;
	};
}
