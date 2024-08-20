package it.scopped.duels.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import it.scopped.duels.Duels;
import it.scopped.duels.arenas.Arena;
import it.scopped.duels.utility.strings.CC;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@CommandAlias("arenas")
@CommandPermission("duels.command.arenas")
@RequiredArgsConstructor
public class ArenasCommand extends BaseCommand {
    private final Duels instance;

    @Default
    public void onCommand(Player player, String[] args) {

    }

    @Subcommand("create")
    public void onSubCreate(Player player, String[] args) {
        if (args.length == 0) {
            CC.send(player, "&cDevi inserire un nome per l'arena.");
            return;
        }

        if (instance.getArenaManager().existArena(args[0])) {
            CC.send(player, "&cEsiste gia' un'arena con questo nome.");
            return;
        }

        instance.getArenaManager().createArena(args[0]);
        CC.send(player, "&aArena creata con successo.");
    }

    @Subcommand("setspawns")
    @CommandCompletion("@locations")
    public void onSubSetSpawns(Player player, String[] args) {
        if (args.length <= 1) {
            CC.send(player, "&cUtilizza: /arenas setspawn <first|second> (arena)");
            return;
        }

        Arena arena = instance.getArenaManager().getArenaById(args[1]);
        if (arena == null) {
            CC.send(player, "&cL'arena non e' stata trovata.");
            return;
        }

        switch (args[0].toLowerCase()) {
            case "first" -> {
                arena.getSpawns().setFirst(player.getLocation());
                instance.getArenaManager().saveArena(arena);
                CC.send(player, "&aPrima posizione impostata con successo.");
            }
            case "second" -> {
                arena.getSpawns().setSecond(player.getLocation());
                instance.getArenaManager().saveArena(arena);
                CC.send(player, "&aSeconda posizione impostata con successo.");
            }
            default -> CC.send(player, "&cUtilizza: /arenas setspawn <first|second>");
        }
    }
}