package com.unlimitedfirearms.api;

import com.alibaba.fastjson.JSON;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ConcurrentHashMap;

public abstract class GunObject {
	protected ConcurrentHashMap<Player, Long> fireCD = new ConcurrentHashMap<>();

	protected String type = "DEFAULT_TYPE";
	protected String name = "DEFAULT_NAME";

	public GunObject(ConfigurationSection section) {
		this.type = section.getString("type");
	}
	public GunObject(ConfigurationSection section, String name){
		this(section);
		this.name = name;
	}

	public void trigger(Player p){
		if(fireCD.containsKey(p) && Math.abs(System.currentTimeMillis() - fireCD.get(p)) > getFireDelay() && canFire(p)){
			fire(p);
		}
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public abstract ConfigurationSection getConfig();

	public static GunObject fromJSON(String json) {
		return JSON.parseObject(json, GunObject.class);
	}

	protected abstract long getFireDelay();

	public abstract ItemStack getItemStack();

	public abstract boolean canFire(Player player);

	public abstract boolean fire(Player player);

}