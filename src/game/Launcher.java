package game;

public class Launcher {
    public static void main(String[] args) {
        GameEngine game = new GameEngine("BattleShip",800,600);
        game.start();

    }

}
