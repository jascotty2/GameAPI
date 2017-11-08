package me.kangarko.gameapi.utils;

import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import me.kangarko.gameapi.plugin.GameAPIPlugin;

/**
 * Util that is capable of sending messages to/from Bungeecords
 */
public class BungeeMessaging {

	/**
	 * Sends a message upstream to Bungeecords as the BungeeCord channel.
	 *
	 * @param receiver the player through which to send the message
	 * @param datas the datas
	 */
	public static final void sendBungeeMessage(Player receiver, Object... datas) {
		final ByteArrayDataOutput out = ByteStreams.newDataOutput();

		for (final Object data : datas) {
			Objects.requireNonNull(data, "Bungee object in array is null! Array: " + StringUtils.join(datas));

			if (data instanceof Integer)
				out.writeInt( (Integer) data);

			else if (data instanceof Double)
				out.writeDouble( (Double) data);

			else if (data instanceof Boolean)
				out.writeBoolean( (Boolean) data);

			else if (data instanceof String)
				out.writeUTF( (String) data);

			else
				throw new RuntimeException("Unknown type of data: " + data + " (" + data.getClass().getSimpleName() + ")");
		}

		receiver.sendPluginMessage(GameAPIPlugin.getInstance(), "BungeeCord", out.toByteArray());
	}
}
