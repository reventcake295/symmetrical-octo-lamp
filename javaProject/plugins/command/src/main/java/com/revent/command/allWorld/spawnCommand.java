package com.revent.command.allWorld;

import com.revent.CoreMain;
import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.ResultingCommandExecutor;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.revent.returnstate.ReturnError;
import com.revent.returnstate.ReturnSucces;
import com.revent.types.ErrorType;
import com.revent.types.SuccesType;

class spawnCommand {

	static ResultingCommandExecutor executor = (sender, args) -> {

		// check if sender is player
		if (!(sender instanceof Player)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
			return 0;
		}
		//get the required data
		Player player = (Player) sender;

		player.teleport(CoreMain.worldData.worldSpawnLocations.get(player.getWorld().getName()), TeleportCause.COMMAND);
		ReturnSucces.sendSuccesToPlayer(true, SuccesType.TPSpawn, player);
		return 1;
	};
}
