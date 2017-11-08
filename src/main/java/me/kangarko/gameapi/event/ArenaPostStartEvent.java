package me.kangarko.gameapi.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.kangarko.gameapi.Arena;

/**
 * Triggered when the arena starts after the lobby wait time has finished.
 */
@Getter
@RequiredArgsConstructor
public final class ArenaPostStartEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	/**
	 * The arena
	 */
	private final Arena arena;

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}