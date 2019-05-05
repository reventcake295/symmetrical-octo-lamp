package com.revent.command.allWorld;

import com.revent.returnstate.ReturnError;
import com.revent.types.ErrorType;
import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.ResultingCommandExecutor;
import org.bukkit.World;
import org.bukkit.entity.Player;

class Rulescomm {

	static ResultingCommandExecutor executor = (sender, args) -> {
		// check if sender is player
		if (!(sender instanceof Player)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
			return 0;
		}
		//get the required data
		Player player = (Player) sender;
		World world = player.getWorld();
		//rules list
		//algemene regels
		player.sendMessage("dit zijn de regels die gelden waar u op dit moment bent:");
		player.sendMessage("    - niet schelden.");
		player.sendMessage("    - niet spammen.");
		player.sendMessage("    - geen ongepaste bewegingen.");

		//rules based on permissions
		if(player.hasPermission("revent.builder")) { // builders stuff
			player.sendMessage("    - geen command blocks gebruik maakt niet uit hoe.");
			player.sendMessage("    - geen items of blocks gebruiken dat de mogelijk heden heeft tot teleportatie behalve anders gespecificeerd.");
		}

		//rules based on worlds
		if (world.getName().equals("survival")) { // survival stuff
			player.sendMessage("    - geen seksueele of discriminerende bouwsels.");

		}
		return 1;
		//TODO: create a menu with the rules?
	};

}
