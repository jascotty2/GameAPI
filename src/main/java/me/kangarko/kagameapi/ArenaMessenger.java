package me.kangarko.kagameapi;

import org.bukkit.entity.Player;

import me.kangarko.kagameapi.type.ArenaSound;
import me.kangarko.kagameapi.type.MessengerTarget;

public interface ArenaMessenger {

	public void playSound(ArenaSound sound, float pitch);

	public String replaceVariables(String line);

	public void broadcast(String replace);

	public void tell(Player pl, String replace);

	public void playSound(Player player, ArenaSound sound, float pitch);

	public void broadcastBar(String replace);

	public MessengerTarget getTarget();

	public void setTarget(MessengerTarget target);

}
