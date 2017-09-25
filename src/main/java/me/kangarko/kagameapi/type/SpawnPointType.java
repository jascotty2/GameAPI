package me.kangarko.kagameapi.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SpawnPointType {

	MONSTER("Monster"),

	PLAYER("Player");

	private final String key;

	@Override
	public String toString() {
		return key;
	}
}