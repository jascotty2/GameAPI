package me.kangarko.gameapi;

import org.bukkit.entity.Player;

public interface ArenaPhase {

	public int getPhase();

	public void startCounter();

	public void enterNextPhase();

	public void onPhaseTick();

	public void stopAndReset();

	void onSnapshotUpdate(boolean save, ArenaSnapshotStage stage);

	void onPlayerLeave(Player pl);
}
