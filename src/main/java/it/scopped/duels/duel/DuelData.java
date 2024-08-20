package it.scopped.duels.duel;

import it.scopped.duels.arenas.Arena;
import it.scopped.duels.duel.state.GameState;
import it.scopped.duels.tasks.DuelStartingTask;
import it.scopped.duels.utility.location.LocationUtil;
import it.scopped.duels.utility.strings.CC;
import it.scopped.duels.utility.tuple.TuplePair;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class DuelData {
    private final TuplePair<Player, Player> players;
    private final List<UUID> spectators;
    private final Arena arena;
    private GameState state;

    public DuelData(Player first, Player second, Arena arena) {
        this.players = new TuplePair<>(first, second);
        this.spectators = new ArrayList<>();
        this.state = GameState.ACTIVE;
        this.arena = arena;
        setGameState(GameState.COUNTDOWN);
    }

    public boolean isIn(UUID uuid) {
        return spectators.contains(uuid)
                || players.first().getUniqueId() == uuid
                || players.second().getUniqueId() == uuid;
    }

    public void setGameState(GameState gameState) {
        Player first = players.first();
        Player second = players.second();

        switch (gameState) {
            case ACTIVE -> {
                this.state = gameState;
            }
            case LIVE -> {
                this.state = gameState;
                arena.setBusy(true);

                first.teleport(arena.getSpawns().first());
                second.teleport(arena.getSpawns().second());

                CC.send(first, "&bIl duello e' iniziato! &fChe vinca il migliore.");
                CC.send(second, "&bIl duello e' iniziato! &fChe vinca il migliore.");

                CC.sendTitle(first, "&b&lCOMBATTI!", "&fIl duello e' iniziato!");
                CC.sendTitle(second, "&b&lCOMBATTI!", "&fIl duello e' iniziato!");
            }
            case RESET -> {
                this.state = gameState;

                first.teleport(LocationUtil.getSPAWN());
                second.teleport(LocationUtil.getSPAWN());

                if (!spectators.isEmpty()) {
                    spectators.stream()
                            .filter(uuid -> Bukkit.getPlayer(uuid) != null)
                            .forEach(spec -> Bukkit.getPlayer(spec).teleport(LocationUtil.getSPAWN()));

                    spectators.clear();
                }

                players.setFirst(null);
                players.setSecond(null);

                // TODO: Make arena reset method
                //arena.resetMap();

                this.arena.setBusy(false);
            }
            case COUNTDOWN -> {
                this.state = gameState;
                new DuelStartingTask(this).start();
            }
        }
    }
}