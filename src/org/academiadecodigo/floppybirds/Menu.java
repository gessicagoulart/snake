package org.academiadecodigo.floppybirds;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class Menu implements KeyboardHandler {

    private Grid grid;
    private Keyboard keyboard;
    private Grid frontGrid;
    private Picture rules;
    private Picture snake;
    private Picture title;
    private Picture button;
    private Text pressText;
    private boolean testPlay;
    private Game g;
    private int cols;
    private int rows;
    //private Text text;


    public Menu(int cols, int rows) {
        grid = new Grid(cols, rows);
        int height = grid.rowToY(rows);
        int width = grid.colToX(cols);

        keyboard = new Keyboard(this);

        frontGrid= new Grid(20, 15);
        rules = new Picture(width-width/2 -20, (height/2-374/2-30 ),"resources/rules.png");
        this.snake = new Picture(20, height/2-358/3, "resources/snake.png");
        title = new Picture(20, 30, "resources/title.png");
        button = new Picture(10*20, 20*20, "resources/Play.png");

        pressText = new Text( width/2-122/2+ 10,height-40 , "Press SPACE to Play");
       // text = new Text( width/2,height-60 , ""+ rules.getHeight()); //descobrir tamanho texto
        pressText.setColor(new Color (233, 196,106));


        this.cols=cols;
        this.rows=rows;

    }


    public void menu() throws InterruptedException {

        grid.init(new Color(38,70,83));
        //frontGrid.init(new Color(42,157,143));
        button.draw();
        snake.draw();
        rules.draw();
        title.draw();
        pressText.draw();
        //text.draw();



        KeyboardEvent play = new KeyboardEvent();
        play.setKey(KeyboardEvent.KEY_SPACE);
        play.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(play);


    }

    //Waits to KeyPlay to init and start Game
    public void play() throws InterruptedException {

        g = new Game(cols, rows);
        while(!testPlay){
            System.out.println("waiting");
        }

        g.init();
        g.start();

    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        testPlay = true;
    }



}
