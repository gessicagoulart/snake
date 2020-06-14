package org.academiadecodigo.floppybirds;

import java.util.LinkedList;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class CollisionDetector {

    private LinkedList<Rectangle> snakeBody;
    private Snake snake;

    public CollisionDetector(Snake snake) {

        snakeBody = snake.getBody();
        this.snake = snake;
    }

    public boolean check() {

        Rectangle head = snakeBody.getFirst();
        for (Rectangle rectangle : snakeBody){
            if(head != rectangle && snakeBody.getFirst().getX() == rectangle.getX() && head.getY() == rectangle.getY()){
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