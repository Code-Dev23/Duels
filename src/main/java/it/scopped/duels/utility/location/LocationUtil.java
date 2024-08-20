package it.scopped.duels.utility.location;

import it.scopped.duels.Duels;
import it.scopped.duels.utility.debug.Logs;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationUtil {
    @Getter
    private static Location SPAWN;

    public static Location deserializeLocation(String string) {
        String[] split = string.split(":");
        return new Location(Bukkit.getWorld(split[0]),
                Double.parseDouble(split[1]),
                Double.parseDouble(split[2]),
                Double.parseDouble(split[3]),
                Float.parseFloat(split[4]),
                Float.parseFloat(split[5]));
    }

    public static String serializeLocation(Location location) {
        if (location == null)
            return null;
        return location.getWorld().getName() + ":" + location.getX() + ":" + location.getY() + ":" + location.getZ() + ":" + location.getYaw() + ":" + location.getPitch();
    }

    public static void checkSpawn() {
        try {
            SPAWN = LocationUtil.deserializeLocation(Duels.getInstance().getConfigFile().getFileConfiguration().getString("spawn"));
        } catch (Exception ex) {
            Logs.error("Spawn not found.");
        }
    }

    public static void setSpawn(Location location) {
        SPAWN = location;
        Duels.getInstance().getConfigFile().getFileConfiguration().set("end_duel_spawn", serializeLocation(location));
        Duels.getInstance().getConfigFile().saveAndReload();
    }
}