package com.revent.permission;

import org.bukkit.entity.Player;

import me.lucko.luckperms.api.User;

public class UserBus {

	public static User getUser(Player player) {//get the user from the luckperms api
	    // assert that the player is online
	    if (!player.isOnline()) {
	        throw new IllegalStateException("Player is offline");
	    }
	    
	    return CentralBus.LuckpermsApi.getUserManager().getUser(player.getUniqueId());
	}
	//test wheter or not a certain player is in a group
	public static boolean isPlayerInGroup(Player player, String group) {
	    return player.hasPermission("group." + group);
	}
}
