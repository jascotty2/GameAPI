package me.kangarko.gameapi;

import java.util.List;

import org.bukkit.entity.Player;

/**
 * Represents an arena with team support
 */
public interface TeamedArena extends Arena {

	public List<Player> getStartingTeamPlayers(Team team);

	public List<Player> getTeamPlayers(Team team);

	public Team getTeam(Player player);

	public void assignTeam(Player player, Team team);
}
