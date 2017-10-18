package me.kangarko.gameapi;

public interface ArenaSnapshot {

	/**
	 * Save the whole arena as a new {@link ArenaSnapshotStage}}.
	 */
	public void take(ArenaSnapshotStage stage);

	/**
	 * Sets the whole arena to a new {@link ArenaSnapshotStage}
	 */
	public void restore(ArenaSnapshotStage stage);

	/**
	 * Is a {@link ArenaSnapshotStage} saved already? This will get rewritten in {@link #save(ArenaSnapshotStage)}
	 */
	public boolean isSaved(ArenaSnapshotStage stage);
}
