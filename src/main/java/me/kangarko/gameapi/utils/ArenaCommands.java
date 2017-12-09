package me.kangarko.gameapi.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.kangarko.gameapi.Arena;

/**
 * A collections of commands that are run during a course of an arena.
 */
public final class ArenaCommands {

	/**
	 * Commands to be run as a player.
	 */
	private final List<String> playerCommands;

	/**
	 * Commands to be run as the console.
	 */
	private final List<String> consoleCommands;

	public ArenaCommands(List<String> playerCommands, List<String> consoleCommands) {
		this.playerCommands = removeFirstSlash(playerCommands);
		this.consoleCommands = removeFirstSlash(consoleCommands);
	}

	// Remove first / slash from the command list.
	private final List<String> removeFirstSlash(List<String> commands) {
		final List<String> copy = new ArrayList<>();

		if (commands != null)
			commands.forEach((cmd) -> {
				if (cmd.startsWith("/"))
					cmd = cmd.substring(1, cmd.length());

				copy.add(cmd);
			});

		return copy;
	}

	/**
	 * Run both {@link #playerCommands} and {@link #consoleCommands}
	 * for each online player in said arena.
	 *
	 * @param arena
	 * @param consoleForEach run console commands for each player as the console or from the console, once?
	 * @param player
	 */
	public final void run(Arena arena, boolean consoleForEach) {
		runConsole(arena,consoleForEach);

		for (final Player player : arena.getPlayers())
			runPlayer(arena, player);
	}

	/**
	 * Run {@link #playerCommands} as the player, if any.
	 *
	 * @param arena
	 * @param player
	 */
	public final void runPlayer(Arena arena, Player player) {
		for (final String cmd : playerCommands) {
			final String coloredCommand = GameAPIUtils.colorize( arena.getMessenger().replaceVariables(cmd.replace("{player}", player.getName())) );

			if (cmd.startsWith("@tell "))
				arena.getMessenger().tell(player, coloredCommand.replaceFirst("@tell ", ""));

			else if (cmd.startsWith("@connect "))
				BungeeMessaging.sendBungeeMessage(player, "Connect", coloredCommand.replaceFirst("@connect ", ""));

			else
				player.performCommand(coloredCommand);
		}
	}

	/**
	 * Run {@link #consoleCommands} as the server operator.
	 *
	 * @param arena
	 * @param consoleForEach run console commands for each player as the console or from the console, once?
	 */
	public final void runConsole(Arena arena, boolean consoleForEach) {
		for (final String cmd : consoleCommands) {
			final String coloredCommand = GameAPIUtils.colorize( arena.getMessenger().replaceVariables(cmd) );

			if (consoleForEach)
				for (final Player player : arena.getPlayers())
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), coloredCommand.replace("{player}", player.getName()));

			else
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), coloredCommand);
		}
	}

	public final void runConsoleFor(Arena arena, Player player) {
		for (final String cmd : consoleCommands) {
			final String coloredCommand = GameAPIUtils.colorize( arena.getMessenger().replaceVariables(cmd.replace("{player}", player.getName())) );

			if (cmd.startsWith("@tell "))
				arena.getMessenger().tell(player, coloredCommand.replaceFirst("@tell ", ""));

			else if (cmd.startsWith("@connect "))
				BungeeMessaging.sendBungeeMessage(player, "Connect", coloredCommand.replaceFirst("@connect ", ""));

			else
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), coloredCommand);
		}
	}

	// Run a task on the main thread
	/*private final void schedule(Runnable r) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(GameAPIPlugin.getInstance(), r);
	}*/
}
