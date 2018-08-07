package com.unlimitedfirearms.cache;

import com.unlimitedfirearms.api.GunObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GunObjectCache {
	public static HashMap<String, GunObject> objectHashMap = new HashMap<>();

	public static void putObject(String obj, GunObject gun) {
		objectHashMap.put(obj, gun);
	}

	public static boolean hasGunObject(String name) {
		return objectHashMap.keySet().contains(name);
	}

	public static GunObject getByName(String name) {
		return objectHashMap.get(name);
	}

	public static void cleanCache() {
		objectHashMap.clear();
	}

	public static List<GunObject> getObjectList() {
		List<GunObject> objList = new ArrayList<>();
		objectHashMap.values().forEach(x -> objList.add(x));
		return objList;
	}
}
