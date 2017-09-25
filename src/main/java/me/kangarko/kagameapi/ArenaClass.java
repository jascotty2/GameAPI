package me.kangarko.kagameapi;

import org.bukkit.entity.Player;

import me.kangarko.kagameapi.misc.Iconable;
import me.kangarko.kagameapi.type.TierMode;
import me.kangarko.kagameapi.utils.TierSettings;

public interface ArenaClass extends Iconable {

	public String getPermission();

	public String getName();

	public void giveToPlayer(Player player, TierMode mode);

	public int getTiers();

	public ClassTier getMinimumTier(int tier);

	public ClassTier getTier(int tier);

	public void updateTier(ClassTier tier);

	public void removeTier(ClassTier tier);

	public TierSettings getTierSettings(int tier);

	public boolean isValid();

	void deleteClass();
}
