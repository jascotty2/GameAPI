package me.kangarko.kagameapi;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * An upgrade in-game enables the players to
 * purchase resources and enhancements during their arena stay. 
 * 
 * The upgrade is one-time buy and you buy all the resources specified
 * below. Those that are not set, are returned null, and are ignored.
 * 
 */
public interface Upgrade {

	/**
	 * The name of this upgrade.
	 */
	public String getName();
	
	/**
	 * The permission to buy this upgrade.
	 */
	public String getPermission();
	
	/**
	 * From which phase is this upgrade available?
	 */
	public int getUnlockPhase();
	
	/**
	 * Which arena class you can buy in this upgrade?
	 */
	//public ArenaClass getArenaClass();
	
	//public void setArenaClass(ArenaClass clazz);	
	
	/**
	 * Custom items to give to the boy.
	 */
	public ItemStack[] getItems();
	
	public void setItems(ItemStack[] items);

	
	public void giveToPlayer(Player pl);
	
	void deleteUpgrade();
}
