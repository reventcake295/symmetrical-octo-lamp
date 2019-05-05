package com.revent.event.allWorld;

import com.revent.CoreMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.revent.mysql.WorldPlayerData;

import java.util.ArrayList;

public class PlayerQuit implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		e.setQuitMessage("");

		if (CoreMain.playerData.combatLog.containsKey(player)) {
			ArrayList<Player> WorldPlayers = new ArrayList<>();
			for (Player all : Bukkit.getOnlinePlayers()) {
				if (all.getWorld() == player.getWorld()) {
					WorldPlayers.add(all);
				} // get the players that are in the same world as the player that is logging out
			} // send the message

			for (Player worldPlayer : WorldPlayers) {
				worldPlayer.sendMessage("<" + player.getDisplayName() + "> just logged of while being in combat!");
			}
		}
		// store the location of the player
		if (CoreMain.worldData.locWorld.contains(player.getWorld().toString()))
			WorldPlayerData.locationStore(player);

		// remove the player from the nickname list
		if (CoreMain.playerData.nicknamedPlayer.get(player) != null) {
			CoreMain.playerData.playerNicknamed.remove(CoreMain.playerData.nicknamedPlayer.get(player));
			CoreMain.playerData.nicknamedPlayer.remove(player);
		}

		// remove the player from the list if he is vanished
		try {
			for (Player playerHidden : CoreMain.playerData.vanished) {
				if (playerHidden.equals(player)) {
					CoreMain.playerData.vanished.remove(player);
				}
			}
		} catch (NullPointerException ignored) {
			//can be that the list is empty then it will return a nullpointer
		}
	}
}
