package com.unlimitedfirearms.core;

import com.unlimitedfirearms.config.CoreConfig;
import com.unlimitedfirearms.core.CommonCore.ServerType;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitCore extends JavaPlugin {
	public static BukkitCore instance;

	@Override
	public void onEnable() {
		instance = this;
		new CoreConfig(ServerType.BUKKIT);
	}
}
