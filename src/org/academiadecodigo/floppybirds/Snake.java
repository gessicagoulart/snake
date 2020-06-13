package org.academiadecodigo.floppybirds;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.util.LinkedList;


public class Snake implements KeyboardHandler {

    private Grid grid;
    private GridDirection currentDirection;
    private LinkedList<Rectangle> body;
    private int col;
    private int row;
    private Keyboard keyboard;
    private boolean ateApple;
    private int score;
    private CollisionDetector collisionDetector;
    public static final int INITIAL_LENGTH = 3;
    public static final Color COLOR = Color.GREEN;

    public Snake(Grid grid) {
        this.grid = grid;
        currentDirection = GridDirection.getRandom();
        body = new LinkedList<>();
        col = (int) (Math.random() * grid.getCols());
        row = (int) (Math.random() * grid.getRows());
        collisionDetector = new CollisionDetector(this);
        keyboard = new Keyboard(this);


        for(int i = 0; i < INITIAL_LENGTH; i++) {
            body.add(new Rectangle(grid.colToX(col+i), grid.rowToY(row), grid.getCellSize(), grid.getCellSize()));
        }

        show();
        init();
    }

    public void move(GridDirection direction) {
        switch (direction) {
            case UP:
                if (row <= 0) {
                    row = grid.getRows() - 1;
                } else {
                    row--;
                }
                break;
            case DOWN:
                if (row >= grid.getRows() - 1) {
                    row = 0;
                } else {
                    row ++;
                }
                break;
            case LEFT:
                if (col <= 0) {
                    col = grid.getCols() - 1;
                } else {
                    col--;
                }
                break;
            case RIGHT:
                if (col >= grid.getCols() - 1) {
                    col = 0;
                } else {
                    col++;
                }
                break;
        }

        clear();
        if (!ateApple) {
            body.removeLast();
        } else {
            score++;
            ateApple = false;
        }
        body.push(new Rectangle(grid.colToX(col), grid.rowToY(row), grid.getCellSize(), grid.getCellSize()));
        show();
    }

    public void clear() {
        for(Rectangle rectangle : body) {
            rectangle.delete();
        }
    }

    public void show() {
        for(Rectangle rectangle : body) {
            rectangle.setColor(COLOR);
            rectangle.fill();
        }
    }

    public boolean snakeCollision() throws InterruptedException {

        while(collisionDetector.check()){
            Thread.sleep(300);
            clear();
            Thread.sleep(300);
            show();
        }
        return collisionDetector.check();
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

    public GridDirection getCurrentDirection() {
        return currentDirection;
    }


    public void setAteApple() {
        ateApple = true;
        score++;
    }

    public LinkedList<Rectangle> getBody(){
        return body;
    }




}
