package com.revent.returnstate;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.revent.CoreMain;
import com.revent.serverData.StaticData;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.revent.types.ErrorType;

/**
 * The Class ReturnError.
 */
public class ReturnError {

	/**
	 * Send error to sender. <br>
	 * according to the ErrorType enum
	 * 
	 * @author reventcake295
	 * @param state     the state that must be return
	 * @param ErrorType the error type that must be send
	 * @param sender    the sender the message is getting sended to
	 * @return equals the param state
	 */
	public static boolean sendErrorToSender(boolean state, ErrorType ErrorType, CommandSender sender) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			player.sendMessage(ErrorMessage(ErrorType, player.getLocale()));
			return state;
		} else {
			sender.sendMessage(ErrorMessage(ErrorType, "en_us"));
			return state;
		}
	}

	/**
	 * Send error to player. <br>
	 * according to the ErrorType enum
	 * 
	 * @author reventcake295
	 * @param state     the state that must be return
	 * @param ErrorType the error type that must be send
	 * @param player    the player the message is getting sended to
	 * @return equals the param state
	 */
	public static boolean sendErrorToPlayer(boolean state, ErrorType ErrorType, Player player) {
		player.sendMessage(ErrorMessage(ErrorType, player.getLocale()));
		return state;
	}

	/**
	 * Error message.
	 *
	 * @param ErrorType the error type
	 * @param lang      the lanquage it must be send in
	 * @return the string whit the error
	 */
	public static String ErrorMessage(ErrorType ErrorType, String lang) {
		String message = null;
		File langfile = new File(StaticData.langData + lang + ".lang");

		if (!langfile.exists())
			langfile = new File("./locale/en_us.lang");

		try (Scanner scanner = new Scanner(langfile)) {

			while (scanner.hasNextLine()) {
				String langLine = scanner.nextLine();
				if (langLine.startsWith('"' + ErrorType.toString())) {
					message = langLine.replaceAll('"' + ErrorType.name() + "=", "").replaceAll('"' + "", "");
					break;
				}
			}

		} catch (IOException ex) {
			ex.printStackTrace(CoreMain.getErrorWriter());
		}
		assert message != null;
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}
