package me.kangarko.kagameapi.plugin;

import java.util.List;
import java.util.Set;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import me.kangarko.kagameapi.Arena;
import me.kangarko.kagameapi.ArenaSign;

public interface ArenaManager {

	public Set<Arena> getArenas();

	public List<String> getArenaNames();

	public Arena findArena(Player pl);

	public Arena findArena(String name);

	public ArenaSign findArenaSign(Sign state);

	public boolean isPlaying(Player pl);
}
