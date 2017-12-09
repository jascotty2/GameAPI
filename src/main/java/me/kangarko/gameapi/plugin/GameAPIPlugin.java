package me.kangarko.gameapi.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;

import me.kangarko.gameapi.data.PlayerData;
import me.kangarko.gameapi.listener.PlayerListener;
import me.kangarko.gameapi.registry.ArenaRegistry;

public final class GameAPIPlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		// Create data folder
		if (!getDataFolder().exists())
			saveResource("data.db", false);

		// Register events
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);

		// Register BungeeCord channel, for ArenaCommands
		final Messenger m = getServer().getMessenger();
		if (!m.isOutgoingChannelRegistered(this, "BungeeCord"))
			m.registerOutgoingPluginChannel(this, "BungeeCord");
	}

	/**
	 * Get the instance of this library, loaded on the server
	 *
	 * TIP: To start with, see {@link ArenaRegistry} and {@link PlayerData}
	 *
	 * @return this instance
	 */
	public static final GameAPIPlugin getInstance() {
		return (GameAPIPlugin) Bukkit.getPluginManager().getPlugin("GameAPI");
	}
}
