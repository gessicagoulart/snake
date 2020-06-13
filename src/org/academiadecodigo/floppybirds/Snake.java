package org.academiadecodigo.floppybirds;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;


public class Snake implements KeyboardHandler {

    private Grid grid;
    private GridDirection currentDirection;
    private GridPosition position;
    private Keyboard keyboard;
    private CollisionDetector collisionDetector;
    private int length = 4;


    public Snake(Grid grid) {
        this.grid = grid;
        position = grid.makeGridPosition();
        this.collisionDetector = new CollisionDetector(position);
        currentDirection = GridDirection.getRandom();
        keyboard = new Keyboard(this);
        init();
    }

    public void init() {

            KeyboardEvent up = new KeyboardEvent();
            up.setKey(KeyboardEvent.KEY_UP);
            up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

            KeyboardEvent down = new KeyboardEvent();
            down.setKey(KeyboardEvent.KEY_DOWN);
            down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

            KeyboardEvent left = new KeyboardEvent();
            left.setKey(KeyboardEvent.KEY_LEFT);
            left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

            KeyboardEvent right = new KeyboardEvent();
            right.setKey(KeyboardEvent.KEY_RIGHT);
            right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

            keyboard.addEventListener(left);
            keyboard.addEventListener(right);
            keyboard.addEventListener(up);
            keyboard.addEventListener(down);

        }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                if (currentDirection != GridDirection.RIGHT) currentDirection = GridDirection.LEFT;
                break;
            case KeyboardEvent.KEY_RIGHT:
                if (currentDirection != GridDirection.LEFT) currentDirection = GridDirection.RIGHT;
                break;
            case KeyboardEvent.KEY_UP:
                if (currentDirection != GridDirection.DOWN) currentDirection = GridDirection.UP;
                break;
            case KeyboardEvent.KEY_DOWN:
                if (currentDirection != GridDirection.UP) currentDirection = GridDirection.DOWN;
                break;
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public void move(GridDirection direction) {
        position.moveInDirection(direction);
    }

    public GridDirection getCurrentDirection() {
        return currentDirection;
    }

    public int getLength() {
        return length;
    }

    public boolean detect() throws InterruptedException {
        if(collisionDetector.check()){
            int i= 10;
            while(i>0) {
               Thread.sleep(300);
               position.clearAll();
               Thread.sleep(300);
               position.showAll();}
            return true;
        }
        return false;
    }
    public GridPosition getPosition(){
        return position;
    }

}
