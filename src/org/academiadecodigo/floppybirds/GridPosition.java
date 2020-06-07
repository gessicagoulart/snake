package org.academiadecodigo.floppybirds;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class GridPosition {

    private Rectangle rectangle;
    private Grid grid;

    public GridPosition(Grid grid) {

        int col = (int) (Math.random() * grid.getCols());
        int row = (int) (Math.random() * grid.getRows());

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

    public void setColor(org.academiadecodigo.simplegraphics.graphics.Color color) {
        rectangle.setColor(color);
    }

    public void show() {
        rectangle.fill();
    }

}
