package org.academiadecodigo.floppybirds;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Game {

    private int delay;
    private int cols;
    private int rows;
    private Grid grid;
    private Snake snake;
    private Apple apple;

    public Game(int cols, int rows, int delay) {
        this.cols = cols;
        this.rows = rows;
        this.delay = delay;
        grid = new Grid(cols, rows);
    }

    public void init() {
        grid.init();
        snake = new Snake(grid);
        apple = new Apple(grid);
    }

    public void start() throws InterruptedException {
        while (true) {

        Thread.sleep(delay);
        snake.move(snake.getCurrentDirection());

        }

    }
}