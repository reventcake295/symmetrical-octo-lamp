package com.revent.event;

import com.revent.event.allWorld.*;
import com.revent.event.hub.ItemClick;
import com.revent.event.hub.PlayerDamage;
import com.revent.event.multiWorld.KitSelect;
import com.revent.event.multiWorld.composter;
import com.revent.event.multiWorld.sieve;
import com.revent.event.roofwars.fight.EggExplosion;
import com.revent.event.roofwars.fight.EggThrow;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EventMain extends JavaPlugin {

	private PluginManager pm = getServer().getPluginManager();

	public void onEnable() {
		eventsMultiple();
		eventsAll();
		eventsRoofwarsFight();
		eventsChat();
		eventsBan();
		eventsHub();
	}

	private void eventsAll() {
		pm.registerEvents(new trashCan(), this);
		pm.registerEvents(new elevatorSign(), this);
		pm.registerEvents(new InventoryClick(), this);
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new PlayerQuit(), this);
		pm.registerEvents(new InteruptMovement(), this);
		pm.registerEvents(new PlayerFight(), this);
	}

	private void eventsMultiple() {
		pm.registerEvents(new sieve(), this);
		pm.registerEvents(new composter(), this);
		pm.registerEvents(new KitSelect(), this);
	}

	private void eventsRoofwarsFight() {
		pm.registerEvents(new EggExplosion(), this);
		pm.registerEvents(new EggThrow(), this);
	}

	private void eventsChat() {
		pm.registerEvents(new chatChannel(), this);
	}

	private void eventsBan() {
		pm.registerEvents(new BanMenuDetect(), this);
	}

	private void eventsHub() {
		pm.registerEvents(new PlayerDamage(), this);
		pm.registerEvents(new ItemClick(), this);
	}
}