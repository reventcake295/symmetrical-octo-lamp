package com.revent.command.allWorld;

import com.revent.returnstate.ReturnError;
import com.revent.returnstate.ReturnSucces;
import com.revent.types.ErrorType;
import com.revent.types.SuccesType;
import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.ResultingCommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

class EffectCommand {

	static ResultingCommandExecutor executorSelf = (sender, args) -> {
		//get the required data
		PotionEffectType effect = (PotionEffectType) args[0];
		int time = 20*(int) args[1];
		int strength = (int) args[2];
		PotionEffect potion = new PotionEffect(effect, time, strength, false, (Boolean) args[3]);

		//make sure the sender is a player
		if(sender instanceof Player) {
			potion.apply((Player) sender);
			ReturnSucces.sendSuccesToSender(true, SuccesType.EffectGiven, sender);
			return 1;
		} else {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
			return 0;
		}
		//the command as used in this function
		// /effect [effect] [time] [strength] [visable]
	};

	static ResultingCommandExecutor executorOtherPlayer = (sender, args) -> {

		//get the required data
		PotionEffectType effect = (PotionEffectType) args[0];
		int time = 20*(int) args[1];
		int strength = (int) args[2];
		Player target = (Player) args[4];
		PotionEffect potion = new PotionEffect(effect, time, strength, false, (Boolean) args[3]);

		//appply the effect to the target
		potion.apply(target);
		ReturnSucces.sendSuccesToSender(true, SuccesType.EffectGiven, sender);
		return 1;

		//the command as used in this function
		// /effect [effect] [time] [strength] [visable] [player]

	};

}
  