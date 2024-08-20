package it.scopped.duels.utility.debug;

import org.bukkit.Bukkit;

public class Logs {
    public static void info(String message) {
        Bukkit.getLogger().info(message);
    }

    public static void warn(String message) {
        Bukkit.getLogger().warning(message);
    }

    public static void error(String message) {
        Bukkit.getLogger().severe(message);
    }

    public static void error(String message, Exception e) {
        throw new RuntimeException(message, e);
    }
}