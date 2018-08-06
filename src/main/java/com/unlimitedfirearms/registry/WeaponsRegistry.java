package com.unlimitedfirearms.registry;

import com.unlimitedfirearms.api.GunBase;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ConcurrentHashMap;

public class WeaponsRegistry {
    private static ConcurrentHashMap<String, Class<? extends  GunBase>> categories = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<ItemStack, GunBase> weaponsRegistry = new ConcurrentHashMap<>();

    public static boolean registerWeapon(ItemStack item, GunBase weapon){
        if(weaponsRegistry.containsKey(item))
            return false;
        weaponsRegistry.put(item, weapon);
        return true;
    }

    public static boolean registerCategory(String id, Class<? extends  GunBase> clz){
        if(categories.containsKey(id))
            return false;
        categories.put(id, clz);
        return true;
    }
}
