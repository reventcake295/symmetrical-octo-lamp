package com.revent.types;

/**
 * The Enum ErrorType.
 */
public enum ErrorType {
	
	/** sender is no player. */
	noPlayer,
	
	/** wrong command usage. */
	wrongCMD,
	
	/** permmission not granted. */
	noPerm,
	
	/** The player is not online. */
	playerNotOn,
	
	/** when there is no sectie */
	noSectie,
	
	/** there is no conversation currently*/
	noChat,
	
	/** bad word usage */
	badWord,
	
	/** elevator has no destination */
	noDestination,
	
	/** name is already in use */
	nameUse,
	
	/** banned from the server */
	bannedServer,
	
	/** banned from world */
	bannedWorld,

	/** if the world is closed for what ever reason*/
	worldClosed,
	/** already in world */
	inWorld,
	
	/** player moved while teleporting */
	playerMoved
}
