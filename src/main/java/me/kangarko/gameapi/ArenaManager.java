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
	 * @param player the player
	 * @return an arena, or null if not found
	 */
	public Arena findArena(Player player);

	/**
	 * Get an arena, or null if none
	 *
	 * @param name the arena's name
	 * @return an arena, or null if not found
	 */
	public Arena findArena(String arenaName);

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
	 * Finds the arena that the player is editing
	 *
	 * @param player the player
	 * @return the edited arena or null if none
	 */
	public Arena findEditedArena(Player player);

	/**
	 * Get if the player is playing in any arena
	 *
	 * @param player the player
	 * @return true if player has any arena
	 */
	public boolean isPlaying(Player player);

	/**
	 * Get if the player is editing any arena
	 *
	 * @param player the player
	 * @return true if player is editing any arena
	 */
	public boolean isEditing(Player player);
}
