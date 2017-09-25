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

	public Location getCenter();

	public Block[] getBlocks();

	public Entity[] getEntities();

	public World getWorld();

	public boolean isWithin(Location loc);
}
