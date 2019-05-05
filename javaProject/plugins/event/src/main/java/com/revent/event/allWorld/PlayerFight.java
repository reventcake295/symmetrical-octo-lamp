package com.revent.event.allWorld;

import com.revent.CoreMain;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.revent.DBMain;
import com.revent.returnstate.ReturnSucces;
import com.revent.types.SuccesType;

public class PlayerFight implements Listener {

	@EventHandler
	public void playerDamge(EntityDamageByEntityEvent event) {
		//make sure we are dealing with a player
		if (!(event.getEntity() instanceof Player))
			return;
		//get the required data
		Player player = (Player) event.getEntity();
		Player attacker = null;
		//make sure that the player is in a world where pvp is a thing
		if (!(CoreMain.worldData.combatWorld.contains(player.getWorld().toString())))
			return;
		//the attacker also may not be left out!
		if (event.getDamager() instanceof Player) {
			attacker = (Player) event.getDamager();
			CoreMain.playerData.combatLog.put(attacker, System.currentTimeMillis());
			ReturnSucces.sendSuccesToPlayer(true, SuccesType.combatLogged, attacker);
		}
		CoreMain.playerData.combatLog.put(player, System.currentTimeMillis());
		ReturnSucces.sendSuccesToPlayer(true, SuccesType.combatLogged, player);
		//wait
		try {
			Thread.sleep(10000);
		} catch (InterruptedException exception) {
			exception.printStackTrace(DBMain.getErrorWriter());
		}
		// noinspection ConstantConditions
		if (!attacker.equals(null))
			CoreMain.playerData.combatLog.remove(attacker);

		CoreMain.playerData.combatLog.remove(player);
		
	}
}
