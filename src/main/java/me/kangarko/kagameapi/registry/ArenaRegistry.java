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
 * Stores plugins.
 *
 * Please register your arena manually!
 */
public class ArenaRegistry {

	private static final HashMap<ArenaPlugin, List<Arena>> registered = new HashMap<>();

	@Getter
	private static final ArenaManager arenaManager = new CommonArenaManager();

	public static void register(ArenaPlugin plugin, Arena arena) {
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

	public static Set<ArenaPlugin> getRegisteredPlugins() {
		return Collections.unmodifiableSet(registered.keySet());
	}

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

				if (arena.getState() == ArenaState.STOPPED)
					continue;

				if (arena.getPlayers().contains(pl)) {
					Validate.isTrue(arena.getState() != ArenaState.STOPPED, "Report / Illegal state of " + arena.getName());

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
