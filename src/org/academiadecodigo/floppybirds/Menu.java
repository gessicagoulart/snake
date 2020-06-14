package org.academiadecodigo.floppybirds;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class Menu implements KeyboardHandler {




    private int delay;
    private Grid grid;
    private Keyboard keyboard;
    private Grid frontGrid;
    private Picture base;
    private Picture snake;
    private Picture button;
    private Text pressText;
    private boolean testPlay;
    private Game g;
    private int cols;
    private int rows;

    private Text text;


    public Menu(int cols, int rows) {
        //this.delay = delay;
        grid = new Grid(cols, rows);
        int height = grid.rowToY(rows);
        int width = grid.colToX(cols);

        keyboard = new Keyboard(this);

        frontGrid= new Grid(20, 15);
        base = new Picture(width-width/2, 15 ,"resources/base.png");
        this.snake = new Picture(20, height/2-170/2, "resources/snake.png");
        button = new Picture(10*20, 20*20, "resources/Play.png");

        pressText = new Text( width/2-122/2,height-40 , "Press SPACE to Play");
        text = new Text( width/2,height-60 , ""+ snake.getHeight()); //descobrir tamanho texto
        pressText.setColor(new Color (233, 196,106));


        this.cols=cols;
        this.rows=rows;

        //g = new Game(cols, rows, delay);

        //initializes menu created
    }


    public void menu() throws InterruptedException {

        grid.init(new Color(38,70,83));
        //frontGrid.init(new Color(42,157,143));
        button.draw();
        snake.draw();
        base.draw();
        pressText.draw();
        text.draw();


        KeyboardEvent play = new KeyboardEvent();
        play.setKey(KeyboardEvent.KEY_SPACE);
        play.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(play);


    }

    //Waits to KeyPlay to init and start Game
    public void play() throws InterruptedException {

        g = new Game(cols, rows, 200);
        while(!testPlay){
            System.out.println("waiting");
        }

        g.init();
        g.start();

    }



    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        testPlay = true;
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }



}
