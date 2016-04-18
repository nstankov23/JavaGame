package state;

import entities.bullets.Bullet;
import entities.bullets.BulletType;
import entities.creatures.Enemy;
import entities.creatures.Player;
import game.GameEngine;
import gfx.SpriteSheet;

import java.awt.*;
import java.util.ArrayList;

public class GameState extends State {

    private Player player;
    private ArrayList<Enemy> enemies;

    //start testing bullets
    private SpriteSheet sh;
    private Bullet bullet;

    //end testing
    public GameState(GameEngine gameEngine){
        super(gameEngine);
        this.player = new Player(100, 300,64,29);
        this.enemies = new ArrayList<Enemy>();
        this.enemies.add(new Enemy(800,100,40,30));
        this.enemies.add(new Enemy(800,150,40,30));
        this.enemies.add(new Enemy(800,200,40,30));
        this.enemies.add(new Enemy(800,250,40,30));
        this.enemies.add(new Enemy(800,300,40,30));
        this.enemies.add(new Enemy(800,350,40,30));
        this.enemies.add(new Enemy(800,400,40,30));
        this.enemies.add(new Enemy(800,450,40,30));

        // If we have enemy shooting uncomment
        this.bullet = new Bullet(BulletType.Player, 600, 800);
    }

    @Override
    public void tick() {
        this.player.tick();

        this.bullet.tick();

        for (Enemy enemy : enemies) {
            enemy.tick();
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
        this.player.render(graphics);

        this.bullet.render(graphics);

        for (Enemy enemy : enemies) {
            enemy.render(graphics);
        }

        graphics.setFont(new Font("Arial", Font.BOLD, 18));
        graphics.setColor(Color.black);
        graphics.drawString("Your health is: " + this.player.getHealth(),10,20);
        graphics.setColor(Color.gray);



    }
}
