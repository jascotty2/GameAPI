package me.kangarko.gameapi.utils;

import org.bukkit.potion.PotionEffect;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public final class TierSettings {

	private final int tier;

	private PotionEffect[] potionEffects;

	private String[] permissionsToGive;
}