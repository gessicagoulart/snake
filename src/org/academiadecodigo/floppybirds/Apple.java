package org.academiadecodigo.floppybirds;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Apple {

    private Ellipse ellipse;
    private Picture apple;
    private int col;
    private int row;

    public Apple(Grid grid) {
        col = (int) (Math.random() * grid.getCols()) * grid.getCellSize() + grid.PADDING;
        row = (int) (Math.random() * grid.getRows()) * grid.getCellSize() + grid.PADDING;
        ellipse = new Ellipse(col, row, grid.getCellSize(), grid.getCellSize());
        ellipse.fill();
        ellipse.setColor(Color.RED);

        col = (int) (Math.random() * grid.getCols()) * grid.getCellSize() + grid.PADDING;
        row = (int) (Math.random() * grid.getRows()) * grid.getCellSize() + grid.PADDING;
        apple = new Picture(col, row, "resources/apple3.png");
        apple.draw();

        col = (int) (Math.random() * grid.getCols()) * grid.getCellSize() + grid.PADDING;
        row = (int) (Math.random() * grid.getRows()) * grid.getCellSize() + grid.PADDING;
        apple = new Picture(col, row, "resources/apple5.png");
        apple.draw();


    }

    public void delete() {
        ellipse.delete();
    }


}
