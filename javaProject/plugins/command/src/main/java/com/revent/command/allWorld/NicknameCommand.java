package com.revent.command.allWorld;

import java.lang.reflect.Field;

import com.revent.CoreMain;
import com.revent.util.PlayerVisibility;
import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.ResultingCommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;

import net.minecraft.server.v1_13_R2.EntityPlayer;

import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;
import com.revent.returnstate.ReturnError;
import com.revent.returnstate.ReturnSucces;
import com.revent.types.ErrorType;
import com.revent.types.SuccesType;

class NicknameCommand {

	static ResultingCommandExecutor executorSetName = (sender, args) -> {
		// check if sender is player
		if (!(sender instanceof Player)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
			return 0;
		}

		// get all the data!
		Player player = (Player) sender;
		String newName = (String) args[0];

		//makesure no one else is using that name
		try {
			if (Bukkit.getPlayer(newName) != null || CoreMain.playerData.playerNicknamed.get(newName) != null) {
				CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.nameUse, player.getLocale()));
				return 0;
			}
		} catch (NullPointerException e) {
			if (CoreMain.playerData.playerNicknamed.get(newName) != null) {
				CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.nameUse, player.getLocale()));
				return 0;
			}
		}

		//change the new name acourding to the perm for yt'ers
		if (!(player.hasPermission("revent.YT"))) {
			newName = "~" + newName;
		}

		//store the data in the server data class
		CoreMain.playerData.playerNicknamed.put(newName, player);
		CoreMain.playerData.nicknamedPlayer.put(player, newName);

		//set the data up so it can be seen
		player.setDisplayName(newName);
		player.setPlayerListName(newName);

		//setting the name in the core of the player
		EntityPlayer ep = ((CraftPlayer) player).getHandle();
		//IChatBaseComponent newNameChatBase;
		//ep.setCustomName(newNameChatBase);
		ep.setCustomNameVisible(true);

		GameProfile profile = ep.getProfile();

		try {
			Field nome = profile.getClass().getDeclaredField("name");
			nome.setAccessible(true);
			nome.set(profile, newName);
		} catch (Exception ex) {
			CoreMain.getErrorWriter().println(
					"-----------------------------------------------ERROR.Commands:setNickname command.get Declared field 2");
			ex.printStackTrace(CoreMain.getErrorWriter());
		}

		//update the name tag and player list through disappaering and appearing
		PlayerVisibility.playerAppearWorld(player, player.getWorld());

		ReturnSucces.sendSuccesToPlayer(true, SuccesType.playerNickChanged, player);
		return 1;
	};

	static ResultingCommandExecutor executorResetName = (sender, args) -> {
		// check if sender is player
		if (!(sender instanceof Player)) {
			CommandAPI.fail(ReturnError.ErrorMessage(ErrorType.noPlayer, "en_us"));
			return 0;
		}

		// get all the data!
		Player player = (Player) sender;

		// so he wants to reset his name? sure
		CoreMain.playerData.playerNicknamed.remove(CoreMain.playerData.nicknamedPlayer.get(player));
		CoreMain.playerData.nicknamedPlayer.remove(player);
		String name = player.getName();

		// reseting the data
		player.setDisplayName(name);
		player.setPlayerListName(name);

		//reseting the data in the core of the player
		EntityPlayer ep = ((CraftPlayer) player).getHandle();
		ep.setCustomNameVisible(false);

		GameProfile profile = ep.getProfile();

		try {
			Field nome = profile.getClass().getDeclaredField("name");
			nome.setAccessible(true);
			nome.set(profile, name);
		} catch (Exception ex) {
			CoreMain.getErrorWriter().println(
					"-----------------------------------------------ERROR.command:resetNickname command.get Declared field");
			ex.printStackTrace(CoreMain.getErrorWriter());
		}

		//update the name tag and player list through disappaering and appearing
		PlayerVisibility.playerAppearWorld(player, player.getWorld());

		ReturnSucces.sendSuccesToPlayer(true, SuccesType.playerNickReset, player);
		return 1;
	};

}
