package org.algorithms.util;

public class Pair<Key, Value> {
    private final Key key;
    private final Value value;

    public Pair(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public Key getKey() {
        return key;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Pair{" +  "Key=" + key + ", Value=" + value + '}';
    }
}

