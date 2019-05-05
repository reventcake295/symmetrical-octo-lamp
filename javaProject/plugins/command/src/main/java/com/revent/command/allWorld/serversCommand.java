package com.revent.command.allWorld;

import com.revent.CoreMain;
import com.revent.managers.WorldTeleportManager;
import com.revent.returnstate.ReturnError;
import com.revent.types.ErrorType;
import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.ResultingCommandExecutor;
import org.bukkit.entity.Player;

class serversCommand {

	static ResultingCommandExecutor executorNormal = (sender, args) -> {
		//make sure the sender is a player
		if (!(sender instanceof Player)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
			return 0;
		}
		//get the required data
		Player player = (Player) sender;
		String targetWorld = (String) args[0];

		//give the list fo servers currently availeble
		if (targetWorld.equals("server")) {
			player.sendMessage("servers op dit moment online:");
			player.sendMessage(CoreMain.worldData.WorldNames.toArray(new String[CoreMain.worldData.WorldNames.size()]));
			return 1;
			// if the target is the builder world make sure the player has the perm to do so
		} else if (targetWorld.equals("builderworld")) {
			if (player.hasPermission("revent.builder.BuilderWorldTeleport")) {
				CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPerm, "en_us"));
				return 0;
			}
		}
		//fail if the world is not listed in the server data class
		if (!CoreMain.worldData.worlds.containsKey(targetWorld)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.wrongCMD, player.getLocale()));
			return 0;
		}
		//fail is the world is closed due to some reason
		if (CoreMain.worldData.worldClosed.get(targetWorld)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.worldClosed, player.getLocale()));
			return 0 ;
		}
		//teleport the player to the targeted world
		WorldTeleportManager.WorldTeleport(player, CoreMain.worldData.worldSpawnLocations.get(targetWorld));
		return 1;
	};

	static ResultingCommandExecutor executorForce = (sender, args) -> {
		//make sure the sender is a player
		if (!(sender instanceof Player)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
			return 0;
		}
		//get the required data
		Player player = (Player) sender;
		String targetWorld = (String) args[0];

		//give the list fo servers currently availeble
		if (targetWorld.equals("server")) {
			player.sendMessage("servers op dit moment online:");
			player.sendMessage(CoreMain.worldData.WorldNames.toArray(new String[CoreMain.worldData.WorldNames.size()]));
			return 1;
			// if the target is the builder world make sure the player has the perm to do so
		} else if (targetWorld.equals("builderworld")) {
			if (player.hasPermission("revent.builder.BuilderWorldTeleport")) {
				CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPerm, "en_us"));
				return 0;
			}
		}
		//fail if the world is not listed in the server data class
		if (!CoreMain.worldData.serverWorlds.contains(targetWorld)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.wrongCMD, "en_us"));
			return 0;
		}

		//fail is the world is closed due to some reason and the player has said false (why the hell would you choose false do nothing then its shorter command structure)
		if (CoreMain.worldData.worldClosed.get(targetWorld)) {
			if (!(boolean) args[1]) {
				CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.worldClosed, player.getLocale()));
				return 0;
			}
		}
		//teleport the player to the targeted world
		WorldTeleportManager.WorldTeleport(player, CoreMain.worldData.worldSpawnLocations.get(targetWorld));
		return 1;
	};
}
