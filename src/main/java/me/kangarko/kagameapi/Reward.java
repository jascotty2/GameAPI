package me.kangarko.kagameapi;

import org.bukkit.inventory.ItemStack;

import me.kangarko.kagameapi.type.RewardType;

public interface Reward {

	public RewardType getType();
	
	public int getCost();
	
	public void setCost(int cost);
	
	public ItemStack getItem();
	
	public void setItem(ItemStack item);
}
