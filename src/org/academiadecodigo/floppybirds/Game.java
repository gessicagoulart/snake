package org.academiadecodigo.floppybirds;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Game implements KeyboardHandler {

    private int delay;
    private Grid grid;
    private Snake snake;
    private Apple apple;
    private Integer score = 0;
    private CollisionDetector collisionDetector;
    private Text text;
    private Keyboard keyboard;

    public Game(int cols, int rows, int delay) {
        this.delay = delay;
        grid = new Grid(cols, rows);
        text = new Text(grid.colToX(grid.getCols() - 5) - 5, grid.PADDING + 5, "SCORE: " + score);
        keyboard = new Keyboard(this);
    }

    public void init() {
        grid.init();
        snake = new Snake(grid);
        apple = new Apple(grid);
        collisionDetector = new CollisionDetector(snake);
        text.setColor(Color.WHITE);
        text.draw();

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

    public void start() throws InterruptedException {

        while (!snake.snakeCollision()) {

            Thread.sleep(delay);
            if(collisionDetector.check(apple)) {
                apple.delete();
                score = score + 10;
                apple = new Apple(grid);
                System.out.println(score);

                text.delete();
                text = new Text(grid.colToX(grid.getCols() - 5) - 5, grid.PADDING + 5, "SCORE: " + score);
                text.setColor(Color.WHITE);
                text.draw();

            }
            snake.move(snake.getCurrentDirection());
        }
    }



    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                if (snake.getCurrentDirection() != GridDirection.RIGHT) snake.setCurrentDirection(GridDirection.LEFT);
                break;
            case KeyboardEvent.KEY_RIGHT:
                if (snake.getCurrentDirection() != GridDirection.LEFT) snake.setCurrentDirection(GridDirection.RIGHT);
                break;
            case KeyboardEvent.KEY_UP:
                if (snake.getCurrentDirection() != GridDirection.DOWN) snake.setCurrentDirection(GridDirection.UP);
                break;
            case KeyboardEvent.KEY_DOWN:
                if (snake.getCurrentDirection() != GridDirection.UP) snake.setCurrentDirection(GridDirection.DOWN);
                break;
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }

}