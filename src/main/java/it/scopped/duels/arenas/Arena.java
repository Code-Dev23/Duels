package it.scopped.duels.arenas;

import it.scopped.duels.utility.tuple.TuplePair;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

@Getter
@Setter
public class Arena {
    private final String id;
    private TuplePair<Location, Location> spawns;
    private boolean busy, enabled;

    public Arena(String id, Location first, Location second) {
        this.id = id;
        this.spawns = new TuplePair<>(first, second);
        this.busy = false;
        this.enabled = true;
    }

    public Arena(String id) {
        this.id = id;
        this.spawns = new TuplePair<>(null, null);
        this.busy = false;
        this.enabled = false;
    }
}