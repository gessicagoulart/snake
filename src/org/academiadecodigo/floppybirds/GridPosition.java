package org.academiadecodigo.floppybirds;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class GridPosition {

    private Rectangle rectangle;
    private Grid grid;
    private int col;
    private int row;

    public GridPosition(Grid grid) {

        col = (int) (Math.random() * grid.getCols());
        row = (int) (Math.random() * grid.getRows());

        int x = grid.colToX(col);
        int y = grid.rowToY(row);

        rectangle = new Rectangle(x, y, grid.getCellSize(), grid.getCellSize());
        setColor(Color.GREEN);
        show();

    }

    public GridPosition(int col, int row, Grid grid) {

        int x = grid.colToX(col);
        int y = grid.rowToY(row);

        rectangle = new Rectangle(x, y, grid.getCellSize(), grid.getCellSize());
    }

    public void moveInDirection(GridDirection direction) {

        int initialCol = col;
        int initialRow = row;

        switch (direction) {

            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
        }


    }

    private void moveUp() {
        int maxRowsUp = Math.min(grid.getCellSize(), row);
        setPos(col, row - maxRowsUp);
    }

    private void moveDown() {
        int maxRowsDown = Math.min(grid.getCellSize(), (grid.getRows() - (row + 1)));
        setPos(col, row + maxRowsDown);
    }

    private void moveLeft() {}

    private void moveRight() {}

    public void setColor(org.academiadecodigo.simplegraphics.graphics.Color color) {
        rectangle.setColor(color);
    }

    public void show() {
        rectangle.fill();
    }

    public void setPos(int col, int row) {
        this.col = col;
        this.row = row;
        show();
    }
}
