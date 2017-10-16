package me.kangarko.kagameapi.impl;

import org.bukkit.plugin.Plugin;

import lombok.RequiredArgsConstructor;
import me.kangarko.kagameapi.plugin.ArenaManager;
import me.kangarko.kagameapi.plugin.ArenaPlugin;
import me.kangarko.kagameapi.registry.ArenaRegistry;

/**
 * Represents a dummy arena plugin using shared arena manager.
 *
 * Make your JavaPlugin extend this.
 */
@RequiredArgsConstructor
public final class DummyPlugin implements ArenaPlugin {

	private final Plugin plugin;

	@Override
	public String getName() {
		return plugin.getName();
	}

	// Return a common automatic registry.
	@Override
	public final ArenaManager getArenas() {
		return ArenaRegistry.getArenaManager();
	}
}