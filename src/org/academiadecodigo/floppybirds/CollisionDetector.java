package org.academiadecodigo.floppybirds;

import java.util.LinkedList;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class CollisionDetector {

    private LinkedList<Rectangle> snakeBody;
    private Snake snake;
    private Grid grid;

    public CollisionDetector(Snake snake, Grid grid) {

        snakeBody = snake.getBody();
        this.grid = grid;
        this.snake = snake;
    }

    public boolean check() {

        Rectangle head = snakeBody.getFirst();
        for (Rectangle rectangle : snakeBody){
            if(head != rectangle && head.getX() == rectangle.getX() && head.getY() == rectangle.getY()) {
                return true;
            }
            if(head.getY() <= 0 || head.getX() <= 0 || head.getY() >= (grid.rowToY(grid.getRows())) || head.getX() >= (grid.colToX(grid.getCols()))) {
                return true;
            }
        }
        return false;
    }

    public boolean check(Apple apple) {

        Rectangle head = snakeBody.getFirst();
        if (head.getX() == apple.getApple().getX() && head.getY() == apple.getApple().getY()) {
            snake.setAteApple();
            return true;
        }
        return false;
    }

}