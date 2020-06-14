package org.academiadecodigo.floppybirds;


import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Grid {

        public static final int PADDING = 10;
        private int cols;
        private int rows;
        private int cellSize = 20;
        private Rectangle background;


        public Grid(int cols, int rows) {
                this.cols = cols;
                this.rows = rows;
        }


        public void init() {

                background = new Rectangle(PADDING, PADDING, cols * cellSize, rows * cellSize);
                background.setColor(Color.DARK_GRAY);
                background.fill();
        }
        public void init(Color color) {

                background = new Rectangle(PADDING, PADDING, cols * cellSize, rows * cellSize);
                background.setColor(color);
                background.fill();
        }



        public int getCols() {
                return cols;
        }

        public int getRows() {
                return rows;
        }

        public int getCellSize() {
                return cellSize;
        }

        public int colToX(int col) {
                return PADDING + cellSize * col;
        }

        public int rowToY(int row) {
                return PADDING + cellSize * row;
        }
}

