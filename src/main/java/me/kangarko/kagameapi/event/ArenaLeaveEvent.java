package me.kangarko.kagameapi.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.kangarko.kagameapi.Arena;
import me.kangarko.kagameapi.cause.LeaveCause;

/**
 * Triggered when a player has left the arena.
 */
@Getter
@Setter
@RequiredArgsConstructor
public final class ArenaLeaveEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	/**
	 * The arena
	 */
	private final Arena arena;

	/**
	 * The cause
	 */
	private final LeaveCause cause;

	/**
	 * The player
	 */
	private final Player player;

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}