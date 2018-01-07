package me.kangarko.gameapi.cause;

import me.kangarko.gameapi.Arena;

/**
 * The reason why an {@link Arena} stopped.
 */
public enum StopCause {

	/**
	 * Natural, usually triggered by somebody or some that has won
	 */
	NATURAL_TRIGGER,

	/**
	 * Natural, the last phase is out
	 */
	NATURAL_LAST_PHASE,

	/**
	 * The time is up!
	 */
	NATURAL_COUNTDOWN,

	/**
	 * There are no more monsters alive.
	 */
	NATURAL_NO_MONSTERS,

	/**
	 * Stopped by a command or a server reload
	 */
	INTERRUPTED_COMMAND,

	/**
	 * Stopped by AutoPlay plugin when the rotation is suspended
	 */
	INTERRUPTED_AUTO_SUSPENDED,

	/**
	 * Stopped by AutoPlay plugin when the maximum allowed arena time is up
	 */
	INTERRUPTED_AUTO_COUNTDOWN,

	/**
	 * Stopped by AutoPlay plugin when we attempt to start a minigame plugin but they reported a failed attempt
	 */
	INTERRUPTED_AUTO_START_FAILED,

	/**
	 * Stopped due to an error
	 */
	INTERRUPTED_ERROR,

	/**
	 * Stopped due to a server or plugin reload
	 */
	INTERRUPTED_RELOAD,

	/**
	 * Stopped due to being empty
	 */
	INTERRUPTED_LAST_PLAYER_LEFT,

	/**
	 * No-one cares
	 */
	CANCELLED_NOT_ENOUGH_PLAYERS
}
