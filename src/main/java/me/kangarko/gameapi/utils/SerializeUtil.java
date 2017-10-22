package me.kangarko.gameapi.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import lombok.NonNull;
import me.kangarko.gameapi.misc.ConfigSerializable;

public class SerializeUtil {

	public static final Object serialize(Object obj) {
		if (obj == null)
			return null;

		if (obj instanceof ConfigSerializable)
			return serialize( ((ConfigSerializable) obj).serialize() );

		else if (obj instanceof Location)
			return serializeLoc((Location) obj);

		else if (obj instanceof Enum<?>)
			return obj.toString();

		else if (obj instanceof Iterable) {
			final List<Object> serialized = new ArrayList<>();

			for (final Object element : (Iterable<?>) obj)
				serialized.add( serialize(element) );

			return serialized;
		}

		return obj;
	}

	public static final String serializeLoc(Location loc) {
		return (loc.getWorld().getName() + ", " + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ()) + (loc.getPitch() != 0F || loc.getYaw() != 0F ? ", " + loc.getYaw() + ", " + loc.getPitch() : "");
	}

	public static final <T extends ConfigSerializable> List<Object> serializeList(Iterable<T> array) {
		final List<Object> list = new ArrayList<>();

		for (final T t : array)
			list.add(t != null ? t.serialize() : null);

		return list;
	}

	public static final Location deserializeLocation(Object raw) {
		if (raw == null)
			return null;

		final String[] parts = raw.toString().split(", ");
		Validate.isTrue(parts.length == 4 || parts.length == 6, "Expected location (string) but got: " + raw);

		final String world = parts[0];
		final World bukkitWorld = Bukkit.getWorld(world);
		Validate.isTrue(Bukkit.getWorld(world) != null, "Location with invalid world '" + world + "': " + raw);

		final int x = Integer.parseInt( parts[1] ), y = Integer.parseInt( parts[2] ), z = Integer.parseInt( parts[3] );
		final float yaw = Float.parseFloat( parts.length == 6 ? parts[4] : "0" ), pitch = Float.parseFloat( parts.length == 6 ? parts[5] : "0" );

		return new Location(bukkitWorld, x, y, z, yaw, pitch);
	}

	public static final int deserializeInt(@NonNull Object o) {
		return Double.valueOf(o.toString()).intValue();
	}

	public static final double deserializeDouble(@NonNull Object o) {
		return Double.valueOf(o.toString());
	}

	public static final float deserializeFloat(@NonNull Object o) {
		return Float.valueOf(o.toString());
	}

	public static final boolean deserializeBoolean(@NonNull Object o) {
		return Boolean.valueOf(o.toString());
	}
}
