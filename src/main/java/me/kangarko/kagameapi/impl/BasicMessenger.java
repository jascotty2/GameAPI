package me.kangarko.kagameapi.impl;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lombok.Getter;
import lombok.Setter;
import me.kangarko.kagameapi.Arena;
import me.kangarko.kagameapi.ArenaMessenger;
import me.kangarko.kagameapi.type.ArenaSound;
import me.kangarko.kagameapi.type.MessengerTarget;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

public final class BasicMessenger implements ArenaMessenger {

	@Getter
	@Setter
	private MessengerTarget target = MessengerTarget.ARENA;

	private final Arena arena;

	public BasicMessenger(Arena arena) {
		this.arena = arena;
	}

	/**
	 * Only tells the directed players, with the player and other variables
	 */
	@Override
	public final void tell(Player player, String message) {
		player.sendMessage(replaceVariables(message.replace("{player}", player.getName())) );
	}

	/**
	 * Tells all players in the arena, replaces variables
	 */
	@Override
	public final void broadcast(String message) {
		message = replaceVariables(message);

		for (final CommandSender sender : getRecipients())
			sender.sendMessage(message.replace("{player}", sender.getName()));
	}

	@Override
	public final void broadcastBar(String message) {
		final BaseComponent[] comp = TextComponent.fromLegacyText(replaceVariables(message));

		try {
			for (final Player pl : getRecipients())
				pl.spigot().sendMessage(ChatMessageType.ACTION_BAR, comp);

		} catch (final NoSuchMethodError er) {
			broadcast(message);
		}
	}

	@Override
	public void playSound(ArenaSound sound, float pitch) {
		for (final Player pl : getRecipients())
			playSound(pl, sound, pitch);
	}

	@Override
	public void playSound(Player player, ArenaSound sound, float pitch) {
		player.playSound(player.getLocation(), Sound.valueOf(sound.toString()), 1F, pitch);
	}

	@Override
	public final String replaceVariables(String message) {
		return ChatColor.translateAlternateColorCodes('&', message
				.replace("{arena}", arena.getName())
				.replace("{state}", arena.getState().toString().toLowerCase())
				.replace("{phase}", arena.getPhase().getPhase() + "")
				.replace("{players}", getRecipients().size() + "")
				.replace("{maxPlayers}", arena.getSettings().getMaximumPlayers() + "")
				.replace("{minPlayers}", arena.getSettings().getMinimumPlayers() + "")
				);
	}

	private final Collection<? extends Player> getRecipients() {
		switch (target) {
			case ARENA:
				return arena.getPlayers();

			case WORLD:
				return arena.getData().getRegion().getWorld().getPlayers();

			case SERVER:
				return Bukkit.getOnlinePlayers();

			default: throw new RuntimeException("Unhandled target " + target);
		}
	}
}
