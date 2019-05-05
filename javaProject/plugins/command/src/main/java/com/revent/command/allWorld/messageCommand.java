package com.revent.command.allWorld;

import com.revent.CoreMain;
import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.ResultingCommandExecutor;
import org.bukkit.entity.Player;
import com.revent.returnstate.ReturnError;
import com.revent.types.ErrorType;

class messageCommand {

	static ResultingCommandExecutor executor = (sender, args) -> {
		// check if sender is player
		if (!(sender instanceof Player)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
			return 0;
		}
		//getting and setting all the data
		Player player = (Player) sender;
		Player reciever = (Player)args[0];
		CoreMain.playerData.setReplyTarget(player, reciever);
		String message = (String) args[1];

		// send both sender and reciever the message
		player.sendMessage("&6>>>" + reciever.getName() + ": &f" + message);
		reciever.sendMessage("&6<<<" + player.getName() + ": &f" + message);
		return 1;
		//TODO: implement chat tracking and spy system throug events or just method calls?
	};

}
