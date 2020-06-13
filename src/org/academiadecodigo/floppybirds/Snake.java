package org.academiadecodigo.floppybirds;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.util.LinkedList;


public class Snake {

    private Grid grid;
    private GridDirection currentDirection;
    private LinkedList<Rectangle> body;
    private int col;
    private int row;
    private Keyboard keyboard;
    private boolean ateApple;
    private CollisionDetector collisionDetector;
    public static final int INITIAL_LENGTH = 3;
    public static final Color COLOR = Color.GREEN;

    public Snake(Grid grid) {
        this.grid = grid;
        currentDirection = GridDirection.DOWN;//GridDirection.getRandom();
        body = new LinkedList<>();
        col = (int) (Math.random() * grid.getCols());
        row = (int) (Math.random() * grid.getRows());
        collisionDetector = new CollisionDetector(this);

        switch (currentDirection) {
            case UP:
                for (int i = 0; i < INITIAL_LENGTH; i++) {
                    body.add(new Rectangle(grid.colToX(col), grid.rowToY(row + i), grid.getCellSize(), grid.getCellSize()));
                }
                break;
            case DOWN:
                for (int i = 0; i < INITIAL_LENGTH; i++) {
                    body.add(new Rectangle(grid.colToX(col), grid.rowToY(row - i), grid.getCellSize(), grid.getCellSize()));
                }
                break;
            case LEFT:
                for(int i = 0; i < INITIAL_LENGTH; i++) {
                    body.add(new Rectangle(grid.colToX(col + i), grid.rowToY(row), grid.getCellSize(), grid.getCellSize()));
                }
                break;
            case RIGHT:
                for(int i = 0; i < INITIAL_LENGTH; i++) {
                    body.add(new Rectangle(grid.colToX(col - i), grid.rowToY(row), grid.getCellSize(), grid.getCellSize()));
                }
                break;
        }

        show();
    }

    public void move(GridDirection direction) {
        switch (direction) {
            case UP:
                if (row <= 0) {
                    row = grid.getRows() - 1;
                } else {
                    row--;
                }
                break;
            case DOWN:
                if (row >= grid.getRows() - 1) {
                    row = 0;
                } else {
                    row ++;
                }
                break;
            case LEFT:
                if (col <= 0) {
                    col = grid.getCols() - 1;
                } else {
                    col--;
                }
                break;
            case RIGHT:
                if (col >= grid.getCols() - 1) {
                    col = 0;
                } else {
                    col++;
                }
                break;
        }

        clear();
        if (!ateApple) {
            body.removeLast();
        } else {
            ateApple = false;
        }
        body.push(new Rectangle(grid.colToX(col), grid.rowToY(row), grid.getCellSize(), grid.getCellSize()));
        show();
    }

    public void clear() {
        for(Rectangle rectangle : body) {
            rectangle.delete();
        }
    }

    public void show() {
        for(Rectangle rectangle : body) {
            rectangle.setColor(COLOR);
            rectangle.fill();
        }
    }

    public boolean snakeCollision() throws InterruptedException {

        while(collisionDetector.check()){
            Thread.sleep(300);
            clear();
            Thread.sleep(300);
            show();
        }
        return collisionDetector.check();
    }


    public GridDirection getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(GridDirection direction) {
        currentDirection = direction;
    }

    public void setAteApple() {
        ateApple = true;
    }

    public LinkedList<Rectangle> getBody(){
        return body;
    }




}
