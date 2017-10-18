package me.kangarko.gameapi.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RegionPoint {
	PRIMARY("Primary"),
	SECONDARY("Secondary");

	private final String key;

	@Override
	public String toString() {
		return key;
	}

	public static RegionPoint fromClick(BlockClick click) {
		if (click == BlockClick.LEFT_CLICK)
			return PRIMARY;

		else if (click == BlockClick.RIGHT_CLICK)
			return RegionPoint.SECONDARY;

		throw new RuntimeException("Unhandled region point from click " + click);
	}
}