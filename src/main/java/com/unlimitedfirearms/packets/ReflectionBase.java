package com.unlimitedfirearms.packets;

import com.unlimitedfirearms.api.UFAAPI;
import net.minecraft.server.v1_12_R1.Packet;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionBase {

    private static boolean init = false;

    private static Class<?> craftPlayer, entityPlayer, playerConnection;
    private static Method getHandle, sendPacket;
    private static Field playerConn;

    public static int versionInt;

    /**
     * Attempts to retrieve the classes, fields, and methods used to send packets
     * this should <b>not</b> be called frequently as it produces significant lag
     */
    public static void init(){
        //Register Methods so less lag;
        versionInt = Integer.parseInt(UFAAPI.mcversion.split("_")[1]);
        try {
            craftPlayer = Class.forName("org.bukkit.craftbukkit."+UFAAPI.mcversion+".entity.CraftPlayer");
            entityPlayer = Class.forName("net.minecraft.server."+UFAAPI.mcversion+".EntityPlayer");
            playerConnection = Class.forName("net.minecraft.server."+UFAAPI.mcversion+".PlayerConnection");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            getHandle = craftPlayer.getMethod("getHandle");
            sendPacket = playerConnection.getMethod("sendPacket", Packet.class);
            playerConn = entityPlayer.getField("playerConnection");
        } catch (NoSuchMethodException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        versionInt = Integer.parseInt(UFAAPI.mcversion.split("_")[1]);

        //Inits class caches of different subclasses

        init = true;
    }

    /**
     * Sends a packet to a player, this could be called in runtime because the methods are cached
     * however, do not call frequently
     * @param player the player to send packet to
     * @param packet the packet sent
     */
    public static void sendPacket(Player player, Object packet){
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(null);

        try {
            sendPacket.invoke(playerConn.get(getHandle.invoke(craftPlayer.cast(player))), packet);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
