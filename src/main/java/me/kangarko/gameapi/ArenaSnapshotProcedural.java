package me.kangarko.gameapi;

import org.bukkit.block.Block;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public interface ArenaSnapshotProcedural extends ArenaSnapshot {

	/**
	 * Returns the block in its stage.
	 */
	//public BlockData getBlock_(ArenaSnapshotStage stage, Location loc);

	/**
	 * Sets the block to its new {@link ArenaSnapshotStage}
	 *
	 * @return The restored block, null if none or the same
	 */
	public Block restoreBlock(Block block, ArenaSnapshotStage stage);

	@RequiredArgsConstructor
	@Getter
	enum DamagedStage implements ArenaSnapshotStage {
		/**
		 * Launch virgin stage.
		 */
		INITIAL(0, "initial", "{arena}"),

		/**
		 * Final damaged stage.
		 */
		DAMAGED(1, "damaged", "{arena}_damaged");

		private final int id;
		private final String formattedName;
		private final String fileName;
	}
}
