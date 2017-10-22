package me.kangarko.gameapi.data;

import java.util.HashMap;

import org.bukkit.entity.Player;

/**
 * A section of the data.db
 */
public class PlayerData extends DataFile {

	/**
	 * Registry with stored player data, by players' name.
	 */
	private static final HashMap<String, PlayerData> stored = new HashMap<>();

	/**
	 * Return {@link PlayerData} for a certain player
	 *
	 * @param player the player
	 * @return the player data
	 */
	public static final PlayerData getFor(Player player) {
		return getFor(player.getName());
	}

	/**
	 * Return {@link PlayerData} representation for a player by their name.
	 *
	 * Creates new data, if doesn't exists.
	 *
	 * @param name the player's name
	 * @return the data for the player
	 */
	public static final PlayerData getFor(String name) {
		synchronized (stored) {
			PlayerData data = stored.get(name);

			if (data == null) {
				data = new PlayerData(name);

				stored.put(name, data);
			}

			return data;
		}
	}

	private PlayerData(String name) {
		super("Players." + name);
	}

	/**
	 * The amount of Nuggets, an in-game currency, the players has
	 */
	public final int getNuggets() {
		return getInt("Nuggets", 0);
	}

	/**
	 * Set nuggets to the player
	 *
	 * @param nuggets the nuggets, new value
	 */
	public final void setNuggets(int nuggets) {
		save("Nuggets", nuggets);

		load();
	}
}
