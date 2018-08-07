package com.unlimitedfirearms.core;

import com.unlimitedfirearms.api.GunObject;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author LocyDragon/Berry64
 */
public class BaseGunObject extends GunObject {
	public BaseGunObject(String type, String name) {
		super(type, name);
	}

	public BaseGunObject(ConfigurationSection section) {
		super(section);
	}

	@Override
	public ItemStack getItemStack() {
		return null;
	}

	@Override
	public boolean fire(Player player) {
		return false;
	}
}
