package me.kangarko.gameapi;

import org.bukkit.entity.Player;

import me.kangarko.gameapi.type.ArenaBarColor;

public interface BossBarIndicator {

	public void updateTitle(String title);

	public void updateProgress(double progress);

	public void updateColor(ArenaBarColor c);

	public void showTo(Player pl);

	public void hideFrom(Player pl);

	public boolean hasBar(Player pl);

	public void hide();
}
