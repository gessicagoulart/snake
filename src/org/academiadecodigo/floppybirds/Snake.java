package org.academiadecodigo.floppybirds;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import java.util.LinkedList;


public class Snake {

    private Grid grid;
    private GridDirection currentDirection;
    private LinkedList<Rectangle> body;
    private int col;
    private int row;
    private boolean ateApple;
    public static final int INITIAL_LENGTH = 3;
    public static final Color COLOR = Color.GREEN;


    public Snake(Grid grid) {

        this.grid = grid;
        currentDirection = GridDirection.getRandom();
        body = new LinkedList<>();
        col = (grid.getCols()/4) + (int) (Math.random() * (grid.getCols()*3/4 - grid.getCols()/4));
        row = (grid.getRows()/4) + (int) (Math.random() * (grid.getRows()*3/4 - grid.getRows()/4));

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
                    row--;
                break;
            case DOWN:
                    row ++;
                break;
            case LEFT:
                    col--;
                break;
            case RIGHT:
                    col++;
                break;
        }

        if (!ateApple) {
            body.getLast().delete();
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
