package com.unlimitedfirearms.core.weapons;

import com.unlimitedfirearms.api.GunObject;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AssultRifle extends GunObject {
    private String name;
    private Object example_properties;
    private ItemStack item;
    private ConfigurationSection sect;

    public AssultRifle(ConfigurationSection section) {
        super(section);
        sect = section;
        //到这里type绝对是AssultRifle或者类似的别名
        name = section.getString("name");
        example_properties = section.get("blah");

        item = new ItemStack(Material.IRON_HOE);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName(name);
        item.setItemMeta(m);
    }

    @Override
    public ConfigurationSection getConfig() {
        return sect;
    }

    @Override
    protected long getFireDelay() {
        return 1000;
    }

    @Override
    public ItemStack getItemStack() {
        return item;
    }

    @Override
    public boolean canFire(Player player) {
        return true;
    }

    @Override
    public boolean fire(Player player) {
        player.sendMessage("pew pew pew");
        return false;
    }
}
