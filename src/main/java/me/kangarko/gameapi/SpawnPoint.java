package me.kangarko.gameapi;

import org.bukkit.Location;

import me.kangarko.gameapi.misc.ConfigSerializable;
import me.kangarko.gameapi.type.SpawnPointType;

public interface SpawnPoint extends ConfigSerializable {

	public Location getLocation();

	public SpawnPointType getType();
}
