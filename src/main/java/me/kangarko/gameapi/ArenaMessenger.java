package me.kangarko.gameapi;

import org.bukkit.entity.Player;

import me.kangarko.gameapi.type.ArenaSound;
import me.kangarko.gameapi.type.MessengerTarget;

/**
 * Represents a way to send messages only to the players inside of an {@link Arena}
 *
 * Variables are automatically replaced by {@link #replaceVariables(String)}
 */
public interface ArenaMessenger {


	/**
	 * Send a message to a player
	 *
	 * @param pl the player
	 * @param message the message
	 */
	public void tell(Player pl, String message);

	/**
	 * Send a message to {@link #getTarget()}
	 *
	 * @param message the message
	 */
	public void broadcast(String message);

	/**
	 * Send a message in form of an action bar to {@link #getTarget()}
	 *
	 * @param message the message
	 */
	public void broadcastBar(String message);

	/**
	 * Broadcast a sound to {@link #getTarget()}
	 *
	 * @param sound the {@link ArenaSound}
	 * @param pitch the pitch
	 */
	public void playSound(ArenaSound sound, float pitch);

	/**
	 * Broadcast a sound to a player
	 *
	 * @param player the player
	 * @param sound the sound
	 * @param pitch the pitch
	 */
	public void playSound(Player player, ArenaSound sound, float pitch);

	/**
	 * Return to whom will messages be broadcasted
	 *
	 * @return the target
	 */
	public MessengerTarget getTarget();

	/**
	 * Set to whom to broadcast messages
	 *
	 * @param target then new target
	 */
	public void setTarget(MessengerTarget target);

	/**
	 * Internal method to replace variables by your plugin
	 *
	 * @param message the message to replace
	 * @return translated message
	 */
	public String replaceVariables(String message);
}
