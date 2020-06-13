package org.academiadecodigo.floppybirds;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;
import java.util.LinkedList;

public class CollisionDetector {
    private LinkedList <Rectangle> snakeBody;


    public CollisionDetector(GridPosition snake){
        this.snakeBody=snake.getSnakeBody();
        //check();

    }
   /*public CollisionDetector(GridPosition snake, Apple apple){
      this.snakeBody=snake.getSnakeBody();
      check(apple);

   }*/

    public boolean check() {
        Rectangle snakeHead = snakeBody.getFirst();
      /*  LinkedList <Rectangle> snake = snakeBody;
        snake.removeFirst();*/
   /*for (Rectangle element : snake) {
      return snakeHead != element && snakeHead.equals(element);
      }*/
        for(Rectangle element : snakeBody){
            if(element!=snakeHead && element.getY()==snakeHead.getY()&&element.getX()==snakeHead.getX()){
                return true;
            }
        }
        return false;
    }

  /* public boolean check(Apple apple) {
      return snakeBody.getFirst().equals(apple.getX, apple.getY);
   }
*/
}