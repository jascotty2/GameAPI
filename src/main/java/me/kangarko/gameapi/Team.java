package me.kangarko.gameapi;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;

import me.kangarko.gameapi.misc.Iconable;

public interface Team extends Iconable {

	public String getName();

	public String getFormattedName();


	public ChatColor getChatColor();

	public DyeColor getColor();

	public void setColor(DyeColor color);



	public String getPermission();

	public void setPermission(String permission);


	public boolean mayObtain(Player player);

	public void giveTeamItems(Player player);


	public void deleteTeam();
}
