package org.academiadecodigo.floppybirds;

import org.academiadecodigo.bootcamp.Sound;
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
    private Integer score;
    private CollisionDetector collisionDetector;
    private Text scoreBoard;
    private Keyboard keyboard;
    private boolean paused;
    private boolean gameOver;
    private boolean stop;
    private Sound eat;
    private Sound gameOverSound;
    private Sound menuSound;

    public Game(int cols, int rows, int delay) throws InterruptedException {
        this.delay = delay;
        paused = true;
        grid = new Grid(cols, rows);
        keyboard = new Keyboard(this);
        menuSound = new Sound("/resources/tetris.wav");
        //menuSound.play(true);
    }

    public void init() throws InterruptedException {

        grid.init(new Color(40, 30, 20));
        score = 0;
        snake = new Snake(grid);
        apple = new Apple(grid);
        collisionDetector = new CollisionDetector(snake, grid);
        scoreBoard = new Text(grid.colToX(grid.getCols() - 5) - 5, grid.PADDING + 5, "SCORE: " + score);
        scoreBoard.setColor(Color.WHITE);
        scoreBoard.draw();

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

        KeyboardEvent space = new KeyboardEvent();
        space.setKey(KeyboardEvent.KEY_SPACE);
        space.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent plus = new KeyboardEvent();
        plus.setKey(KeyboardEvent.KEY_X);
        plus.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent minus = new KeyboardEvent();
        minus.setKey(KeyboardEvent.KEY_Z);
        minus.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent esc = new KeyboardEvent();
        esc.setKey(KeyboardEvent.KEY_ESC);
        esc.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent menu = new KeyboardEvent();
        menu.setKey(KeyboardEvent.KEY_M);
        menu.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(left);
        keyboard.addEventListener(right);
        keyboard.addEventListener(up);
        keyboard.addEventListener(down);
        keyboard.addEventListener(space);
        keyboard.addEventListener(plus);
        keyboard.addEventListener(minus);
        keyboard.addEventListener(esc);
        keyboard.addEventListener(menu);
    }

    public void start() throws InterruptedException {

        while (!stop||!gameOver) {

            if (paused) {
                Thread.sleep(delay);
            } else {
                Thread.sleep(delay);
                snakeCollision();
                appleCollision();
                snake.move(snake.getCurrentDirection());
            }
            if(stop){Menu g = new Menu(40, 30);
            g.menu();
            g.play();}
        }


    }

    public void appleCollision() {

        if (collisionDetector.check(apple)) {
            if (apple.getType() == "Red") {
                score = score + 10;
            }
            if (apple.getType() == "Golden") {
                score = score + 30;
            }
            apple.delete();
            eat = new Sound("/resources/eat.wav");
            eat.play(true);
            apple = new Apple(grid);
            System.out.println(score);
            scoreBoard.delete();
            scoreBoard = new Text(grid.colToX(grid.getCols() - 5) - 5, grid.PADDING + 5, "SCORE: " + score);
            scoreBoard.setColor(Color.WHITE);
            scoreBoard.draw();
        }
    }

    public void snakeCollision() throws InterruptedException {
        int counter = 0;
        while (collisionDetector.check()) {
            gameOver = true;
            Thread.sleep(300);
            snake.clear();
            Thread.sleep(300);
            snake.show();
            counter++;
            if (counter == 1) {
                gameOverSound = new Sound("/resources/gameover.wav");
                menuSound.stop();
                gameOverSound.play(true);
            }
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
            case KeyboardEvent.KEY_ESC:
                System.exit(0);
                break;
            case KeyboardEvent.KEY_M:
                stop=true;
                break;
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_SPACE:
                if (!paused) {
                    paused = true;
                } else {
                    paused = false;
                }
                break;
            case KeyboardEvent.KEY_Z:
                delay = delay + 15;
                break;
            case KeyboardEvent.KEY_X:
                delay = delay - 15;
                break;
        }
    }


}
