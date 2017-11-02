package me.kangarko.gameapi;

import java.util.List;

import me.kangarko.gameapi.utils.ArenaMaterialAllower;
import me.kangarko.gameapi.utils.ArenaTrigger;

/**
 * Those are user-alterable settings stored in the arenas/ folder.
 */
public interface ArenaSettings {

	/**
	 * Get the arena name.
	 *
	 * @return the arena name
	 */
	public String getName();

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
	 * Should players be teleported to a random spawnpoint, or to the first one
	 * from when the arena started?
	 *
	 * @return whether respawning should teleport to random spawnpoints
	 */
	public boolean isRespawningRandom();

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

	/**
	 * Get an implementation of when chests should be refillen.
	 *
	 * @return the chest refill trigger
	 */
	public ArenaTrigger getChestRefill();

	/**
	 * Get material allower for things that can be broken.
	 *
	 * @return a list of things that can be broken
	 */
	public ArenaMaterialAllower getBreakingList();

	/**
	 * Get material allower for things that can be placed.
	 *
	 * @return a list of things that can be placed
	 */
	ArenaMaterialAllower getPlaceList();

	/**
	 * Get the commands to be run as the player when arena ends.
	 *
	 * @return the commands to be run as the player when arena ends.
	 */
	public List<String> getEndCommands();

	// -------------------------------------------------------------------------------------
	// Features for specific plugins or that needs to be implemented in those plugins
	// -------------------------------------------------------------------------------------

	/**
	 * Calculate team reward depending on how many teams are left when arena ended.
	 *
	 * @param teamsLeft how many winning teams there are
	 * @return the reward for the parameter
	 */
	public Integer getTeamReward(int teamsLeft);

	/**
	 * Get the y-height for which players are killed.
	 * Only works if the plugin supports it (for ex. Puncher)
	 *
	 * @return the kill height
	 */
	public int getKillHeight();

	// -------------------------------------------------------------------------------------
	// Private API
	// -------------------------------------------------------------------------------------

	/**
	 * Get the internal data section
	 *
	 * @return the internal data section
	 */
	ArenaData getDataSection();

	/**
	 * Delete the settings file.
	 *
	 * This keeps arena registered, please use method in arena manager to remove arena!
	 */
	void removeSettingsFile();
}
