package me.kangarko.kagameapi.cause;

public enum LeaveCause {

	/**
	 * He got killed, etc
	 */
	KILLED,

	/**
	 * Left the game
	 */
	DISCONNECT,

	/**
	 * An error in the plugin has caused the player to be kicked for safety
	 */
	ERROR,

	/**
	 * Pussy
	 */
	COMMAND,

	/**
	 * Arena is finished
	 */
	ARENA_END,

	/**
	 * No class selected and could not chose a random class (no permission?)
	 */
	NO_CLASS,

	/**
	 * The arena requires higher tier then all of the classes player dispose.
	 */
	NO_ENOUGH_CLASS,

	/**
	 * Exited from the arena automatically, via AutoPlay for example
	 */
	AUTO_MODE,
}
