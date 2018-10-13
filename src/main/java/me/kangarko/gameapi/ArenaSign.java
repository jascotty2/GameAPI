package me.kangarko.gameapi;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.kangarko.gameapi.misc.ConfigSerializable;

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
		LEAVE("Leave"),
		CLASS("Class"),
		TEAM("Team"),
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
