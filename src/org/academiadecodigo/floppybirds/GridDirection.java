package org.academiadecodigo.floppybirds;

public enum GridDirection {

    UP,
    DOWN,
    LEFT,
    RIGHT;

    public static GridDirection getRandom() {
        return values() [(int) (Math.random() * values().length)];
    }
}
