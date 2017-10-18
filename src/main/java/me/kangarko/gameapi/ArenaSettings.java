package me.kangarko.gameapi;

import me.kangarko.gameapi.utils.ArenaMaterialAllower;
import me.kangarko.gameapi.utils.ArenaTrigger;

/**
 * Those are user-alterable settings stored in the arenas/ folder.
 */
public interface ArenaSettings {

	/**
	 * Return which phase activates PvP (player may
	 * kill other players, not just mobs)
	 *
	 * @return from which phase friendly fire is enabled
	 */
	public int getPvpPhase();

	/**
	 * Return how many times the player may get
	 * killed before they loose and get kicked out of the arena.
	 *
	 * @return lifes per player in the arena
	 */
	public int getLifes();

	/**
	 * Get the minimum class tier required to enter
	 *
	 * @return the minimum class tier required to enter
	 */
	public int getMinimumTier();

	/**
	 * Return whether or not the classes are completely
	 * disabled in this arena and players may use their
	 * own equipment from their gameplay.
	 *
	 * @return if arena allows joining with own equipment
	 */
	public boolean allowOwnEquipment();

	/**
	 * Return whether or not the mobs should drop
	 * their natural death items on death?
	 *
	 * @return if arena allows mobs natural drops
	 */
	public boolean allowNaturalDrops();

	/**
	 * Return the maximum players in the arena
	 *
	 * @return the maximum players in the arena
	 */
	public int getMinimumPlayers();

	/**
	 * Return the minimum players in the arena
	 *
	 * @return the minimum players in the arena
	 */
	public int getMaximumPlayers();

	/**
	 * Return the maximum monsters in the arena
	 *
	 * @return the maximum monsters in the arena
	 */
	public int getMobLimit();

	/**
	 * Get the lobby duration, in seconds
	 *
	 * @return the lobby duration, in seconds
	 */
	public int getLobbyDurationSeconds();

	/**
	 * Get the arena duration, in seconds
	 *
	 * @return the arena duration, in seconds
	 */
	public int getArenaDurationSeconds();

	/**
	 * Get the phase duration, in seconds
	 *
	 * @return the phase duration, in seconds
	 */
	public int getPhaseDurationSeconds();

	// -------------------------------------------------------------------------------------
	// Private API
	// -------------------------------------------------------------------------------------

	String getName();

	ArenaData getDataSection();

	ArenaTrigger getChestRefill();

	ArenaMaterialAllower getBreakingList();

	ArenaMaterialAllower getPlaceList();

	void removeSettingsFile();
}
