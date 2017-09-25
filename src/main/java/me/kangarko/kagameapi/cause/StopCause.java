package me.kangarko.kagameapi.cause;

public enum StopCause {

	/**
	 * Natural, usually triggered by somebody or some that has won
	 */
	NATURAL_TRIGGER,

	/**
	 * The time is up!
	 */
	NATURAL_COUNTDOWN,

	/**
	 * Stopped by a command or a server reload
	 */
	INTERRUPTED_COMMAND,

	INTERRUPTED_ERROR,

	INTERRUPTED_RELOAD,

	INTERRUPTED_LAST_PLAYER_LEFT,

	/**
	 * No-one cares
	 */
	CANCELLED_NOT_ENOUGH_PLAYERS
}
