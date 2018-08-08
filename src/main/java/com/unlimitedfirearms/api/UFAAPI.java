package com.unlimitedfirearms.api;

import com.unlimitedfirearms.gui.UIInvHolder;
import org.bukkit.Bukkit;

public class UFAAPI {
    public static UIInvHolder ufaholder = new UIInvHolder();
    public static String mcversion = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
}
