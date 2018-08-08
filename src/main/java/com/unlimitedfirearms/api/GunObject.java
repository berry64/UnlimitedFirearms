package com.unlimitedfirearms.api;

import com.alibaba.fastjson.JSON;
import org.bukkit.Warning;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ConcurrentHashMap;

public abstract class GunObject {
	protected ConcurrentHashMap<Player, Long> fireCD = new ConcurrentHashMap<>();

	protected String type = "DEFAULT_TYPE";
	protected String name = "DEFAULT_NAME";

	public GunObject(ConfigurationSection section) {
		this.type = section.getString("type");
	}

	@Deprecated
	@Warning(reason = "You are suggested to use GunObject(ConfigurationSection) instead")
	public GunObject(ConfigurationSection section, String name){
		this(section);
		this.name = name;
	}

	public void trigger(Player p, Action a){
		if(fireCD.containsKey(p) && Math.abs(System.currentTimeMillis() - fireCD.get(p)) > getFireDelay() && canFire(p, a)){
			fire(p, a);
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

	public abstract boolean canFire(Player player, Action action);

	public abstract boolean fire(Player player, Action action);

}