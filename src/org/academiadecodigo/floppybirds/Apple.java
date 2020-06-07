package org.academiadecodigo.floppybirds;

public class Apple {

    private Grid grid;
    private GridPosition position;

    public Apple(Grid grid) {
        this.grid = grid;
        position = grid.makeGridPosition();
    }
}
