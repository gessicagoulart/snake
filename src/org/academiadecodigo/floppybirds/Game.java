package org.academiadecodigo.floppybirds;

import org.academiadecodigo.bootcamp.Sound;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Game implements KeyboardHandler {

    private Grid grid;
    private Snake snake;
    private Apple apple;
    private int score;
    private CollisionDetector collisionDetector;
    private Text scoreBoard;
    private Text gameOverText;
    private Text backToMenuText;
    private Text keyText;
    private Keyboard keyboard;
    private boolean paused;
    private boolean gameOver;
    private boolean stop;
    private Sound eat;
    private Sound gameOverSound;
    public static int delay;

    public Game(int cols, int rows) throws InterruptedException {
        //this.delay = Math.max(50, 200 - score / 50 * 8);
        grid = new Grid(cols, rows);
        int height = grid.rowToY(rows);
        int width = grid.colToX(cols);

        keyboard = new Keyboard(this);
        keyText = new Text( width/2-317/2+ 10,height-40 , "SPACE: Pause/Restart   M: Back to menu   ESC: Exit");
        keyText.setColor(new Color (233, 196,106));
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
        //keyText = new Text( width/2-122/2+ 10,height-40 , "SPACE: Pause/Restart   M: Back to manu   ESC: Exit");
        //text = new Text( width/2,height-60 , ""+ rules.getHeight()); //descobrir tamanho text
        keyText.draw();
        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_UP);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent k = new KeyboardEvent();
        k.setKey(KeyboardEvent.KEY_K);
        k.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_DOWN);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent j = new KeyboardEvent();
        j.setKey(KeyboardEvent.KEY_J);
        j.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent h = new KeyboardEvent();
        h.setKey(KeyboardEvent.KEY_H);
        h.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent l = new KeyboardEvent();
        l.setKey(KeyboardEvent.KEY_L);
        l.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

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
        keyboard.addEventListener(h);
        keyboard.addEventListener(right);
        keyboard.addEventListener(l);
        keyboard.addEventListener(up);
        keyboard.addEventListener(k);
        keyboard.addEventListener(down);
        keyboard.addEventListener(j);
        keyboard.addEventListener(space);
        keyboard.addEventListener(plus);
        keyboard.addEventListener(minus);
        keyboard.addEventListener(esc);
        keyboard.addEventListener(menu);
    }

    public void start() throws InterruptedException {

        while (!stop||!gameOver) {

            if (paused) {
                Thread.sleep(200);
            } else {
                Thread.sleep(Math.max(70, 200 - score / 50 * 8));
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
        while (collisionDetector.check() && !stop) {
            gameOver = true;
            Thread.sleep(300);
            snake.clear();
            Thread.sleep(300);
            snake.show();
            counter++;
            if (counter == 1) {
                gameOverSound = new Sound("/resources/gameover.wav");
                //menuSound.stop();
                gameOverSound.play(true);
                gameOverText = new Text (350, 250, "GAME OVER!!!");
                gameOverText.setColor(Color.WHITE);
                gameOverText.grow(130, 170);
                gameOverText.draw();
                backToMenuText = new Text(300, 400, "Press SPACE to go back to menu");
                backToMenuText.setColor(Color.WHITE);
                backToMenuText.draw();
            }
        }
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
            case KeyboardEvent.KEY_H:
                if (snake.getCurrentDirection() != GridDirection.RIGHT) snake.setCurrentDirection(GridDirection.LEFT);
                break;
            case KeyboardEvent.KEY_RIGHT:
            case KeyboardEvent.KEY_L:
                if (snake.getCurrentDirection() != GridDirection.LEFT) snake.setCurrentDirection(GridDirection.RIGHT);
                break;
            case KeyboardEvent.KEY_UP:
            case KeyboardEvent.KEY_K:
                if (snake.getCurrentDirection() != GridDirection.DOWN) snake.setCurrentDirection(GridDirection.UP);
                break;
            case KeyboardEvent.KEY_DOWN:
            case KeyboardEvent.KEY_J:
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
                if (!gameOver) {
                    if (!paused) {
                        paused = true;
                    } else {
                        paused = false;
                    }
                } if (gameOver) {
                snake.clear();
                stop = true;
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
