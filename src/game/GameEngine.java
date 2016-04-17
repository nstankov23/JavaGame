package game;

public class GameEngine implements Runnable {
    private GameWindow display;

    public GameEngine(String title, int width, int height){
        this.display = new GameWindow(title , width, height);


    }

    @Override
    public void run() {

    }
}
