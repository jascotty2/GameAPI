package me.kangarko.gameapi;

import me.kangarko.gameapi.utils.ArenaCommands;
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
	 * Get when we should stop the arena if there is less or equals players
	 *
	 * @return see above
	 */
	public int getAutoStopPlayersLimit();

	/**
	 * Get the last phase or -1 if not set (arena ends only when time is up).
	 *
	 * @return the last phase, -1 for no end phase
	 */
	public int getLastPhase();

	/**
	 * Get the phase after which arena ends automatically when all monsters are killed.
	 *
	 * @return the end phase no monsters, or -1 if feature disabled
	 */
	public int getEndPhaseNoMonsters();

	/**
	 * Get the last phase or -1 if not set (arena will still continue to play, just
	 * the phase won't increase anymore after it has reached the limit).
	 *
	 * @return the max phase, -1 for no infinite increase
	 */
	public int getMaxPhase();

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
	 * Get the random distance around the mob spawner to spread mobs out.
	 *
	 * @return the random distance around the mob spawner to spread mobs out
	 */
	public int getMobSpread();

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
	 * Get if monsters should burn on the sunlight.
	 * Default: false
	 *
	 * @return if monsters should burn
	 */
	public boolean allowMonstersBurn();

	/**
	 * Place team helmets?
	 *
	 * @return whether or not to place them!
	 */
	public boolean placeTeamHelmets();

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
	public ArenaMaterialAllower getPlaceList();

	/**
	 * Get the commands to be run when arena starts.
	 *
	 * @return the commands.
	 */
	public ArenaCommands getStartCommands();

	/**
	 * Get the commands to be run on the next phase.
	 *
	 * @return the commands.
	 */
	public ArenaCommands getPhaseCommands();

	/**
	 * Get the commands to only be run when arena ends gracefully.
	 *
	 * @return the commands.
	 */
	public ArenaCommands getFinishCommands();

	/**
	 * Get the commands to be run when arena ends for whatever reason.
	 *
	 * @return the commands.
	 */
	public ArenaCommands getEndCommands();

	/**
	 * Get the commands to be run when a player leaves for whatever reason.
	 *
	 * @return the commands.
	 */
	public ArenaCommands getPlayerLeaveCommands();

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
