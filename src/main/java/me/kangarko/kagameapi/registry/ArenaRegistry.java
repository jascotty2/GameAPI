package me.kangarko.kagameapi.registry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.Validate;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import lombok.Getter;
import me.kangarko.kagameapi.Arena;
import me.kangarko.kagameapi.ArenaSign;
import me.kangarko.kagameapi.ArenaSigns;
import me.kangarko.kagameapi.plugin.ArenaManager;
import me.kangarko.kagameapi.plugin.ArenaPlugin;
import me.kangarko.kagameapi.type.ArenaState;

/**
 * The main class that you need to utilize in order for your plugin to be
 * recognized within other plugins like AutoPlay.
 *
 * Please register your arena manually at {@link #register(ArenaPlugin, Arena)}!
 */
public class ArenaRegistry {

	private static final HashMap<ArenaPlugin, List<Arena>> registered = new HashMap<>();

	@Getter
	private static final ArenaManager arenaManager = new CommonArenaManager();

	/**
	 * Register your arena within your arena plugin.
	 *
	 * @param plugin the plugin
	 * @param arena the arena
	 */
	public static void register(ArenaPlugin plugin, Arena arena) {
		final List<Arena> current = registered.getOrDefault(plugin, new ArrayList<>());
		Validate.isTrue(!current.contains(arena), "Arena " + arena.getName() + " already registered for " + plugin.getName());

		current.add(arena);
		registered.put(plugin, current);
	}

	/**
	 * Unregister an arena of a certain arena plugin.
	 *
	 * @param plugin the plugin
	 * @param arena the arena
	 */
	public static void unregister(ArenaPlugin plugin, Arena arena) {
		final List<Arena> current = registered.getOrDefault(plugin, new ArrayList<>());
		Validate.isTrue(current.remove(arena), "Arena " + arena.getName() + " is not registered for " + plugin.getName());

		current.remove(arena);
	}

	/**
	 * Clears all registered arenas for a plugin.
	 *
	 * @param plugin the plugin
	 */
	public static void unregisterAll(ArenaPlugin plugin) {
		registered.remove(plugin);
	}

	/**
	 * Get all registered arena plugins.
	 *
	 * @return registered plugins
	 */
	public static Set<ArenaPlugin> getRegisteredPlugins() {
		return Collections.unmodifiableSet(registered.keySet());
	}

	/**
	 * Get all arenas for a plugin.
	 *
	 * @param plugin the plugin
	 * @return the arenas for that plugin
	 */
	public static List<Arena> getArenas(ArenaPlugin plugin) {
		final List<Arena> list = registered.get(plugin);

		return list != null ? Collections.unmodifiableList(list) : null;
	}

	/**
	 * Represents an arena manager that is shared for all of the registered arenas.
	 */
	public static final class CommonArenaManager implements ArenaManager {

		@Override
		public final ArenaSign findArenaSign(Sign state) {
			for (final Arena arena : getArenas()) {
				final ArenaSigns signs = arena.getData().getSigns();

				if (signs != null) {
					final ArenaSign arenasign = signs.getSignAt(state.getLocation());

					if (arenasign != null)
						return arenasign;
				}
			}

			return null;
		}

		@Override
		public final Arena findArena(Player pl) {
			for (final Arena arena : getArenas()) {
				if (!arena.getSetup().isReady())
					continue;

				if (arena.getPlayers().contains(pl)) {
					Validate.isTrue(arena.getState() != ArenaState.STOPPED, "Report / Found player '" + pl.getName() + "' in a stopped arena " + arena.getName() + " by " + arena.getPlugin());

					return arena;
				}
			}

			return null;
		}

		@Override
		public final Arena findArena(String name) {
			for (final Arena arena : getArenas())
				if (arena.getName().equalsIgnoreCase(name))
					return arena;

			return null;
		}

		@Override
		public final Set<Arena> getArenas() {
			final List<Arena> all = new ArrayList<>();

			for (final List<Arena> pluginArenas : registered.values())
				all.addAll( pluginArenas );

			return new HashSet<>(all);
		}

		@Override
		public final boolean isPlaying(Player pl) {
			return findArena(pl) != null;
		}

		@Override
		public final List<String> getArenaNames() {
			final List<String> all = new ArrayList<>();

			getArenas().forEach( (a) -> all.add(a.getName()) );

			return all;
		}
	}
}
