package me.kangarko.gameapi;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.kangarko.gameapi.misc.ConfigSerializable;

public interface ArmorContent extends ConfigSerializable {

	public ItemStack getHelmet();

	public ItemStack getChestplate();

	public ItemStack getLeggings();

	public ItemStack getBoots();

	public ItemStack getByOrder(int order);

	public void giveTo(Player pl);
}