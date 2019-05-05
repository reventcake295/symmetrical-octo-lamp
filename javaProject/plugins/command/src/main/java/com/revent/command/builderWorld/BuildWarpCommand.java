package com.revent.command.builderWorld;

import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.ResultingCommandExecutor;
import org.bukkit.entity.Player;

import com.revent.mysql.BuilderWarpData;
import com.revent.returnstate.ReturnError;
import com.revent.returnstate.ReturnSucces;
import com.revent.types.ErrorType;
import com.revent.types.SuccesType;

class BuildWarpCommand {

	static ResultingCommandExecutor executorSectieList = (sender, args) -> {
		// check if sender is player
		if (!(sender instanceof Player)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
			return 0;
		}
		//get the required data
		Player player = (Player) sender;
		//make sure the player is in the builderworld
		if (!(player.getWorld().getName().equals("builderWorld"))) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.wrongCMD, player.getLocale()));
			return 0;
		}
		//send the sectie list to the player
		BuilderWarpData.sendSectieList(player);
		return 1;
	};
	static ResultingCommandExecutor executorCreateWarp = (sender, args) -> {
		// check if sender is player
		if (!(sender instanceof Player)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
			return 0;
		}
		//get the required data
		Player player = (Player) sender;
		//make sure the player is in the builderworld
		if (!(player.getWorld().getName().equals("builderWorld"))) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.wrongCMD, player.getLocale()));
			return 0;
		}
		//create a warp in the selected sectie
		BuilderWarpData.createWarpPlayer(player, (String) args[1], (String) args[0]);
		ReturnSucces.sendSuccesToPlayer(true, SuccesType.WarpCreated, player);
		return 1;
	};

	static ResultingCommandExecutor executorWarpList = (sender, args) -> {
		// check if sender is player
		if (!(sender instanceof Player)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
			return 0;
		}
		//get the required data
		Player player = (Player) sender;
		//make sure the player is in the builderworld
		if (!(player.getWorld().getName().equals("builderWorld"))) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.wrongCMD, player.getLocale()));
			return 0;
		}
		//send the list of warps in the selected sectie
		BuilderWarpData.sendWarpList(player, (String)args[0]);
		return 1;
	};

	static ResultingCommandExecutor executorCreateSectie = (sender, args) -> {
		// check if sender is player
		if (!(sender instanceof Player)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
			return 0;
		}
		//get the required data
		Player player = (Player) sender;
		//make sure the player is in the builderworld
		if (!(player.getWorld().getName().equals("builderWorld"))) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.wrongCMD, player.getLocale()));
			return 0;
		}
		//create sectie
		BuilderWarpData.createSectie((String) args[1], player);
		ReturnSucces.sendSuccesToPlayer(true, SuccesType.sectieCreated, player);
		return 1;
	};

	static ResultingCommandExecutor executorTeleportToWarp = (sender, args) -> {
		// check if sender is player
		if (!(sender instanceof Player)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
			return 0;
		}
		//get the required data
		Player player = (Player) sender;
		//make sure the player is in the builderworld
		if (!(player.getWorld().getName().equals("builderWorld"))) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.wrongCMD, player.getLocale()));
			return 0;
		}
		//teleport the player to the warp selected
		BuilderWarpData.warpPlayer(player, (String)args[0], (String)args[1]);
		return 1;
	};
}
