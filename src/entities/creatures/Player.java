package entities.creatures;

import contracts.Intersectable;
import contracts.Shooting;
import entities.bullets.Bullet;
import game.GameEngine;
import gfx.Assets;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Creature implements Intersectable, Shooting {
    private final  int DEFAULT_DAMAGE = 10;
        double isMoving;
        boolean hasShot;
        private ArrayList<Bullet> bullets;



    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.setCreatureDamage(DEFAULT_DAMAGE);
        this.isMoving = 0;
    }

    @Override
    public void tick() {
        if (GameEngine.inputHandler.up){
            this.y -= this.getVelocity();
        }else if (GameEngine.inputHandler.down){
            this.y += this.getVelocity();
        }


        if (GameEngine.inputHandler.left){
            this.x -= this.getVelocity();
        }else if (GameEngine.inputHandler.right){
            this.x += this.getVelocity();
        }
        if (GameEngine.inputHandler.space && !hasShot){
            shoot();
        } else if (!GameEngine.inputHandler.space){
            hasShot = false;
        }

        this.getBoundingBox().setBounds(this.x, this.y, this.width, this.height);
        if (isMoving > 3){
            isMoving = 0;
        }
        isMoving += 0.3;

    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.player.crop(0,0+(int)isMoving* 29,64,29),this.x,this.y,null);
        graphics.drawRect((int) this.getBoundingBox().getX(), (int) this.getBoundingBox().getY(),
                (int) this.getBoundingBox().getWidth(), (int) this.getBoundingBox().getHeight());

    }

    @Override
    public boolean intersects(Rectangle rect) {
        return this.getBoundingBox().contains(rect);
    }

    @Override
    public void shoot() {
        this.bullets.add(new Bullet(Assets.playerBullet,this.x, this.y));
    }
}
