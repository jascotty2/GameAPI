package me.kangarko.kagameapi.type;

public enum MessengerTarget {

	/**
	 * The default
	 *
	 * Players in the arena
	 */
	ARENA,

	/**
	 * Players in the same world as the arena is within, used by world automatic mode
	 */
	WORLD,

	/**
	 * Everyone on the server, used by server automatic mode
	 */
	GLOBAL
}
