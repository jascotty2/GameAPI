package me.kangarko.kagameapi.type;

import java.util.Objects;

import org.bukkit.event.block.Action;

/**
 * Used when player has clicked on a block.
 */
public enum BlockClick {

	RIGHT_CLICK,

	LEFT_CLICK;

	public static BlockClick fromAction(Action action) {
		final BlockClick click = BlockClick.valueOf(action.toString().replace("_BLOCK", ""));
		Objects.requireNonNull(click, "Report / Unsupported click type from " + action);

		return click;
	}
}
