package org.academiadecodigo.floppybirds;


public class Main {
    public static void main(String[] args) throws InterruptedException {

        Game g = new Game(40, 30, 200);
        g.init();
        g.start();
    }
}
