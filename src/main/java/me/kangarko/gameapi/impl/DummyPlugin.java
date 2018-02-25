package me.kangarko.gameapi.impl;

import org.bukkit.plugin.Plugin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.kangarko.gameapi.ArenaManager;
import me.kangarko.gameapi.ArenaPlugin;

/**
 * Represents a dummy arena plugin using a shared arena manager.
 */
@RequiredArgsConstructor
public final class DummyPlugin implements ArenaPlugin {

	@Getter
	private final Plugin plugin;

	@Override
	public String getName() {
		return plugin.getName();
	}

	// Return a common automatic registry.
	@Override
	public final ArenaManager getArenas() {
		throw new UnsupportedOperationException("Use ArenaRegistry to get ArenaManager");
	}
}