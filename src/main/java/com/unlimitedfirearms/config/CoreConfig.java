package com.unlimitedfirearms.config;

import com.unlimitedfirearms.core.BukkitCore;
import com.unlimitedfirearms.core.CommonCore.ServerType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class CoreConfig {
	private static ServerType server;
	private static YamlConfiguration lang;

	/**
	 * Starts to load Configuration for UnlimitedFirearms
	 *
	 * @param s The type of this server
	 * @see ServerType
	 */
	public CoreConfig(ServerType s) {
		server = s;
		if (server == ServerType.BUKKIT) {
			loadBukkitConfig();
		} else if (server == ServerType.SPONGE) {
			loadSpongeConfig();
		} else if (server == ServerType.NUKKIT) {
			loadNukkitConfig();
		} else {
			loadCustomConfig();
		}
	}

	private static void loadBukkitConfig() {
		File folder = BukkitCore.instance.getDataFolder();
		File f = new File(folder, "config.yml");
		if (!f.exists()) {
			BukkitCore.instance.saveDefaultConfig();
			BukkitCore.instance.reloadConfig();
		}

		f = new File(folder, "lang.yml");
		lang = loadCustomConf(f);
	}

	private static YamlConfiguration loadCustomConf(File f) {
		if (!f.exists()) {
			BukkitCore.instance.saveResource("lang.yml", true);
		}
		try {
			return YamlConfiguration.loadConfiguration(
					new InputStreamReader(new FileInputStream(f), "utf-8"));
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static void loadSpongeConfig() {
		//TODO Sponge
	}

	private static void loadNukkitConfig() {
		//TODO Nukkit
	}

	private static void loadCustomConfig() {
		//TODO I don't think you should be here
	}

	public static FileConfiguration getCoreConfig() {
		return BukkitCore.instance.getConfig();
	}

	public static YamlConfiguration getLang() {
		return lang;
	}

	public static ServerType getServerType() {
		return server;
	}
}
