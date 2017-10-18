package me.kangarko.gameapi;

import java.util.List;

import org.bukkit.Location;

import me.kangarko.gameapi.misc.Iconable;
import me.kangarko.gameapi.type.RegionPoint;
import me.kangarko.gameapi.type.SpawnPointType;

public interface ArenaData extends Iconable {

	public void onPostLoad();

	public Lobby getLobby();

	public void setLobby(Location loc);

	public void removeLobby();

	public ArenaRegion getRegion();

	public void setRegion(Location loc, RegionPoint point);

	public void removeRegion(RegionPoint point);

	public ArenaSigns getSigns();

	public void addSign(ArenaSign sign);

	public void removeSign(Location loc);

	public void removeSign(ArenaSign sign);


	public List<SpawnPoint> getSpawnPoints(SpawnPointType type);

	public SpawnPoint findSpawnPoint(Location loc);

	public void addSpawnPoint(SpawnPoint point);

	public void removeSpawnPoint(SpawnPointType type, Location loc);


	void updateSpawnPoint(SpawnPoint point);

	boolean isDataValid();

	void deleteDataSection();
}
