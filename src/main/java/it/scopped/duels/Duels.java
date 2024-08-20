package it.scopped.duels;

import co.aikar.commands.BukkitCommandManager;
import it.scopped.duels.arenas.Arena;
import it.scopped.duels.arenas.struct.ArenaManager;
import it.scopped.duels.commands.ArenasCommand;
import it.scopped.duels.duel.struct.DuelManager;
import it.scopped.duels.kit.struct.KitsManager;
import lombok.Getter;
import net.pino.simpleconfig.BasicQuickConfig;
import net.pino.simpleconfig.impl.QuickConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public final class Duels extends JavaPlugin {

    @Getter
    private static Duels instance;

    private QuickConfig configFile, messagesFile, arenasFile, kitsFile;

    private ArenaManager arenaManager;
    private DuelManager duelManager;
    private KitsManager kitsManager;

    @Override
    public void onEnable() {
        instance = this;
        initConfigurations();

        initialize();

        loadCommandsAndListeners();
    }

    @Override
    public void onDisable() {

    }

    private void initialize() {
        this.arenaManager = new ArenaManager(this);
        this.kitsManager = new KitsManager(this);
        this.duelManager = new DuelManager(this);
    }

    private void loadCommandsAndListeners() {
        BukkitCommandManager commandManager = new BukkitCommandManager(this);

        List.of(
                new ArenasCommand(this)
        ).forEach(commandManager::registerCommand);

        commandManager.getCommandCompletions().registerAsyncCompletion("locations", context -> List.of("first", "second", "end"));
        commandManager.getCommandCompletions().registerAsyncCompletion("arenas", context -> arenaManager.getArenas().stream().map(Arena::getId).collect(Collectors.toList()));
    }

    private void initConfigurations() {
        this.configFile = new BasicQuickConfig();
        this.messagesFile = new BasicQuickConfig();
        this.arenasFile = new BasicQuickConfig();
        this.kitsFile = new BasicQuickConfig();

        this.configFile.registerQuickConfig(this, "config.yml");
        this.messagesFile.registerQuickConfig(this, "messages.yml");
        this.arenasFile.registerQuickConfig(this, "arenas.yml");
        this.kitsFile.registerQuickConfig(this, "kits.yml");
    }
}