package com.revent.objects;

import com.revent.types.FightType;
import org.bukkit.entity.Player;

import com.revent.types.PlayerState;
import com.revent.types.GameState;

public class PlayerFight {

	/** the player */
	public Player player;

	public Player getPlayer() {
		return player;
	}

	/** The type of fight this is */
	private FightType fightType;

	public FightType getGameType() {
		return fightType;
	}

	public PlayerFight(FightType fightType, Player player) {
		this.fightType = fightType;
		this.player = player;
	}

	/** The current state of the player */
	private PlayerState currentState;

	public PlayerFight setCurrentState(PlayerState playerState) {
		this.currentState = playerState;
		return this;
	}

	public PlayerState getCurrentState() {
		return currentState;
	}

	/** His class this round */
	private String classType;

	public PlayerFight setClassType(String playerClass) {
		this.classType = playerClass;
		return this;
	}
	
	public String getClassType() {
		return classType;
	}


	/** game State */
	public GameState gameState;

	public PlayerFight setGameState(GameState gameState) {
		this.gameState = gameState;
		return this;
	}
	
	public GameState getGameState() {
		return gameState;
	}


}
