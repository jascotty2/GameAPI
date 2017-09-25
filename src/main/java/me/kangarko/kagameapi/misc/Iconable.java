package me.kangarko.kagameapi.misc;

import org.bukkit.inventory.ItemStack;

public interface Iconable {

	public boolean hasIcon();
	
	public void setIcon(ItemStack icon);

	public ItemStack getIcon();
}
