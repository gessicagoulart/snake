package org.academiadecodigo.floppybirds;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Apple {

    private Picture apple;
    private int col;
    private int row;
    private String type;

    public Apple(Grid grid) {

        col = (int) (Math.random() * grid.getCols()) * grid.getCellSize() + grid.PADDING;
        row = (int) (Math.random() * grid.getRows()) * grid.getCellSize() + grid.PADDING;
        apple = new Picture(col, row, random());
        apple.draw();
    }

    private String random() {

        if ((int) (Math.random() * 100) < 70) {
            type = "Red";
            return "resources/apple3.png";
        }
        type = "Golden";
        return "resources/apple5.png";
    }

    public void delete() {
        apple.delete();
    }


    public Picture getApple() {
        return apple;
    }

    public String getType() {
        return type;
    }

}
