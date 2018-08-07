package com.unlimitedfirearms.core;

import com.unlimitedfirearms.config.CoreConfig;
import com.unlimitedfirearms.core.CommonCore.ServerType;
import com.unlimitedfirearms.core.commands.UnlimitedFirearmsCommand;
import com.unlimitedfirearms.listeners.BukkitListenerCore;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.file.FileAlreadyExistsException;

public class BukkitCore extends JavaPlugin {
	public static BukkitCore instance;

	@Override
	public void onEnable() {
		instance = this;
		try {
			new CoreConfig(ServerType.BUKKIT);
		} catch (FileAlreadyExistsException e) {
			e.printStackTrace();
			Bukkit.getServer().getPluginManager().disablePlugin(this);
		}
		this.getServer().getPluginCommand("unlimitedfirearms").setExecutor(new UnlimitedFirearmsCommand());
		this.getServer().getPluginManager().registerEvents(new BukkitListenerCore(), this);
	}

	public void loadDefaultWeapons(){
		
	}
}
