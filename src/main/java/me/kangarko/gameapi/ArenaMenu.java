package me.kangarko.gameapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.kangarko.gameapi.event.MenuOpenEvent;
import me.kangarko.gameapi.type.MenuType;

// Currently only used as a workaround to fire MenuOpenEvent
public interface ArenaMenu {

	public default boolean callEvent(Player player) {
		final MenuOpenEvent event = new MenuOpenEvent(getMenuType(), player);
		Bukkit.getPluginManager().callEvent(event);

		return event.isCancelled();
	}

	MenuType getMenuType();
}
