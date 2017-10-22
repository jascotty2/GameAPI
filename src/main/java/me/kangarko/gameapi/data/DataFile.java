package me.kangarko.gameapi.data;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.YamlConfiguration;

import me.kangarko.gameapi.misc.ConfigSerializable;
import me.kangarko.gameapi.plugin.GameAPIPlugin;
import me.kangarko.gameapi.utils.SerializeUtil;

/**
 * Represents the data.db, a YAML configuration file.
 */
@SuppressWarnings("unchecked")
public class DataFile {

	public static final String[] HEADER = new String[] {
			"",
			"This file stores various data you create via the plugin.",
			"",
			" ** THE FILE IS MACHINE GENERATED. PLEASE DO NOT EDIT **",
			""
	};

	/**
	 * The file this class belongs to.
	 */
	private final File file;

	/**
	 * The configuration
	 */
	private final YamlConfiguration config;

	/**
	 * The default hardcoded path prefix
	 */
	private final String initialPathPrefix;

	/**
	 * The beginning of each section, automatically inserted, can be edited upstream
	 */
	private String tempPathPrefix;

	/**
	 * Make a data.db file in GameAPI/ folder with a path prefix
	 *
	 * Loads config automatically.
	 *
	 * @param pathPrefix
	 * @param header
	 */
	public DataFile(String pathPrefix) {
		this.file = new File(GameAPIPlugin.getInstance().getDataFolder(), "data.db");
		this.config = new YamlConfiguration();
		this.initialPathPrefix = pathPrefix;

		load();
		onDataLoad();
	}

	/**
	 * Load or re-loads the configuration from the file.
	 */
	public final void load() {
		try {
			if (!file.exists())
				file.createNewFile();

			config.load(file);

		} catch (IOException | InvalidConfigurationException e) {
			System.out.println("Error loading " + file.getName());
			e.printStackTrace();
		}
	}

	/**
	 * Called when the class is initialized.
	 *
	 * Please mind that the configuration may not contain (all) values.
	 */
	protected void onDataLoad() {}

	/**
	 * Add's a path prefix, so when you call getX(path) it will be automatically added to the path.
	 *
	 * @param localPrefix a new path prefix
	 */
	protected final void pathPrefix(String localPrefix) {
		this.tempPathPrefix = localPrefix == null || localPrefix.isEmpty() ? null : localPrefix;
	}

	// ----------------------------------------------------------------
	// Getters
	// ----------------------------------------------------------------

	/**
	 * Get an integer or a null if not set
	 *
	 * @param path
	 * @param def
	 * @return
	 */
	protected final Integer getInt(String path) {
		return isSet(path) ? getInt(formPathPrefix(path), -1) : null;
	}

	/**
	 * Get an integer or a default value if not set
	 *
	 * @param path
	 * @param def
	 * @return
	 */
	protected final int getInt(String path, int def) {
		return config.getInt(formPathPrefix(path), def);
	}

	/**
	 * Get a double or a null if not set
	 *
	 * @param path
	 * @param def
	 * @return
	 */
	protected final Double getDouble(String path) {
		return isSet(path) ? getDouble(formPathPrefix(path), -1.0) : null;
	}

	/**
	 * Get a double or a default value if not set
	 *
	 * @param path
	 * @param def
	 * @return
	 */
	protected final double getDouble(String path, double def) {
		return config.getDouble(formPathPrefix(path), def);
	}

	/**
	 * Get a boolean or a null if not set
	 *
	 * @param path
	 * @return
	 */
	protected final Boolean getBoolean(String path) {
		return isSet(path) ? config.getBoolean(formPathPrefix(path)) : null;
	}

	/**
	 * Get a List<String> or a null if not set
	 *
	 * @param path
	 * @return
	 */
	protected final List<String> getStringList(String path) {
		return config.getStringList(formPathPrefix(path));
	}

	/**
	 * Get a string or a null if not set
	 *
	 * @param path
	 * @return
	 */
	protected final String getString(String path) {
		return config.getString(formPathPrefix(path));
	}

	/**
	 * Get keys of a certain configuration section or a null if not set
	 *
	 * @param path
	 * @return
	 */
	protected final Set<String> getKeys(String path) {
		return isSet(path) ? config.getConfigurationSection(formPathPrefix(path)).getKeys(false) : null;
	}

	/**
	 * Get unspecified object or a null if not set
	 *
	 * @param path
	 * @return
	 */
	protected final Object get(String path) {
		return config.get(formPathPrefix(path));
	}

	/**
	 * Get an unspecified array list or a null if not set
	 *
	 * @param path
	 * @return
	 */
	protected final ArrayList<?> getList(String path) {
		final Object obj = get(path);

		return obj != null && obj instanceof ArrayList ? (ArrayList<?>) obj : null;
	}

	/**
	 * Get a location or a null if not set.
	 *
	 * We use our serialization system for that, so the location is put on one line.
	 *
	 * @param path
	 * @return
	 */
	protected final Location getLocation(String path) {
		Objects.requireNonNull(path);

		return SerializeUtil.deserializeLocation(getString(path));
	}

	/**
	 * Get a map or a null if not set
	 *
	 * @param path
	 * @return
	 */
	protected final Map<String, Object> getMap(String path) {
		return isSet(path) ? getMapFromSection(get(path)) : null;
	}

	// ----------------------------------------------------------------
	// Utils
	// ----------------------------------------------------------------

	private final Map<String, Object> getMapFromSection(Object mapOrSection) {
		try {
			final Map<String, Object> map = (Map<String, Object>) (mapOrSection instanceof Map ? mapOrSection : mapOrSection instanceof MemorySection ? getFieldContent("map", mapOrSection) : null);
			Objects.requireNonNull(map, "Unexpected " + mapOrSection.getClass() + ": " + mapOrSection);

			return map;

		} catch (final ReflectiveOperationException ex) {
			throw new RuntimeException(ex);
		}
	}

	private Object getFieldContent(String field, Object instance) throws ReflectiveOperationException {
		final Field f = instance.getClass().getDeclaredField("field");
		f.setAccessible(true);

		return f.get(instance);
	}

	// ----------------------------------------------------------------
	// Functions
	// ----------------------------------------------------------------

	/**
	 * Put a value in the config and save (write to the file)
	 *
	 * @param path
	 * @param object
	 */
	protected final void save(String path, Object object) {
		setNoSave(path, object);

		saveData();
	}

	/**
	 * Put a value in the config. No saving.
	 *
	 * NB: We serialize your object automatically, see {@link SerializeUtil}.
	 * Please make it extend {@link ConfigSerializable} to be serialized automatically.
	 *
	 * @param path
	 * @param object object, serialized
	 */
	protected final void setNoSave(String path, Object object) {
		object = SerializeUtil.serialize(object);

		config.set(formPathPrefix(path), object);
	}

	/**
	 * Get whether a path is set in the config
	 *
	 * @param path
	 * @return
	 */
	protected final boolean isSet(String path) {
		return config.isSet(formPathPrefix(path));
	}

	/**
	 * Save the config with a {@link #header}
	 */
	protected final void saveData() {
		{ // Copy header
			config.options().header(StringUtils.join(HEADER, System.lineSeparator()));
			config.options().copyHeader(true);
		}

		try {
			config.save(file);

		} catch (final IOException ex) {
			System.out.println("Error saving " + file.getName());
			ex.printStackTrace();
		}
	}

	/**
	 * Delete the file permanently.
	 *
	 * @return
	 */
	public final boolean deleteFile() {
		return file.delete();
	}

	private final String formPathPrefix(String myPath) {
		String path = "";

		if (initialPathPrefix != null)
			path += initialPathPrefix + ".";

		if (tempPathPrefix != null)
			path += tempPathPrefix + ".";

		path+= myPath;
		path = path.endsWith(".") ? path.substring(0, path.length() - 1) : path;

		return path;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{file=" + file.getPath() + "}";
	}
}
