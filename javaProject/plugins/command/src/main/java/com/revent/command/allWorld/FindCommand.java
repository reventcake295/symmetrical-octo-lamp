package com.revent.command.allWorld;

import com.revent.CoreMain;
import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.ResultingCommandExecutor;
import org.bukkit.entity.Player;

import com.revent.returnstate.ReturnError;
import com.revent.returnstate.ReturnSucces;
import com.revent.types.ErrorType;
import com.revent.types.SuccesType;

class FindCommand {

	static ResultingCommandExecutor executor = (sender, args) -> {

		if (!(sender instanceof Player)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
			return 0;
		}
		// get all data
		Player target = (Player) args[0];
		Player player = (Player) sender;

		if (target == null) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.playerNotOn, player.getLocale()));
			return 0;
		}
		String wordlName = CoreMain.worldData.worldName.get(target.getWorld().getName());

		// getting if the target is vanish or not
		boolean vanish = CoreMain.playerData.vanished.contains(target);

		// if else statement around vanish perm en vanish
		if (sender.hasPermission("revent.ranked.vanish") && vanish) {
			// player has access to vanish so he sees that he is vanish in a world
			player.sendMessage(ReturnSucces.SuccesMessage(SuccesType.playerFindVanish, player.getLocale().replace("%w", wordlName)));
			return 1;

		} else if (!(sender.hasPermission("revent.ranked.vanish")) && vanish ) {
			// player has no access to vanish so its faked that he is not online
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.playerNotOn, player.getLocale()));
			return 0;

		} else {
			// player gets to see where the target is
			player.sendMessage(ReturnSucces.SuccesMessage(SuccesType.playerFind, player.getLocale().replace("%w", wordlName)));
			return 1;

		}
	};

}
