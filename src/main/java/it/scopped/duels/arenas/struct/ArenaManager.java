package it.scopped.duels.arenas.struct;

import it.scopped.duels.Duels;
import it.scopped.duels.arenas.Arena;
import it.scopped.duels.utility.debug.Logs;
import it.scopped.duels.utility.location.LocationUtil;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {
    private final Duels instance;
    @Getter
    private final List<Arena> arenas = new ArrayList<>();

    public ArenaManager(Duels instance) {
        this.instance = instance;
        loadArenas();
    }

    public void loadArenas() {
        FileConfiguration config = instance.getArenasFile().getFileConfiguration();
        ConfigurationSection section = config.getConfigurationSection("arenas");
        if (config.contains("arenas") && section != null) {
            for (String key : section.getKeys(false)) {
                Location first = LocationUtil.deserializeLocation(section.getString(key + ".spawns.1"));
                Location second = LocationUtil.deserializeLocation(section.getString(key + ".spawns.2"));

                if (first != null && second != null) {
                    Logs.info("Error loading arena with id: " + key);
                    return;
                }

                arenas.add(
                        new Arena(
                                key,
                                first,
                                second
                        )
                );
            }
        }
    }

    public Arena searchAvailableArena() {
        return arenas.stream().filter(arena -> arena.isEnabled() && !arena.isBusy()).findFirst().orElse(null);
    }

    public Arena getArenaById(String id) {
        return arenas.stream().filter(arena -> arena.getId().equals(id)).findFirst().orElse(null);
    }

    public boolean existArena(String id) {
        return getArenaById(id) != null;
    }

    public void createArena(String id) {
        arenas.add(new Arena(id));
    }

    public void saveArena(Arena arena) {
        FileConfiguration config = instance.getArenasFile().getFileConfiguration();

        if (arena.getSpawns().first() != null) {
            config.set("arenas." + arena.getId() + ".spawns.1", LocationUtil.serializeLocation(arena.getSpawns().first()));
            instance.getArenasFile().saveAndReload();
        }
        if (arena.getSpawns().second() != null) {
            config.set("arenas." + arena.getId() + ".spawns.2", LocationUtil.serializeLocation(arena.getSpawns().second()));
            instance.getArenasFile().saveAndReload();
        }
    }
}