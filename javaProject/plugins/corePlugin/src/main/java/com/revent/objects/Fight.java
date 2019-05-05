package com.revent.objects;

import java.util.ArrayList;
import java.util.HashMap;

import com.revent.CoreMain;
import com.revent.types.FightType;
import org.bukkit.entity.Player;

import com.revent.types.PlayerState;
import com.revent.types.GameState;

public class Fight {

	private HashMap<Player, PlayerFight> fight;

	public Fight(FightType fightType, ArrayList<Player> players) {
		fight = new HashMap<>();
		for (Player player : players) {
			PlayerFight fightTemp = new PlayerFight(fightType, player);
			fight.put(player, fightTemp);
		}
	}

	public Fight gameStart(Player player, PlayerState playerState, String classType) { //start whatever game this fight is
		PlayerFight tempFight = fight.get(player);
		tempFight.setClassType(classType);
		tempFight.setCurrentState(playerState);
		tempFight.gameState = GameState.GameInProgress;
		fight.put(player, tempFight);
		CoreMain.playerData.playerFight.add(player);
		return this;
	}

	public Fight playerLeave(Player player) {//for what ever reason the player leaves the fight
		fight.remove(player);
		CoreMain.playerData.playerFight.remove(player);
		return this;
	}

	public Fight playerKilled(Player player) {//designate one of the players as dead
		PlayerFight tempFight = fight.get(player);
		tempFight.setCurrentState(PlayerState.Dead);
		fight.put(player, tempFight);
		CoreMain.playerData.playerFight.remove(player);
		return this;
	}

	public Fight gameEnded(ArrayList<Player> players) {//end the game
		for (Player player : players) {
			PlayerFight tempFight = fight.get(player);
			tempFight.gameState = GameState.GameEnded;
			CoreMain.playerData.playerFight.remove(player);
		}
		return this;
	}
}
