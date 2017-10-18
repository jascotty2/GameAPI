package me.kangarko.gameapi.plugin;

import org.bukkit.plugin.Plugin;

import me.kangarko.gameapi.registry.ArenaRegistry;

/**
 * Represents a minigame plugin with arena	s.
 */
public interface ArenaPlugin {

	/**
	 * Get the arena manager.
	 *
	 * If you don't have one, you can just return {@link ArenaRegistry#getArenaManager()}
	 * and that will use our shared manager.
	 *
	 * @return the arena manage
	 */
	public ArenaManager getArenas();

	/**
	 * Get the name of this plugin
	 *
	 * @return the name of this plugin
	 */
	public String getName();

	/**
	 * Get the {@link ArenaPlugin} representation
	 *
	 * @return the plugin
	 */
	public Plugin getPlugin();
}
