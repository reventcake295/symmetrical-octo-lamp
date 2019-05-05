package com.revent;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.revent.returnstate.ReturnError;
import com.revent.types.ErrorType;

public class info {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		// check if the sender is player
		if (!(sender instanceof Player))
			return ReturnError.sendErrorToSender(true, ErrorType.noPlayer, sender);

		// get the deep connection between the sever and the player
		//Player player = (Player) sender;
		//PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;

		// send the chat message
		//PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a("{\"text\":\"TEXT TEXT TEXT TEXT TEXT TEXT\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://www.google.nl/?gfe_rd=cr&dcr=0&ei=WFrjWYfrMbGk8wee_JzgBw\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"CLICK ME\"}]}}}"));
		//connection.sendPacket(packet);
		return true;
	}

}


