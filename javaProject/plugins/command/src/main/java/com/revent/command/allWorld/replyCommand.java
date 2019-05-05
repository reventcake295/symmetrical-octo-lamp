package com.revent.command.allWorld;

import com.revent.CoreMain;
import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.ResultingCommandExecutor;
import org.bukkit.entity.Player;

import com.revent.returnstate.ReturnError;
import com.revent.types.ErrorType;

class replyCommand {

    static ResultingCommandExecutor executor = (sender, args) -> {
        //make sure the sender is a player
        if (!(sender instanceof Player)) {
            CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
            return 0;
        }
        //get the required data and make sure everything is correct
        Player player = (Player) sender;
        if(CoreMain.playerData.getReplyTarget(player) == null){
            CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noChat, player.getLocale()));
            return 0;
        }
        Player reciever = CoreMain.playerData.getReplyTarget(player);
        String message = (String) args[0];

        // send both sender and reciever the message
        player.sendMessage("&6>>>" + reciever.getName() + ": &f" + message);
        reciever.sendMessage("&6<<<" + player.getName() + ": &f" + message);
        return 1;
        //TODO: implement chat tracking and spy system throug events or just method calls?
	};

}
