package org.academiadecodigo.floppybirds;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import java.util.LinkedList;

public class GridPosition {

    private Grid grid;
    private int col;
    private int row;
    private int snakeLength = 12;
    private LinkedList<Rectangle> snakeBody = new LinkedList<>();
    private Color snakeColor = Color.GREEN;
    private int counter = 10;

    public GridPosition(Grid grid) {
        this.grid = grid;
        col = (int) (Math.random() * grid.getCols());
        row = (int) (Math.random() * grid.getRows());

        for(int i = 0; i < snakeLength; i++) {
            snakeBody.add(new Rectangle(grid.colToX(col+i), grid.rowToY(row), grid.getCellSize(), grid.getCellSize()));
        }

        show();
    }

   /* public GridPosition(int col, int row, Grid grid) {

        int x = grid.colToX(col);
        int y = grid.rowToY(row);
        this.grid = grid;

        rectangle = new Rectangle(x, y, grid.getCellSize(), grid.getCellSize());
    }*/

    public void moveInDirection(GridDirection direction) {

        int initialCol = col;
        int initialRow = row;

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
        if (counter != 1) {
            snakeBody.removeLast();
        }
        snakeBody.push(new Rectangle(grid.colToX(col), grid.rowToY(row), grid.getCellSize(), grid.getCellSize()));
        show();
        counter--;

    }

    private void moveUp() {
        int maxRowsUp = Math.min(1, row);
        row = row - maxRowsUp;
    }

    private void moveDown() {
        int maxRowsDown = Math.min(1, (grid.getRows() - (row + 1)));
        setPos(col, row + maxRowsDown);
    }

    private void moveLeft() {
        int maxRowsLeft = Math.min(1, col);
        setPos(col - maxRowsLeft, row);
    }

    private void moveRight() {
        int maxRowsRight = Math.min(1, (grid.getCols() - (col + 1)));
        setPos(col + maxRowsRight, row);
    }

    public void setColor(org.academiadecodigo.simplegraphics.graphics.Color color) {
            for(Rectangle rectangle : snakeBody) {
                rectangle.setColor(color);
            }
    }

    public void clear() {
        for(Rectangle rectangle : snakeBody) {
            rectangle.delete();
        }
    }

    public void show() {
        for(Rectangle rectangle : snakeBody) {
            rectangle.setColor(snakeColor);
            rectangle.fill();
        }
    }

    public void setPos(int col, int row) {
        this.col = col;
        this.row = row;
        show();
    }

    public LinkedList<Rectangle> getSnakeBody() {
        return snakeBody;
    }
}
