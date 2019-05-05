package com.revent.command.allWorld;

import com.revent.CoreMain;
import io.github.jorelali.commandapi.api.ResultingCommandExecutor;
import org.bukkit.entity.Player;

class Excecutor {

	static ResultingCommandExecutor executorCommand = (sender, args) -> {

		//get the required data
		Player target = (Player)args[0];
		String Command = (String) args[1];

		// excecute the command
		target.performCommand(Command);
		return 1;
	};

	static ResultingCommandExecutor executorMessage = (sender, args) -> {

		//get the required data
		Player target = (Player)args[0];
		String message = (String) args[1];

		// call the chat manager
		CoreMain.cM.chatMessage(target, message, true, sender.toString());
		return 1;
	};

}
