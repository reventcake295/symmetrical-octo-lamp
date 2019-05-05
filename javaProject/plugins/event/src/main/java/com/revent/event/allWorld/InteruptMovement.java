package com.revent.event.allWorld;

import com.revent.CoreMain;
import com.revent.managers.WorldTeleportManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.revent.returnstate.ReturnError;
import com.revent.types.ErrorType;

public class InteruptMovement implements Listener {
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		//if the player is on the wait list remove set player from the wait list and report that the player has moved
		if (CoreMain.playerData.waitList.contains(event.getPlayer())) {
			CoreMain.playerData.waitList.remove(event.getPlayer());
			ReturnError.sendErrorToPlayer(false, ErrorType.playerMoved, event.getPlayer());
        }
	}
}
