package it.scopped.duels.tasks;

import it.scopped.duels.Duels;
import it.scopped.duels.duel.DuelData;
import it.scopped.duels.duel.state.GameState;
import it.scopped.duels.utility.strings.CC;
import lombok.RequiredArgsConstructor;
import org.bukkit.scheduler.BukkitRunnable;

@RequiredArgsConstructor
public class DuelStartingTask extends BukkitRunnable {
    private final DuelData duel;
    private int i = 5;

    public void start() {
        runTaskTimer(Duels.getInstance(), 0, 20L);
    }

    @Override
    public void run() {
        if (i <= 0) {
            duel.setGameState(GameState.LIVE);
            this.cancel();
            return;
        }

        // TODO: Check if one players leave from the server and gave the win.

        CC.send(duel.getPlayers().first(), "Il duello iniziera' tra &b{time}&f!", "{time}", i);
        CC.send(duel.getPlayers().second(), "Il duello iniziera' tra &b{time}&f!", "{time}", i);

        CC.sendActionBar(duel.getPlayers().first(), "&b{time}", "{time}", i);
        CC.sendActionBar(duel.getPlayers().second(), "&b{time}", "{time}", i);
        i--;
    }
}