package org.academiadecodigo.floppybirds;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Snake implements KeyboardHandler {

    private Grid grid;
    private GridDirection currentDirection;

    public Snake(Grid grid) {
        this.grid = grid;
        grid.makeGridPosition();
        currentDirection = GridDirection.getRandom();
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                currentDirection = GridDirection.LEFT;
                break;
            case KeyboardEvent.KEY_RIGHT:
                currentDirection = GridDirection.RIGHT;
                break;
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
