package it.scopped.duels.utility.tuple;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class TuplePair<J, K> {
    private J first;
    private K second;

    public J first() {
        return first;
    }

    public K second() {
        return second;
    }
}