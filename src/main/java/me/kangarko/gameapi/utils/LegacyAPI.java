package me.kangarko.gameapi.utils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Class for compatibility purposes.
 */
public class LegacyAPI {

	private static final Method getPlayersMethod;
	private static final boolean isGetPlayersCollection;

	static {
		try {
			getPlayersMethod = Bukkit.class.getMethod("getOnlinePlayers");
			isGetPlayersCollection = getPlayersMethod.getReturnType() == Collection.class;
		} catch (final Throwable t) {
			throw new RuntimeException("Failed to setup reflection", t);
		}
	}

	public static Collection<? extends Player> getOnlinePlayers() {
		if (isGetPlayersCollection)
			return Bukkit.getOnlinePlayers();

		try {
			return Arrays.asList( (Player[]) getPlayersMethod.invoke(null) );
		} catch (final ReflectiveOperationException ex) {
			throw new RuntimeException("Reflection malfunction", ex);
		}
	}
}
