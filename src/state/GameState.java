package state;

import entities.creatures.Enemy;
import entities.creatures.Player;
import game.GameEngine;
import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameState extends State {

    private Player player;
    private ArrayList<Enemy> enemies;
    private SpriteSheet background;

    public GameState(GameEngine gameEngine){
        super(gameEngine);
        this.player = new Player(100, 300,64,29);
        this.enemies = new ArrayList<Enemy>();
        //this.enemies.add(new Enemy(800,100,40,30));
        //this.enemies.add(new Enemy(800,150,40,30));
        //this.enemies.add(new Enemy(800,200,40,30));
        //this.enemies.add(new Enemy(800,250,40,30));
        //this.enemies.add(new Enemy(800,300,40,30));
        //this.enemies.add(new Enemy(800,350,40,30));
        //this.enemies.add(new Enemy(800,400,40,30));
        //this.enemies.add(new Enemy(800,450,40,30));

    }



    @Override
    public void tick() {
        this.player.tick();

        for (Enemy enemy : enemies) {
            enemy.tick();
            if (enemy.getHealth() == 0 || enemy.getX() <= -100){
                enemies.remove(enemy);

            }
        }
        if (enemies.size() == 0){
            enemies.addAll(addEnemyOnRandPos());
        }

        for (Enemy enemy : enemies) {
            if (this.player.getBoundingBox().intersects(enemy.getBoundingBox())){
                this.player.setHealth(0);
                System.out.println("YOU DEAD");
            }
        }


        if (this.player.getHealth() == 0){
            System.out.println("You died!");
            game.stop();
        }

    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.background,0,0,1782,600,null);
        this.player.render(graphics);

        for (Enemy enemy : enemies) {
            enemy.render(graphics);
        }

        graphics.setFont(new Font("Arial", Font.BOLD, 18));
        graphics.setColor(Color.black);
        graphics.drawString("Your health is: " + this.player.getHealth(),10,20);
        graphics.setColor(Color.gray);

    }


    private ArrayList<Enemy> addEnemyOnRandPos() {
        int enemyNumber = 10;
        int enemyPosX;
        int enemyPosY;
        int nX = 800;
        int mX = 1500;
        int nY = 100;
        int mY = 550;
        Random rnd = new Random();
        ArrayList<Enemy> list = new ArrayList<>();

        for (int i = 0; i < enemyNumber; i++) {
            enemyPosX = rnd.nextInt(mX-nX+1)+nX;
            enemyPosY = rnd.nextInt(mY-nY+1)+nY;
            Enemy currentEnemy = new Enemy(enemyPosX,enemyPosY,40,30);
            list.add(currentEnemy);
        }

        return list;
    }
}
