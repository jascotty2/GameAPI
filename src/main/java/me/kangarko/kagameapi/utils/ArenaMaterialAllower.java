package me.kangarko.kagameapi.utils;

import java.util.Set;

import org.apache.commons.lang.Validate;
import org.bukkit.Material;

public class ArenaMaterialAllower {

	private final AllowMode mode;

	private final Set<Material> materials;

	public ArenaMaterialAllower(AllowMode mode) {
		this(null, mode);
	}

	public ArenaMaterialAllower(Set<Material> materials) {
		this(materials, AllowMode.SPECIFIC);
	}

	private ArenaMaterialAllower(Set<Material> materials, AllowMode mode) {
		this.materials = materials;
		this.mode = mode;

		if (materials == null)
			Validate.isTrue(mode != AllowMode.SPECIFIC, "Mode cannot be specific when the list is null");
	}

	public boolean isAllowed(Material material) {
		if (mode == AllowMode.NONE)
			return false;

		if (mode == AllowMode.ALL)
			return true;

		return materials.contains(material);
	}

	public enum AllowMode {
		ALL,

		NONE,

		SPECIFIC
	}
}
