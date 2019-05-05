package com.revent.managers;

import com.revent.CoreMain;
import com.revent.util.PlayerVisibility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.revent.mysql.WorldPlayerData;
import com.revent.returnstate.ReturnError;
import com.revent.returnstate.ReturnSucces;
import com.revent.types.ErrorType;
import com.revent.types.SuccesType;

public class WorldTeleportManager {



	@SuppressWarnings({"null"})
	public static void WorldTeleport(Player player, Location warpLoc) {

		if (player.getWorld() == warpLoc.getWorld()) {
			ReturnError.sendErrorToPlayer(false, ErrorType.inWorld, player);
			return;
		}

		//get the required data
		String World = warpLoc.getWorld().getName();
		String oldWorld = player.getWorld().getName();

		//if the player is in a fight world force the player to stand still for five seconds
		if (CoreMain.worldData.combatWorld.contains(oldWorld)) {
			player.sendMessage(ReturnSucces.SuccesMessage(SuccesType.waitFiveSeconds, player.getLocale()));
			CoreMain.playerData.waitList.add(player);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace(CoreMain.getErrorWriter());
			}
			
			if (!CoreMain.playerData.waitList.contains(player))
				return;

			CoreMain.playerData.waitList.remove(player);
		}
		
		if (CoreMain.worldData.locWorld.contains(oldWorld))
			// store player data
			WorldPlayerData.locationStore(player);

		if (CoreMain.worldData.locWorld.contains(World)) {
			//teleport the player to the last known location in this world
			if (WorldPlayerData.LocationTest(player, warpLoc.getWorld())) {
				// teleport the player to the world required and sets fall distance
				player.setFallDistance(WorldPlayerData.getFallDistance(player, World));
				player.teleport(WorldPlayerData.locationGet(player, warpLoc.getWorld()), TeleportCause.COMMAND);

			} else {
				// teleport the player to the world required and resets fall distance
				player.setFallDistance(0);
				player.teleport(warpLoc, TeleportCause.COMMAND);
			}
		} else {
			player.setFallDistance(0);
			player.teleport(warpLoc, TeleportCause.COMMAND);
		}

		// hide everyone but let every one that is in the same world appear
		PlayerVisibility.playerAppearWorld(player, Bukkit.getWorld(World));

		//send a title to the player
		player.sendTitle(ChatColor.GOLD + CoreMain.worldData.worldName.get(World),
				ReturnSucces.SuccesMessage(SuccesType.welcomeWorld, player.getLocale()), 10, 70, 20);

	}
}
