package com.unlimitedfirearms.gui;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class UIInvHolder implements InventoryHolder {
    @Override
    public Inventory getInventory() {
        return Bukkit.createInventory(this, 72, "You are not supposed to be here!");
    }
}
