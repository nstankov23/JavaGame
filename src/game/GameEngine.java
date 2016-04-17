package game;

import contracts.Updatable;
import gfx.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameEngine implements Runnable, Updatable {
    private GameWindow display;
    private Thread thread;

    private boolean isRunning;
    //display fields
    private String title;
    private int width,height;
    //drawing fields
    private Graphics graphics;
    private BufferStrategy bs;

    private SpriteSheet spriteSheet;
    //temporary fields


    public GameEngine(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;


    }

    private void init(){
        this.display = new GameWindow(this.title , this.width, this.height);
        this.display.getCanvas().createBufferStrategy(2);
        this.bs = this.display.getCanvas().getBufferStrategy();
        this.graphics = this.bs.getDrawGraphics();
        this.spriteSheet = new SpriteSheet("/images/Ship/Spritesheet_64x29.png");

    }



    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics = this.bs.getDrawGraphics();
        graphics.clearRect(0,0,this.width,this.height);
        //start drawing

        graphics.drawImage(this.spriteSheet.crop(0,0,64,29),0,0,null);
        //graphics.drawImage(ImageLoader.loadImage("/images/Backgrounds/farback.gif"),0,0,null);
        //end drawing
        this.bs.show();
        graphics.dispose();
    }


    @Override
    public void run() {
        init();

        while (isRunning){
            tick();
            render(this.graphics);
        }
        stop();
    }

    public synchronized void start(){
        if (this.thread != null) {
            return;
        }
        this.isRunning = true;
        this.thread = new Thread(this);
            this.thread.start();

    }

    public synchronized void stop(){
        if (this.thread == null) {
            return;
        }
        this.isRunning = false;

        try {
                this.thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }

}
