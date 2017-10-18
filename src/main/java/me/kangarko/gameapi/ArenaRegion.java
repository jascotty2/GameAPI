package me.kangarko.gameapi;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import me.kangarko.gameapi.misc.ConfigSerializable;

/**
 * The protected arena region
 */
public interface ArenaRegion extends ConfigSerializable {

	public Location getPrimary();

	public Location getSecondary();

	public default Location getCenter() {
		return getWorld() != null ? new Location(
				getPrimary().getWorld(),
				(getPrimary().getX() + getSecondary().getX()) / 2,
				(getPrimary().getY() + getSecondary().getY()) / 2,
				(getPrimary().getZ() + getSecondary().getZ()) / 2) : null;
	}

	public Block[] getBlocks();

	public Entity[] getEntities();

	public default World getWorld() {
		return getPrimary() != null ? getPrimary().getWorld() : null;
	}

	public boolean isWithin(Location loc);
}
