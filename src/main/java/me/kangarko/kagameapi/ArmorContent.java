package me.kangarko.kagameapi;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.kangarko.kagameapi.misc.ConfigSerializable;

public interface ArmorContent extends ConfigSerializable {

	public ItemStack getHelmet();

	public ItemStack getChestplate();

	public ItemStack getLeggings();

	public ItemStack getBoots();

	public ItemStack getByOrder(int order);

	public void giveTo(Player pl);
}