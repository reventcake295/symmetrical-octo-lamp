package com.revent.event.hub;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamage implements Listener {

	@EventHandler
	public void playerDamge(EntityDamageEvent event) {
		//get the entity that got hurt
		Entity e = event.getEntity();

		if ((e instanceof Player && e.getWorld().getName().equals("world"))) {
			//no player is allowed to get hurt in the hub
			event.setCancelled(true);
		}
	}
}
