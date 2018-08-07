package com.unlimitedfirearms.registry;

import com.unlimitedfirearms.api.GunObject;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ConcurrentHashMap;

public class WeaponsRegistry {
    private static ConcurrentHashMap<String, Class<? extends GunObject>> categories = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<ItemStack, GunObject> weaponsRegistry = new ConcurrentHashMap<>();

    /**
     * Registers a specific weapon by ItemStack
     * @param item the itemstack to register
     * @param weapon the GunObject to link the ItemStack with
     * @return whether the registration is successful
     */
    public static boolean registerWeapon(ItemStack item, GunObject weapon){
        if(weaponsRegistry.containsKey(item))
            return false;
        weaponsRegistry.put(item, weapon);
        return true;
    }

    /**
     * Registers a specific category by a String identifier
     * the identifier is used in configuration to define which class it should use
     * @param id the identifier
     * @param clz the class
     * @return whether the registration is successful
     */
    public static boolean registerCategory(String id, Class<? extends  GunObject> clz){
        if(categories.containsKey(id))
            return false;
        categories.put(id, clz);
        return true;
    }
}
