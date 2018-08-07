package com.unlimitedfirearms.api;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class GunObject {
    public GunObject(ConfigurationSection cfg){
        String type = cfg.getString("type");

    }

   /* enum AttatchmentLocation{
        RAIL,
        SIGHT,
        BARREL,
        LOWER_RECIEVER
    }

    public abstract boolean addAttatchment(AttatchmentLocation slot, AttatchmentObject obj);
*/
    public abstract ItemStack getItemStack();

    public abstract boolean fire(Player player);

}