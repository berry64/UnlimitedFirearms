package com.unlimitedfirearms.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class GunObject {
	public String type = "assultrifle";
	public String name = "NORMAL_NAME";

	private GunObject() {
	}

	public GunObject(String type, String name) {
		this.type = type;
		this.name = name;
	}

	public GunObject(ConfigurationSection section) {
		this.type = section.getString("type");
		this.name = section.getString("name");
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public GunObject setType(String type) {
		this.type = type;
		return this;
	}

	public GunObject setName(String name) {
		this.name = name;
		return this;
	}

	public abstract ItemStack getItemStack();

	public abstract boolean fire(Player player);

}