package com.revent.command.allWorld;

import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.ResultingCommandExecutor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.revent.returnstate.ReturnError;
import com.revent.types.ErrorType;

class TpCommand {

	static ResultingCommandExecutor executorToLocation = (sender, args) -> {
		//make sure the sender is a player
		if (!(sender instanceof Player)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
			return 0;
		}
		//get the needed data
		Player player = (Player) sender;
		Location target = (Location) args[0];

		//teleport to a location
		player.teleport(target, TeleportCause.COMMAND);
		return 1;
	};

	static ResultingCommandExecutor executorToPlayer = (sender, args) -> {
		//make sure the sender is a player
		if (!(sender instanceof Player)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
			return 0;
		}
		//get the needed data
		Player player = (Player) sender;
		Player target = (Player) args[0];
		if (player.getWorld() != target.getWorld()) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.playerNotOn, "en_us"));
			return 0;
		}
		//teleport to a player
		player.teleport(target, TeleportCause.COMMAND);
		return 1;
	};

	static ResultingCommandExecutor executorPlayerToPlayer = (sender, args) -> {
		//get the needed data
		Player player = (Player) args[0];
		Player target = (Player) args[1];
		if (player.getWorld() != target.getWorld()) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.playerNotOn, "en_us"));
			return 0;
		}
		//teleport the target player to a difrent player
		player.teleport(target, TeleportCause.COMMAND);
		return 1;
	};

	static ResultingCommandExecutor executorPlayerToLocation = (sender, args) -> {
		//get the needed data
		Player player = (Player) args[0];
		Location target = (Location) args[1];

		//teleport the target player to a location
		player.teleport(target, TeleportCause.COMMAND);
		return 1;
	};
}
