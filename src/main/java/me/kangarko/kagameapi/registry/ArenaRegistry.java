package me.kangarko.kagameapi.registry;

import java.util.ArrayList;
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
import me.kangarko.kagameapi.plugin.ArenaManager;
import me.kangarko.kagameapi.plugin.ArenaPlugin;

/**
 * Stores plugins.
 *
 * Please register your arena manually!
 */
public class ArenaRegistry {

	private static final HashMap<ArenaPlugin, List<Arena>> registered = new HashMap<>();

	@Getter
	private static final ArenaManager arenaManager = new CommonArenaManager();

	public static void register(ArenaPlugin plugin, Arena arena) {
		if (!registered.containsKey(plugin))
			System.out.println("Hooked into: " + plugin.getName());

		final List<Arena> current = registered.getOrDefault(plugin, new ArrayList<>());
		Validate.isTrue(!current.contains(arena), "Arena " + arena.getName() + " already registered for " + plugin.getName());

		current.add(arena);
		registered.put(plugin, current);
	}

	public static void unregister(ArenaPlugin plugin, Arena arena) {
		final List<Arena> current = registered.getOrDefault(plugin, new ArrayList<>());
		Validate.isTrue(current.remove(arena), "Arena " + arena.getName() + " is not registered for " + plugin.getName());

		registered.put(plugin, current);
	}

	public static void unregisterAll(ArenaPlugin plugin) {
		registered.remove(plugin);
	}

	public static final class CommonArenaManager implements ArenaManager {

		@Override
		public ArenaSign findArenaSign(Sign state) {
			for (final ArenaPlugin plugin : registered.keySet()) {
				final ArenaSign found = plugin.getArenas().findArenaSign(state);

				if (found != null)
					return found;
			}

			return null;
		}

		@Override
		public Arena findArena(Player pl) {
			for (final ArenaPlugin plugin : registered.keySet()) {
				final Arena found = plugin.getArenas().findArena(pl);

				if (found != null)
					return found;
			}

			return null;
		}

		@Override
		public Arena findArena(String name) {
			for (final ArenaPlugin plugin : registered.keySet()) {
				final Arena found = plugin.getArenas().findArena(name);

				if (found != null)
					return found;
			}

			return null;
		}

		@Override
		public Set<Arena> getArenas() {
			final List<Arena> all = new ArrayList<>();

			for (final List<Arena> pluginArenas : registered.values())
				all.addAll( pluginArenas );

			return new HashSet<>(all);
		}

		@Override
		public boolean isPlaying(Player pl) {
			for (final ArenaPlugin plugin : registered.keySet()) {
				final boolean playing = plugin.getArenas().isPlaying(pl);

				if (playing)
					return true;
			}

			return false;
		}

		@Override
		public List<String> getArenaNames() {
			final List<String> all = new ArrayList<>();

			for (final List<Arena> pluginArenas : registered.values())
				for (final Arena arena : pluginArenas)
					all.add(arena.getName());

			return all;
		}
	}
}
