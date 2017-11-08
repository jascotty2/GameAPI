package me.kangarko.gameapi.utils;

import org.bukkit.ChatColor;

/**
 * Misc utils
 */
public final class GameAPIUtils {

	/**
	 * Colorizes the messages using the & letters.
	 *
	 * @param message the message
	 * @return the colorized message.
	 */
	public static final String colorize(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}
