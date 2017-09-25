package me.kangarko.kagameapi;

import org.bukkit.Location;

import me.kangarko.kagameapi.misc.ConfigSerializable;
import me.kangarko.kagameapi.type.SpawnPointType;

public interface SpawnPoint extends ConfigSerializable {

	public Location getLocation();

	public SpawnPointType getType();
}
