package org.academiadecodigo.floppybirds;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Game {

    private int delay;
    private Grid grid;
    private Snake snake;
    private Apple apple;
    private int score;
    private CollisionDetector collisionDetector;

    public Game(int cols, int rows, int delay) {
        this.delay = delay;
        grid = new Grid(cols, rows);
    }

    public void init() {
        grid.init();
        snake = new Snake(grid);
        apple = new Apple(grid);
        collisionDetector = new CollisionDetector(snake);
    }

    public void start() throws InterruptedException {

        while (!snake.snakeCollision()) {

            Thread.sleep(delay);
            if(collisionDetector.check(apple)) {
                apple.delete();
                score++;
                apple = new Apple(grid);

            }
            snake.move(snake.getCurrentDirection());
        }
    }

}