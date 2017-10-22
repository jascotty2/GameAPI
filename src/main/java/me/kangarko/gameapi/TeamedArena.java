package me.kangarko.gameapi;

/**
 * Represents an arena with team support
 */
public interface TeamedArena extends Arena {

	public Team getTeam(Player player);

	public Team setTeam(Player player, Team team);
}
