package org.academiadecodigo.floppybirds;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class Game {

    private int delay;
    private Grid grid;
    private Snake snake;
    private Apple apple;
    private Integer score = 0;
    private CollisionDetector collisionDetector;
    private Text text;

    public Game(int cols, int rows, int delay) {
        this.delay = delay;
        grid = new Grid(cols, rows);
        text = new Text(grid.colToX(grid.getCols() - 5) - 5, grid.PADDING + 5, "SCORE: " + score);
    }

    public void init() {
        grid.init();
        snake = new Snake(grid);
        apple = new Apple(grid);
        collisionDetector = new CollisionDetector(snake);
        text.setColor(Color.WHITE);
        text.draw();
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

}