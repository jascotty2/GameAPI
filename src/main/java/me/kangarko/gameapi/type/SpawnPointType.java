package me.kangarko.gameapi.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SpawnPointType {

	MONSTER("Monster"),

	PLAYER("Player"),

	TEAM("Team");

	private final String key;

	@Override
	public String toString() {
		return key;
	}
}