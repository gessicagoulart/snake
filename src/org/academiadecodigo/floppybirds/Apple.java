package org.academiadecodigo.floppybirds;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Apple {

    private Ellipse ellipse;
    private Picture apple;
    private int col;
    private int row;
    private String appleType;

    public Apple(Grid grid) {


/*        col = (int) (Math.random() * grid.getCols()) * grid.getCellSize() + grid.PADDING;
        row = (int) (Math.random() * grid.getRows()) * grid.getCellSize() + grid.PADDING;
        apple = new Picture(col, row, random());
        apple.draw();*/


        col = (int) (Math.random() * grid.getCols()) * grid.getCellSize() + grid.PADDING;
        row = (int) (Math.random() * grid.getRows()) * grid.getCellSize() + grid.PADDING;
        ellipse = new Ellipse(col, row, grid.getCellSize(), grid.getCellSize());
        ellipse.setColor(Color.RED);
        ellipse.fill();

        /*
        col = (int) (Math.random() * grid.getCols()) * grid.getCellSize() + grid.PADDING;
        row = (int) (Math.random() * grid.getRows()) * grid.getCellSize() + grid.PADDING;
        apple = new Picture(col, row, "resources/apple5.png");
        apple.draw();
*/

    }

    private String random() {
        if ((int) (Math.random() * 100) < 70) {
            appleType = "Red";
            return "resources/apple3.png";
        }
        appleType = "Golden";
        return "resources/apple5.png";
    }

    public void delete() {
        ellipse.delete();
    }

    public Ellipse getEllipse() {
        return ellipse;
    }

    public Picture getApple() {
        return apple;
    }

    public String getAppleType() {
        return appleType;
    }

}
