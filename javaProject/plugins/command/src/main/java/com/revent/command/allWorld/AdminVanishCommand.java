package com.revent.command.allWorld;

import com.revent.CoreMain;
import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.ResultingCommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.revent.returnstate.ReturnError;
import com.revent.returnstate.ReturnSucces;
import com.revent.types.ErrorType;
import com.revent.types.SuccesType;


class AdminVanishCommand {

	@SuppressWarnings("deprecation")
	static ResultingCommandExecutor executor = (sender, args) -> {
			// check is sender is player
		if (!(sender instanceof Player)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
			return 0;
		}
		Player player = (Player) sender;

		// if already vanish appear!
		if (CoreMain.playerData.vanished.contains(player)) {
			for (Player playerWorld : Bukkit.getOnlinePlayers()) {
				playerWorld.showPlayer(player);
				CoreMain.playerData.vanished.remove(player);
			}
			ReturnSucces.sendSuccesToPlayer(true, SuccesType.visable, player);
			return 1;

			// if not then hide!
		} else {
			for (Player playerWorld : Bukkit.getOnlinePlayers()) {
				playerWorld.hidePlayer(player);
			}
			for (Player vanish : CoreMain.playerData.vanished) {
				vanish.showPlayer(player);
			}
			CoreMain.playerData.vanished.add(player);
			ReturnSucces.sendSuccesToPlayer(true, SuccesType.invisble, player);
			return 1;
		}
	};
}
