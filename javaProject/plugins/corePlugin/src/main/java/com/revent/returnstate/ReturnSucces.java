package com.revent.returnstate;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.revent.CoreMain;
import com.revent.serverData.StaticData;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.revent.types.SuccesType;

/**
 * The Class ReturnSucces.
 */
public class ReturnSucces {

	/**
	 * Send error to sender. <br>
	 * according to the SuccesType enum
	 * 
	 * @author reventcake295
	 * @param state     the state that must be return
	 * @param SuccesType the succes type that must be send
	 * @param sender    the sender the message is getting sended to
	 * @return equals the param state
	 */
	public static boolean sendSuccesToSender(boolean state, SuccesType SuccesType, CommandSender sender) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			player.sendMessage(SuccesMessage(SuccesType, player.getLocale()));
			return state;
		} else {
			sender.sendMessage(SuccesMessage(SuccesType, "en_us"));
			return state;
		}
	}

	/**
	 * Send error to player. <br>
	 * according to the SuccesType enum
	 * 
	 * @author reventcake295
	 * @param state      the state that must be return
	 * @param SuccesType the succes type that must be send
	 * @param player     the player the message is getting sended to
	 * @return equals the param state
	 */
	public static boolean sendSuccesToPlayer(boolean state, SuccesType SuccesType, Player player) {
		player.sendMessage(SuccesMessage(SuccesType, player.getLocale()));
		return state;
	}

	/**
	 * Error message.
	 *
	 * @param SuccesType the Succes type
	 * @param lang      the lanquage it must be send in
	 * @return the string whit the error
	 */
	public static String SuccesMessage(SuccesType SuccesType, String lang) {
		String message = null;
		File langfile = new File(StaticData.langData + lang + ".lang");
		
		if (!langfile.exists()) 
			langfile = new File("./locale/en_us.lang");
		
		try (Scanner scanner = new Scanner(langfile)) {

			while (scanner.hasNextLine()) {
				String langLine = scanner.nextLine();
				if (langLine.startsWith('"' + SuccesType.toString())) {
					message = langLine.replaceAll('"' + SuccesType.toString() + "=", "").replaceAll('"' + "", "");
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace(CoreMain.getErrorWriter());
		}
		assert message != null;
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}
