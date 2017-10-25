package me.kangarko.gameapi.plugin;

import org.bukkit.plugin.java.JavaPlugin;

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
	}

	/**
	 * Get the instance of this library, loaded on the server
	 *
	 * TIP: To start with, see {@link ArenaRegistry} and {@link PlayerData}
	 *
	 * @return this instance
	 */
	public static final GameAPIPlugin getInstance() {
		return GameAPIPlugin.getPlugin(GameAPIPlugin.class);
	}
}
