package me.kangarko.gameapi.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.kangarko.gameapi.Arena;
import me.kangarko.gameapi.cause.JoinCause;

/**
 * Triggered when the a player attempts to join an arena.
 */
@Getter
@Setter
@RequiredArgsConstructor
public final class ArenaPreJoinEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	/**
	 * The arena
	 */
	private final Arena arena;

	/**
	 * The cause
	 */
	private final JoinCause cause;

	/**
	 * The player
	 */
	private final Player player;

	/**
	 * Cancel joining?
	 */
	private boolean cancelled;

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}