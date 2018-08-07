package com.unlimitedfirearms.registry;

import com.unlimitedfirearms.api.GunObject;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WeaponsRegistry {
	private static ConcurrentHashMap<String[], Class<? extends GunObject>> categories = new ConcurrentHashMap<>();
	private static ConcurrentHashMap<ItemStack, GunObject> weaponsRegistry = new ConcurrentHashMap<>();

	/**
	 * Registers a specific weapon by ItemStack
	 *
	 * @param item   the itemstack to register
	 * @param weapon the GunObject to link the ItemStack with
	 * @return whether the registration is successful
	 */
	public static boolean registerWeapon(ItemStack item, GunObject weapon) {
		if (weaponsRegistry.containsKey(item)) {
			return false;
		}
		weaponsRegistry.put(item, weapon);
		return true;
	}

	/**
	 * Registers a specific category by a String identifier
	 * the identifier is used in configuration to define which class it should use
	 *
	 * @param clz   the class
	 * @param names the alternative names that the weapon can be written as in type
	 * @return whether the registration is successful
	 */
	public static boolean registerCategory(Class<? extends GunObject> clz, String[] names) {
		for (String[] name : categories.keySet()) {
			for (String s : name) {
				for (String na : names) {
					if (s.equalsIgnoreCase(na)) {
						return false;
					}
				}
			}
		}
		categories.put(names, clz);
		return true;
	}

	/**
	 * Tries to look for
	 *
	 * @param name
	 * @return
	 */
	public static Class<? extends GunObject> getCategoryByName(String name) {
		for (Map.Entry<String[], Class<? extends GunObject>> e : categories.entrySet()) {
			for (String s : e.getKey()) {
				if (s.equalsIgnoreCase(name)) {
					return e.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * Gets the GunObject by an ItemStack from registry, ignores amount
	 * returns null if no corresponding GunObject is found
	 *
	 * @param itemStack the ItemStack to look for
	 * @return the GunObject corresponding with the ItemStack
	 */
	public static GunObject getGunByItem(ItemStack itemStack) {
		for (Map.Entry<ItemStack, GunObject> e : weaponsRegistry.entrySet()) {
			if (e.getKey().isSimilar(itemStack)) {
				return e.getValue();
			}
		}
		return null;
	}

	/**
	 * Gets the GunObject by an ItemStack from registry and looks for the exact match
	 * returns null if no corresponding GunObject is found
	 *
	 * @param itemStack the ItemStack to look for
	 * @return the GunObject corresponding with the ItemStack
	 */
	public static GunObject getGunByItemExact(ItemStack itemStack) {
		return weaponsRegistry.get(itemStack);
	}
}
