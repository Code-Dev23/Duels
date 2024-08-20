package it.scopped.duels.kit.struct;

import it.scopped.duels.Duels;
import it.scopped.duels.kit.Kit;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class KitsManager {
    private final Duels instance;
    @Getter
    private final List<Kit> kits = new ArrayList<>();

    public KitsManager(Duels instance) {
        this.instance = instance;
        loadKits();
    }

    private void loadKits() {
        FileConfiguration config = instance.getKitsFile().getFileConfiguration();
        ConfigurationSection section = config.getConfigurationSection("kits");
        if (config.contains("kits") && section != null) {

        }
    }
}