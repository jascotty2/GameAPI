package me.kangarko.kagameapi;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.kangarko.kagameapi.misc.ConfigSerializable;
import me.kangarko.kagameapi.type.TierMode;

public interface ClassTier extends ConfigSerializable {

	public int getTier();

	@Deprecated
	public void setTier(int tier);


	public int getLevelCost();

	public void setLevelCost(int cost);


	public ItemStack[] getContent();

	public ArmorContent getArmor();

	public void giveToPlayer(Player pl, TierMode mode);

	public void onArenaLeave(Player pl);
}
