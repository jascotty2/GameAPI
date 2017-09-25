package me.kangarko.kagameapi;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.kangarko.kagameapi.misc.ConfigSerializable;

public interface ArenaSign extends ConfigSerializable {

	public SignType getType();

	public Location getLocation();

	public Arena getArena();

	public void updateState();

	public void onSignOutGameClick(Player player);

	public void onSignInGameClick(Player player);

	public void onSignSetupClick(Player player);

	public void removeSign();

	@RequiredArgsConstructor
	public static enum SignType {
		JOIN("Join"),

		CLASS("Class"),

		UPGRADE("Upgrade"),

		POWER("Power");

		@Getter
		private final String key;

		@Override
		public String toString() {
			return key;
		}
	}
}
