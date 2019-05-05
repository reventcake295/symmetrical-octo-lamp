package com.revent.objects;

import java.util.Collection;

import com.revent.permission.CentralBus;
import org.bukkit.entity.Player;

import me.lucko.luckperms.api.Group;

public class Ranks {

	//collections of ranks in all types
	public static Collection<String> possibleGroups;
	
	public static Collection<String> specialStaffGroups;
	
	public static Collection<String> buyableGroups;
	
	public Ranks() {//construct the ranks
		possibleGroups.add("admin");
		possibleGroups.add("co-admin");
		possibleGroups.add("mod");
		possibleGroups.add("builders");
		possibleGroups.add("troller");
		possibleGroups.add("YT");
		possibleGroups.add("diamond");
		possibleGroups.add("emerald");
		possibleGroups.add("gold");
		possibleGroups.add("donator");
		possibleGroups.add("default");
		
		specialStaffGroups.add("admin");
		specialStaffGroups.add("co-admin");
		specialStaffGroups.add("mod");
		specialStaffGroups.add("builders");
		specialStaffGroups.add("troller");
		specialStaffGroups.add("YT");
		
		buyableGroups.add("diamond");
		buyableGroups.add("emerald");
		buyableGroups.add("gold");
		buyableGroups.add("donator");
		buyableGroups.add("default");
    }
	
	public static String getPlayerGroupAll(Player player) {//get the group what ever is considerd the highest ranked for the player
	    for (String group : possibleGroups) {
	        if (player.hasPermission("group." + group)) {
	            return group;
	        }
	    }
	    return "default";
	}
	
	public static String getPlayerGroupSpecialStaff(Player player) {//get the group from the staff list
	    for (String group : specialStaffGroups) {
	        if (player.hasPermission("group." + group)) {
	            return group;
	        }
	    }
	    return null;
	}
	
	public static String getPlayerGroupBuy(Player player) {//get the group from the buyable list
	    for (String group : buyableGroups) {
	        if (player.hasPermission("group." + group)) {
	            return group;
	        }
	    }
	    return "default";
	}
	
	public static Group getGroup(String Group) {
		Group group = CentralBus.LuckpermsApi.getGroupManager().getGroup(Group);
		if (group == null) {
		    // group doesn't exist.
		    throw new NullPointerException("Group does not exist");
		}
		return group;
	}
}
