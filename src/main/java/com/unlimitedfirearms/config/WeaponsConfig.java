package com.unlimitedfirearms.config;

import com.unlimitedfirearms.api.GunObject;
import com.unlimitedfirearms.core.BukkitCore;
import com.unlimitedfirearms.registry.WeaponSerializer;
import com.unlimitedfirearms.registry.WeaponsRegistry;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.*;

public class WeaponsConfig {
    /**
     * Loads all weapons(files ending in .yml, .json, and .ufa) in a given folder
     * this should be invoked after all registries have been completed
     *
     * @param weapons the folder containing weapons
     */
    public static void loadWeaps(File weapons){
        if(weapons.listFiles() != null)
            for (File yml: weapons.listFiles()) {
                if(yml.getName().endsWith(".yml") || yml.getName().endsWith(".ufa")){
                    try {
                        YamlConfiguration y = YamlConfiguration.loadConfiguration(
                                new InputStreamReader(new FileInputStream(yml), "utf-8"));
                        for(String key : y.getKeys(false)){
                            ConfigurationSection cs = y.getConfigurationSection(key);
                            GunObject obj = WeaponSerializer.loadWeaponFromConf(cs, key);
                            if(cs.get("type") != null && obj != null){
                                ItemStack is = obj.getItemStack();
                                if(is != null) {
                                    if(!WeaponsRegistry.registerWeapon(is, obj)){
                                        BukkitCore.instance.getLogger().warning("Ignoring "+yml.getName()+"'s "+key+" because another weapon with same key is already loaded");
                                        continue;
                                    }
                                    BukkitCore.instance.getLogger().info("Loaded weapon: "+obj.getName()+" from file: "+yml.getName());
                                } else {
                                    BukkitCore.instance.getLogger().severe("Ignoring "+yml.getName()+"'s "+key+" because the weapon type is invalid");
                                    continue;
                                }
                            } else {
                                BukkitCore.instance.getLogger().severe("Ignoring "+yml.getName()+"'s "+key+" because the weapon type is unknown");
                                continue;
                            }
                            //GunObjectCache.putObject(cs.getString("name"), new BaseGunObject(cs));
                        }
                    } catch (UnsupportedEncodingException | FileNotFoundException e) {
                        BukkitCore.instance.getLogger().severe("Cannot load weapon file "+yml.getName());
                        e.printStackTrace();
                    }
                }
            }
    }
}
