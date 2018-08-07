package com.unlimitedfirearms.api;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class GunObject {
    private ItemStack item;

    public GunObject(){

    }

    public ItemStack getItemStack(){
        return item;
    }

    public abstract boolean fire(Player player);

}