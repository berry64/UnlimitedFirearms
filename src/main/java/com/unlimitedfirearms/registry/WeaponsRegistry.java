package com.unlimitedfirearms.registry;

import com.unlimitedfirearms.api.GunBase;

import java.util.HashMap;
import java.util.Map;

public class WeaponsRegistry {
    private static Map<String, GunBase> weaponsRegistry = new HashMap<String, GunBase>();

    public static boolean registerWeapon(String name, GunBase weapon){
        if(weaponsRegistry.containsKey(name))
            return false;
        weaponsRegistry.put(name, weapon);
        return true;
    }
}
