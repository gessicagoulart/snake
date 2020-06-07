package org.academiadecodigo.floppybirds;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Game {

    Grid grid = new Grid();
    Snake snake;

    public  void init() {
        grid.init();
        snake = new Snake(grid);
    }
}
