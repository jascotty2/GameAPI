package me.kangarko.kagameapi;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import me.kangarko.kagameapi.misc.ConfigSerializable;

/**
 * The protected arena region
 */
public interface ArenaRegion extends ConfigSerializable {

	public Location getPrimary();

	public Location getSecondary();

	public default Location getCenter() {
		return new Location(
				getPrimary().getWorld(),
				(getPrimary().getX() + getSecondary().getX()) / 2,
				(getPrimary().getY() + getSecondary().getY()) / 2,
				(getPrimary().getZ() + getSecondary().getZ()) / 2);
	}

	public Block[] getBlocks();

	public Entity[] getEntities();

	public default World getWorld() {
		return getPrimary().getWorld();
	}

	public boolean isWithin(Location loc);
}
