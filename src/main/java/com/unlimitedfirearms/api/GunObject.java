package com.unlimitedfirearms.api;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class GunObject {
    public GunObject(){
    }


    public static GunObject deserialize(ConfigurationSection config) {
        return null;
    }

    public abstract ItemStack getItemStack();

    public abstract boolean fire(Player player);

}