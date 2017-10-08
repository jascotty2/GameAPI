package me.kangarko.kagameapi.cause;

public enum JoinCause {

	/**
	 * Player has joined as a spectator
	 */
	//SPECTATE,

	/**
	 * Player has been joined automatically, via AutoPlay for example
	 */
	AUTO_JOIN,

	/**
	 * Player has joined via command
	 */
	COMMAND,

	/**
	 * Player has joined via clicking a regular sign
	 */
	SIGN
}
