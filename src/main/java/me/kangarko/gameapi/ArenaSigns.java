package me.kangarko.gameapi;

import java.util.List;

import org.bukkit.Location;

import me.kangarko.gameapi.ArenaSign.SignType;

public interface ArenaSigns {

	public ArenaSign getSignAt(Location loc);

	public List<ArenaSign> getSigns(SignType type);

	public void updateSigns(SignType type, Arena arena);
}