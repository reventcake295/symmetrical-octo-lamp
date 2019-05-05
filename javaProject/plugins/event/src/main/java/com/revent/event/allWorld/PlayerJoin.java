package com.revent.event.allWorld;

import com.revent.util.PlayerVisibility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.revent.returnstate.ReturnSucces;
import com.revent.types.SuccesType;

public class PlayerJoin implements Listener {

	@SuppressWarnings({"null"})
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		// teleport player
		player.teleport(new Location(Bukkit.getWorld("world"), 33.5, 75, 264.5), TeleportCause.PLUGIN);

		//send the title
		player.sendTitle(ReturnSucces.SuccesMessage(SuccesType.welcomeMessageUnderline, player.getLocale()),
				ReturnSucces.SuccesMessage(SuccesType.welcomeMessageUnderline, player.getLocale()), 10, 70, 20);
		event.setJoinMessage("");
		//let the player only be in the hub be visable
		PlayerVisibility.playerAppearWorld(player, Bukkit.getWorld("world"));

		//TODO: test for precense in database
		//      place player in database otherwise
	}
}
