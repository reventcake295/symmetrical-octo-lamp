package com.revent.event.allWorld;

import com.revent.DBMain;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class chatChannel implements Listener {

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {

		e.setCancelled(true);
		//call the chatmanager so the message may be prossed cantralized
		DBMain.cM.chatMessage(e.getPlayer(), e.getMessage(), false, "none");
	}
}
