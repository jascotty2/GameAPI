package me.kangarko.kagameapi.impl;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
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

import lombok.RequiredArgsConstructor;
import me.kangarko.kagameapi.Arena;
import me.kangarko.kagameapi.ArenaMessenger;
import me.kangarko.kagameapi.ArenaSnapshotStage;
import me.kangarko.kagameapi.cause.DeathCause;
import me.kangarko.kagameapi.cause.JoinCause;
import me.kangarko.kagameapi.cause.LeaveCause;
import me.kangarko.kagameapi.cause.StopCause;
import me.kangarko.kagameapi.event.ArenaJoinEvent;
import me.kangarko.kagameapi.event.ArenaLeaveEvent;
import me.kangarko.kagameapi.event.ArenaPreJoinEvent;
import me.kangarko.kagameapi.event.ArenaPreLeaveEvent;
import me.kangarko.kagameapi.event.ArenaStartEvent;
import me.kangarko.kagameapi.event.ArenaStopEvent;
import me.kangarko.kagameapi.event.LobbyStartEvent;
import me.kangarko.kagameapi.type.ArenaState;
import me.kangarko.kagameapi.utils.ExpItemTag;

/**
 * A very simple implementation of the Arena, firing
 * events automatically on player join or quit, lobby start and arena start and end.
 */
@RequiredArgsConstructor
public abstract class DummyArena implements Arena {

	/**
	 * The name of your arena
	 */
	private final String name;

	/**
	 * Your plugin that creates the arena
	 */
	private final String plugin;

	/**
	 * The inbuilt messenger for sending messages.
	 */
	private final ArenaMessenger messenger = new BasicMessenger(this);

	/**
	 * Arena state.
	 */
	private ArenaState state = ArenaState.STOPPED;

	/**
	 * An internal flag to workaround some stuff.
	 */
	private boolean stopping;

	@Override
	public final boolean joinPlayer(Player pl, JoinCause cause) {
		if (!callEvent(new ArenaPreJoinEvent(this, cause, pl)))
			return false;

		final boolean success = onPostJoin(pl, cause);

		callEvent(new ArenaJoinEvent(this, cause, pl));
		return success;
	}

	/**
	 * Called after the player attempts to join,
	 * and the {@link ArenaPreJoinEvent} has been fired and not cancelled.
	 *
	 * @param player the player
	 * @param cause the cause
	 */
	protected abstract boolean onPostJoin(Player player, JoinCause cause);

	@Override
	public final boolean kickPlayer(Player pl, LeaveCause cause) {
		if (!callEvent(new ArenaPreLeaveEvent(this, cause, pl)))
			return false;

		final boolean success = onPostLeave(pl, cause);

		callEvent(new ArenaLeaveEvent(this, cause, pl));
		return success;
	}

	/**
	 * Called after the player attempts to quit,
	 * and the {@link ArenaPreLeaveEvent} has been fired and not cancelled.
	 *
	 * @param player the player
	 * @param cause the cause
	 */
	protected abstract boolean onPostLeave(Player player, LeaveCause cause);

	@Override
	public final void onPostLoad() {
	}

	@Override
	public final void teleportPlayerToSpawn(Player pl) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public final void onSnapshotUpdate(boolean save, ArenaSnapshotStage stage) {
	}

	@Override
	public final boolean onArenaStart() {
		state = ArenaState.RUNNING;

		callEvent(new ArenaStartEvent(this));
		return onPostStart();
	}

	/**
	 * Called after arena starts and the {@link ArenaStartEvent} has been fired.
	 */
	protected abstract boolean onPostStart();

	@Override
	public final void onLobbyStart() {
		state = ArenaState.LOBBY;

		Bukkit.getPluginManager().callEvent(new LobbyStartEvent(this));
	}

	@Override
	public final void onArenaStop(StopCause cause) {
		if (stopping)
			return;

		state = ArenaState.STOPPED;
		stopping = true;

		Bukkit.getPluginManager().callEvent(new ArenaStopEvent(this, cause));

		try {
			onPostStop();
		} finally {
			stopping = false;
		}
	}

	/**
	 * Called when the arena ends and the {@link ArenaStopEvent} has been fired.
	 */
	protected abstract void onPostStop();

	@Override
	public void onPlayerPvP(EntityDamageByEntityEvent e, Player damager, Player victim, double damage) {
	}

	@Override
	public void onPlayerPvE(Player damager, LivingEntity victim, double damage) {
	}

	@Override
	public void onPlayerDamage(EntityDamageByEntityEvent e, Player player, Entity source, double damage) {
	}

	@Override
	public void onPlayerBlockDamage(EntityDamageByBlockEvent e, Player player, double damage) {
	}

	@Override
	public void onPlayerDeath(Player pl, Player killer) {
	}

	@Override
	public void onPlayerDeath(Player pl, DeathCause cause) {
	}

	@Override
	public void onPlayerClick(Player pl, Block clickedBlock, ItemStack hand) {
	}

	@Override
	public void onPlayerClickAir(Player pl, ItemStack hand) {
	}

	@Override
	public void onPlayerBlockPlace(BlockPlaceEvent e) {
	}

	@Override
	public void onPlayerBlockBreak(BlockBreakEvent e) {
	}

	@Override
	public void onEntityTarget(EntityTargetEvent e) {
	}

	@Override
	public void onEntityDeath(EntityDeathEvent e) {
	}

	@Override
	public void onPlayerRespawn(PlayerRespawnEvent e) {
	}

	@Override
	public void onPlayerPickupTag(PlayerPickupItemEvent e, ExpItemTag tag) {
	}

	@Override
	public void onProjectileHit(ProjectileHitEvent e) {
	}

	@Override
	public void onProjectileLaunch(ProjectileLaunchEvent e) {
	}

	/**
	 * An utility method to call events.
	 *
	 * @param e the event
	 * @return true if the event was not cancelled, meaning it has passed.
	 */
	protected final boolean callEvent(org.bukkit.event.Event e) {
		Bukkit.getPluginManager().callEvent(e);

		return e instanceof Cancellable ? !((Cancellable)e).isCancelled() : true;
	}

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public final String getPlugin() {
		return plugin;
	}

	@Override
	public final ArenaMessenger getMessenger() {
		return messenger;
	}

	@Override
	public final boolean isStopping() {
		return stopping;
	}

	@Override
	public final ArenaState getState() {
		return state;
	}
}
