package com.unlimitedfirearms.api;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class GunObject {
    private ItemStack item;

    public GunObject(){
        //TODO moar constructors
    }

    public abstract GunObject deserialize(ConfigurationSection config);

    public ItemStack getItemStack(){
        return item;
    }

    public abstract boolean fire(Player player);

}