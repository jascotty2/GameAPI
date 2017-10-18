package me.kangarko.gameapi.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RewardType {
	
	ITEM("Items"),
	
	BLOCK("Blocks"),
	
	PACK("Packs"),
	
	TIERS("Tiers");
	
	private final String key;
	
	@Override
	public String toString() {
		return key;
	}
}
