package me.kangarko.gameapi;

import org.bukkit.inventory.ItemStack;

import me.kangarko.gameapi.type.RewardType;

public interface Reward {

	public RewardType getType();
	
	public int getCost();
	
	public void setCost(int cost);
	
	public ItemStack getItem();
	
	public void setItem(ItemStack item);
}
