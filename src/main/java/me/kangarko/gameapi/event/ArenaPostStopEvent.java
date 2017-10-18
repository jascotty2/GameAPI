package me.kangarko.gameapi.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.kangarko.gameapi.Arena;
import me.kangarko.gameapi.cause.StopCause;

/**
 * Triggered after the arena stopped for any reason (stop command, reload, error, or naturally)
 */
@Getter
@RequiredArgsConstructor
public final class ArenaPostStopEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	/**
	 * The arena
	 */
	private final Arena arena;

	/**
	 * Why is the arena stopping
	 */
	private final StopCause cause;

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}