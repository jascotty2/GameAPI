package me.kangarko.gameapi.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * You can use this simple class to save and restore players' inventories.
 */
public class InventoryStorage {

	private static final InventoryStorage instance = new InventoryStorage();

	/**
	 * Get the instance of this inventory storage.
	 *
	 * @return the {@link InventoryStorage} instance
	 */
	public static final InventoryStorage $() {
		return instance;
	}

	private final Map<String, StoredInventory> inventories = new HashMap<>();

	public final void saveInventoryClone(Player pl) {
		//debug("Saving inventory");

		//Validate.isTrue(getStored(pl) == null, "Player " + pl.getName() + " already has stored inventory!");

		final StoredInventory s = StoredInventory.builder()

				.gameMode(pl.getGameMode())

				.armorContent(pl.getInventory().getArmorContents())
				.content(pl.getInventory().getContents())

				.healthScaled(pl.isHealthScaled())

				.remainingAir(pl.getRemainingAir())
				.maximumAir(pl.getMaximumAir())
				.fallDistance(pl.getFallDistance())
				.fireTicks(pl.getFireTicks())

				.totalXp(pl.getTotalExperience())
				.lvl(pl.getLevel())
				.exp(pl.getExp())

				.foodLevel(pl.getFoodLevel())
				.exhaustion(pl.getExhaustion())
				.saturation(pl.getSaturation())

				.flySpeed(pl.getFlySpeed())
				.walkSpeed(pl.getWalkSpeed())

				.potionEffects(pl.getActivePotionEffects())

				.build();

		try {
			s.setMaxHealth(pl.getMaxHealth());
			s.setHealth(pl.getHealth());
			s.setExtraContent( pl.getInventory().getExtraContents() );
			s.setInvulnerable(pl.isInvulnerable());
			s.setSilent(pl.isSilent());
			s.setGlowing(pl.isGlowing());
			s.setScoreboardTags(pl.getScoreboardTags());
		} catch (final NoSuchMethodError err) {}

		inventories.put(pl.getName(), s);
	}

	public final void restore(Player pl) {
		//debug("Restore inventory");

		final StoredInventory s = getStored(pl);
		Validate.isTrue(s != null, "Player " + pl.getName() + " does not have a stored inventory!");

		pl.setGameMode(s.getGameMode());

		pl.getInventory().setArmorContents(s.getArmorContent());
		pl.getInventory().setContents(s.getContent());

		try {
			pl.getInventory().setExtraContents(s.getExtraContent());
		} catch (final NoSuchMethodError err) {}

		pl.setMaxHealth(s.getMaxHealth());
		pl.setHealth(s.getHealth());
		pl.setHealthScaled(s.isHealthScaled());

		pl.setRemainingAir(s.getRemainingAir());
		pl.setMaximumAir(s.getMaximumAir());
		pl.setFallDistance(s.getFallDistance());
		pl.setFireTicks(s.getFireTicks());

		pl.setTotalExperience(s.getTotalXp());
		pl.setExp(s.getExp());
		pl.setLevel(s.getLvl());

		pl.setFoodLevel(s.getFoodLevel());
		pl.setExhaustion(s.getExhaustion());
		pl.setSaturation(s.getSaturation());

		pl.setFlySpeed(s.getFlySpeed());
		pl.setWalkSpeed(s.getWalkSpeed());

		try {
			pl.setGlowing(s.isGlowing());
			pl.setInvulnerable(s.isInvulnerable());
			pl.setSilent(s.isSilent());
		} catch (final NoSuchMethodError err) {}

		// Potions
		for (final PotionEffect ef : pl.getActivePotionEffects())
			pl.removePotionEffect(ef.getType());

		for (final PotionEffect ef : s.getPotionEffects())
			pl.addPotionEffect(ef);

		// Scoreboard tags
		try {
			for (final String tag : s.getScoreboardTags())
				pl.addScoreboardTag(tag);
		} catch (final Throwable err) {}

		inventories.remove(pl.getName());
	}

	public final boolean dumpIfStored(Player pl) {
		if (hasStored(pl)) {
			inventories.remove(pl.getName());

			return true;
		}

		return false;
	}

	public final boolean hasStored(Player pl) {
		return getStored(pl) != null;
	}

	private final StoredInventory getStored(Player pl) {
		return inventories.get(pl.getName());
	}
}

@Builder
@Getter(AccessLevel.PROTECTED)
@Setter
class StoredInventory {

	private GameMode gameMode;

	private ItemStack[] content;
	private ItemStack[] armorContent;
	private ItemStack[] extraContent;

	private double maxHealth;
	private double health;
	private boolean healthScaled;

	private int remainingAir;
	private int maximumAir;

	private float fallDistance;
	private int fireTicks;

	private int totalXp;
	private int lvl;
	private float exp;

	private int foodLevel;
	private float exhaustion;
	private float saturation;

	private float flySpeed;
	private float walkSpeed;

	private boolean glowing;
	private boolean invulnerable;
	private boolean silent;

	private Collection<PotionEffect> potionEffects;
	private Collection<String> scoreboardTags;
}