package me.kangarko.gameapi;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

public interface Setup {

	public void onEnterEditMode(Player byWhom);

	public void onLeaveEditMode(Player player);

	public void onSetupClick(Player pl, Action action, Block clickedBlock);

	// Methods
	// -----------------------------------------------------

	/** can be null */
	public Player getEditor();

	public boolean areJoinSignsSet();

	public boolean isEdited();

	public boolean isLobbySet();

	public boolean isRegionSet();

	public boolean isPlayerSpawnpointSet();

	public boolean isReady();
}