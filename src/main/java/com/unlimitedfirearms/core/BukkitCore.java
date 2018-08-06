package com.unlimitedfirearms.core;

import org.bukkit.plugin.java.JavaPlugin;

import com.unlimitedfirearms.config.CoreConfig;
import com.unlimitedfirearms.core.CommonCore.ServerType;

public class BukkitCore extends JavaPlugin{
	public static BukkitCore instance;
	
	@Override
	public void onEnable() {
		instance = this;
		new CoreConfig(ServerType.BUKKIT);
	}
}
