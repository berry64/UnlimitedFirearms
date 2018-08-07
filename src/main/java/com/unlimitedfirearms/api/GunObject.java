package com.unlimitedfirearms.api;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class GunObject {
    ItemStack item;

    public GunObject(){
        item = new ItemStack(Material.IRON_HOE);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName("§aHELLO TEST GUNz");
        item.setItemMeta(m);
    }

    public ItemStack getItemStack(){
        return item;
    }

    public abstract boolean fire(Player player);

}