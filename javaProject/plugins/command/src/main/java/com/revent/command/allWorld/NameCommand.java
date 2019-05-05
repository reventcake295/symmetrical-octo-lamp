package com.revent.command.allWorld;

import com.revent.CoreMain;
import io.github.jorelali.commandapi.api.ResultingCommandExecutor;
import org.bukkit.entity.Player;

import com.revent.returnstate.ReturnError;
import com.revent.returnstate.ReturnSucces;
import com.revent.types.ErrorType;
import com.revent.types.SuccesType;

class NameCommand {

	static ResultingCommandExecutor executor = (sender, args) -> {

		// get player and make sure he is online
		String STarget = (String) args[0];
		if (CoreMain.playerData.playerNicknamed.get(STarget) == null) {
			ReturnError.sendErrorToSender(true, ErrorType.playerNotOn, sender);
			return 0;
		}

		//get the data about the targeted player
		Player target = CoreMain.playerData.playerNicknamed.get(STarget);
		STarget = CoreMain.playerData.nicknamedPlayer.get(target);

		//send the data to the player
		sender.sendMessage(ReturnSucces.SuccesMessage(SuccesType.playerNameName, "en_us").replace("%p", target.getDisplayName()));
		sender.sendMessage(ReturnSucces.SuccesMessage(SuccesType.playerNameNick, "en_us").replace("%p", STarget));
		return 1;

	};
}
