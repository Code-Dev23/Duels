package it.scopped.duels.duel.struct;

import it.scopped.duels.Duels;
import it.scopped.duels.arenas.Arena;
import it.scopped.duels.duel.DuelData;
import it.scopped.duels.duel.state.GameState;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.*;

@RequiredArgsConstructor
public class DuelManager {
    private final Duels instance;
    @Getter
    private final Map<UUID, UUID> requests = new HashMap<>();
    @Getter
    private final List<DuelData> duels = new ArrayList<>();

    public void createDuel(Player first, Player second, Arena arena) {
        DuelData duel = new DuelData(
                first,
                second,
                arena
        );
        duel.setGameState(GameState.COUNTDOWN);
        duels.add(duel);
    }

    public void endDuel(DuelData duel) {
        duel.setGameState(GameState.RESET);
        duels.remove(duel);
    }

    public boolean isInDuel(Player player) {
        return duels.stream().anyMatch(duelData -> duelData.isIn(player.getUniqueId()));
    }

    public DuelData getDuelByUuid(UUID uuid) {
        return duels.stream().filter(duelData -> duelData != null && duelData.isIn(uuid)).findFirst().orElse(null);
    }
}