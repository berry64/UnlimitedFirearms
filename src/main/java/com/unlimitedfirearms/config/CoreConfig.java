package com.unlimitedfirearms.config;

import com.unlimitedfirearms.core.BukkitCore;
import com.unlimitedfirearms.core.CommonCore.ServerType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;

public class CoreConfig {
	private static ServerType server;
	private static YamlConfiguration lang;
	private static File weapons;

	/**
	 * Starts to load Configuration for UnlimitedFirearms
	 *
	 * @param s The type of this server
	 * @see ServerType
	 */
	public CoreConfig(ServerType s) throws FileAlreadyExistsException {
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

	private static void loadBukkitConfig() throws FileAlreadyExistsException {
		File folder = BukkitCore.instance.getDataFolder();
		File f = new File(folder, "config.yml");
		if (!f.exists()) {
			BukkitCore.instance.saveDefaultConfig();
			BukkitCore.instance.reloadConfig();
		}

		lang = loadCustomConf("lang.yml");

		weapons = new File(folder+"/weapons/");
		if((!weapons.exists())&& (!weapons.mkdirs()))
			throw new FileAlreadyExistsException("Cannot create weapons folder");

		//load weapons
		WeaponsConfig.loadWeaps(weapons);
	}

	private static YamlConfiguration loadCustomConf(String filename) {
		File f = new File(BukkitCore.instance.getDataFolder(), filename);
		if (!f.exists()) {
			BukkitCore.instance.saveResource(filename, true);
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

	/**
	 * Returns the configuration file for UnlimitedFirearms Core
	 *
	 * @return the configuration file
	 */
	public static FileConfiguration getCoreConfig() {
		return BukkitCore.instance.getConfig();
	}

	/**
	 * Returns the language file currently in use
	 *
	 * @return language file
	 */
	public static YamlConfiguration getLang() {
		return lang;
	}

	/**
	 * Gets the "./weapons/" folder as File
	 * returns null if is not a folder
	 * @return the folder
	 */
	public static File getWeaponsFolder(){
		return weapons;
	}

	/* now reloaced by GunObject.getConfig();
	public static ConfigurationSection getWeaponConfig(String weaponid){
		if(loadedWeapons.keySet().contains(weaponid))
			return loadedWeapons.get(weaponid);

		return null;
	}(/


	/**
	 * Returns the type of server that the CoreConfig is currently running with
	 * Allowed types are: NUKKIT, SPONGE, BUKKIT
	 *
	 * @return The ServerType
	 * @see ServerType
	 */
	public static ServerType getServerType() {
		return server;
	}
}