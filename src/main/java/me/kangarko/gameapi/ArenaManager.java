package me.kangarko.gameapi;

import java.util.List;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

/**
 * Represents a simple manager of your loaded arenas
 */
public interface ArenaManager {

	/**
	 * Get a copy of all arenas
	 *
	 * @return a copy of all arenas
	 */
	public Set<Arena> getArenas();

	/**
	 * Get a copy of all arena names
	 *
	 * @return a copy of all arena names
	 */
	public List<String> getAvailable();

	/**
	 * Get a player's arena, or null if none
	 *
	 * @param pl the player
	 * @return an arena, or null if not found
	 */
	public Arena findArena(Player pl);

	/**
	 * Get a player's arena, or null if none
	 *
	 * @param pl the player
	 * @return an arena, or null if not found
	 */
	public Arena findArena(String name);

	/**
	 * Get arena at the location, or null if none
	 *
	 * @param loc the location
	 * @return an arena, or null if not found
	 */
	public Arena findArena(Location loc);

	/**
	 * Wrap a sign into an arena sign, if exist
	 *
	 * @param sign the sign
	 * @return an arena sign representation, or null if none
	 */
	public ArenaSign findSign(Sign sign);

	/**
	 * Get if the player is playing in any arena
	 *
	 * @param pl the player
	 * @return true if player has any arena
	 */
	public boolean isPlaying(Player pl);
}
