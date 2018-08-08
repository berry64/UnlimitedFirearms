package com.unlimitedfirearms.registry;

import com.unlimitedfirearms.api.GunObject;
import com.unlimitedfirearms.core.BukkitCore;
import org.bukkit.configuration.ConfigurationSection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;

public class WeaponSerializer {
    private static ConcurrentHashMap<Class<? extends GunObject>, Constructor<? extends  GunObject>> constructors =new ConcurrentHashMap<>();

    /**
     * Attempts to retrieve a new GunObject from a Configuration Section
     * Reflection is used in this method so you are not suggested to call this frequently
     *
     * @param sect The configuration to load from
     * @return the GunObject created
     */
    public static GunObject loadWeaponFromConf(ConfigurationSection sect, String key){
        String type = sect.getString("type");
        Class<? extends  GunObject> clz = WeaponsRegistry.getCategoryByName(type);
        if(clz == null){
            BukkitCore.instance.getLogger().severe("Invalid Type: "+type+" in weapon "+key);
            return null;
        }
        if (!constructors.containsKey(clz)){
            try {
                Constructor<? extends GunObject> constructor = clz.getConstructor(ConfigurationSection.class, String.class);
                constructors.put(clz, constructor);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        try {
            return constructors.get(clz).newInstance(sect, key);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
