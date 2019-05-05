package com.revent.event.roofwars.fight;

import com.revent.CoreMain;
import com.revent.random.serverData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;

import java.util.Random;

public class EggThrow implements Listener {
	
	@EventHandler
	public void eggThrow(PlayerEggThrowEvent event) {
		//make sure the world is correct and the player is in combat
		if (event.getPlayer().getWorld().getName().equals("roofwarsFight") && CoreMain.playerData.playerFight.contains(event.getPlayer()))
			return;
		//there may be no uncontrolled chickens running around
		event.setHatching(false);
		if (new Random(3).nextInt() < 2) {//2 out of 3 chance that the egg may explode other wise there will be a chicken
			event.getEgg().setCustomName("Exploding egg");
		} else {
			event.getEgg().setCustomName("Chicken egg 0");
		}
	}
} 
