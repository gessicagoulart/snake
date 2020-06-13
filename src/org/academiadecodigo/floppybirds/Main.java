package org.academiadecodigo.floppybirds;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Game g = new Game(40, 30, 200);
        g.init();
        g.start();
    }
}
