package me.kangarko.gameapi;

import java.util.Collection;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import me.kangarko.gameapi.cause.DeathCause;
import me.kangarko.gameapi.cause.JoinCause;
import me.kangarko.gameapi.cause.LeaveCause;
import me.kangarko.gameapi.cause.StopCause;
import me.kangarko.gameapi.event.ArenaPreLeaveEvent;
import me.kangarko.gameapi.type.ArenaState;
import me.kangarko.gameapi.utils.ExpItemTag;
public interface Arena {

	/**
	 * Attempts to join a player to this arena.
	 *
	 * @param pl the player
	 * @param cause why is the player joining
	 * @throws EventHandledException when the event is handled in the pipeline
	 *
	 * @return if the player was joined, false if conditions didn't allow it
	 */
	public boolean joinPlayer(Player pl, JoinCause cause);

	/**
	 * Attempts to kick a player from this arena or
	 * throws an error when he's not playing or the arena is not running.
	 *
	 * @param pl the player
	 * @param cause why is the player quitting
	 * @return if the player was kicked, false if the {@link ArenaPreLeaveEvent} was cancelled
	 */
	public boolean kickPlayer(Player pl, LeaveCause cause);

	// ----------------------------------------------------------------------------------------
	// Main API methods
	// ----------------------------------------------------------------------------------------

	/**
	 * The name of the arena, as specified in the config.
	 */
	public String getName();

	/**
	 * List of player names joined in the arena. This does not mean they are playing, it means
	 * they are registered in the arena, for example in the lobby.
	 */
	public Collection<Player> getPlayers();

	/**
	 * The game state.
	 */
	public ArenaState getState();

	/**
	 * The arena's settings that user can alter.
	 */
	public ArenaSettings getSettings();

	/**
	 * The internal data from database.
	 */
	public ArenaData getData();

	/**
	 * The thing for sending messages.
	 */
	public ArenaMessenger getMessenger();

	/**
	 * Snapshot is all the blocks in the arena in a certain phase.
	 */
	public ArenaSnapshot getSnapshot();

	/**
	 * The setup manager, for example spawn points or supply points, etc.
	 */
	public Setup getSetup();

	/**
	 * Phases are levels within the arena
	 */
	public ArenaPhase getPhase();

	/**
	 * Has the stopping been initiated in the pipeline?
	 */
	public boolean isStopping();

	/**
	 * How much seconds is left before the finish?
	 *
	 * @return the remaining time, in seconds
	 */
	public int getRemainingSeconds();

	/**
	 * The plugin that owns this arena.
	 *
	 * @return the plugin
	 */
	public ArenaPlugin getPlugin();

	// ----------------------------------------------------------------------------------------
	// Private API methods
	// ----------------------------------------------------------------------------------------

	void onPostLoad();

	void teleportPlayerToSpawn(Player pl);

	void onSnapshotUpdate(boolean save, ArenaSnapshotStage stage);

	/** Returns false if the arena could not be started */
	boolean onArenaStart();

	void onLobbyStart();

	void onArenaStop(StopCause cause);

	void onPlayerPvP(EntityDamageByEntityEvent e, Player damager, Player victim, double damage);

	void onPlayerPvE(Player damager, LivingEntity victim, double damage);

	void onPlayerDamage(EntityDamageByEntityEvent e, Player player, Entity source, double damage);

	void onPlayerBlockDamage(EntityDamageByBlockEvent e, Player player, double damage);

	void onPlayerDeath(Player pl, Player killer);

	void onPlayerDeath(Player pl, DeathCause cause);

	void onPlayerClick(Player pl, Block clickedBlock, ItemStack hand);

	void onPlayerClickAir(Player pl, ItemStack hand);

	void onPlayerBlockPlace(BlockPlaceEvent e);

	void onPlayerBlockBreak(BlockBreakEvent e);

	void onEntityTarget(EntityTargetEvent e);

	void onEntityDeath(EntityDeathEvent e);

	void onPlayerRespawn(PlayerRespawnEvent e);

	void onPlayerPickupTag(PlayerPickupItemEvent e, ExpItemTag tag);

	default void onProjectileLaunch(ProjectileLaunchEvent e) {
	}

	default void onProjectileHit(ProjectileHitEvent e) {
	}
}
