package com.unlimitedfirearms.listeners;

import com.unlimitedfirearms.api.GunObject;
import com.unlimitedfirearms.registry.WeaponsRegistry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class BukkitListenerCore implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent evt){
        GunObject currentGun = WeaponsRegistry.getGunByItem(evt.getItem());
        if(currentGun != null){
            evt.setCancelled(true);
            currentGun.trigger(evt.getPlayer());
        }
    }
}
