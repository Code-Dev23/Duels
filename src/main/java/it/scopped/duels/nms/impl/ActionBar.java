package it.scopped.duels.nms.impl;

import it.scopped.duels.Duels;
import it.scopped.duels.nms.Versioning;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ActionBar {
    public static void sendActionBar(Player player, String msg) {
        if (msg == null)
            msg = "";

        if (Bukkit.getVersion().equalsIgnoreCase("v1_7_R4."))
            return;

        ActionBarNMS m = new ActionBarNMS(Duels.getInstance());
        m.sendActionBar(player, msg);
    }

    public static void sendActionBar(Player player, String msg, int duration) {
        if (msg == null)
            msg = "";

        if (Versioning.getServerVersion().equalsIgnoreCase("v1_7_R4."))
            return;

        ActionBarNMS m = new ActionBarNMS(Duels.getInstance());
        m.sendActionBar(player, msg, duration);
    }
}