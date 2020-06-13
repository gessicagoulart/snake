package org.academiadecodigo.floppybirds;

import java.awt.*;
import java.util.LinkedList;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class CollisionDetector {
    private LinkedList<Rectangle> snake;

    public CollisionDetector(GridPosition snake){
        this.snake = snake.getSnakeBody();

    }
    public boolean check(){
        Rectangle head = snake.getFirst();
        for (Rectangle element:snake){
            if(snake.getFirst()!=element&&snake.getFirst().getX()==element.getX()&&snake.getFirst().getY()==element.getY()){
                return true;
            }
        }
        return false;
    }
    public boolean check(Apple apple){
        Rectangle head = snake.getFirst();
       if(head.getY()==apple.getApple().getX()&&head.getY()==apple.getApple().getY()){
           return true;
        }
        return false;
    }
}