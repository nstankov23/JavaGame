package entities.creatures;

import contracts.Intersectable;
import contracts.Shooting;
import entities.bullets.Bullet;
import entities.bullets.BulletType;
import game.GameEngine;
import gfx.Assets;
import input.InputHandler;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Creature implements Intersectable, Shooting {
    private final  int DEFAULT_DAMAGE = 10;
    private ArrayList<Bullet> bullets;
    boolean hasShot;

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.setCreatureDamage(DEFAULT_DAMAGE);
        this.bullets = new ArrayList<>();
    }

    @Override
    public void tick() {
        if (GameEngine.inputHandler.up){
            if (this.y > 0) {
                this.y -= this.getVelocity();
            }else {
                InputHandler.up = false;
            }
        }else if (GameEngine.inputHandler.down){
            if (this.y < 570) {
                this.y += this.getVelocity();
            } else {
                InputHandler.down = false;
            }
        }


        if (GameEngine.inputHandler.left){
            if (this.x > 0) {
                this.x -= this.getVelocity();
            } else {
                InputHandler.left = false;
            }
        }else if (GameEngine.inputHandler.right){
            if (this.x < 735) {
                this.x += this.getVelocity();
            } else {
                InputHandler.right = false;
            }
        }

        this.getBoundingBox().setBounds(this.x, this.y, this.width, this.height);

        if (GameEngine.inputHandler.space && !hasShot) {
            shoot();
            hasShot = true;
        } else if (!GameEngine.inputHandler.space) {
            hasShot = false;
        }

        for (Bullet bullet : bullets) {
            bullet.tick();
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.player.crop(0,0,64,29),this.x,this.y,null);
        graphics.drawRect((int) this.getBoundingBox().getX(), (int) this.getBoundingBox().getY(),
                (int) this.getBoundingBox().getWidth(), (int) this.getBoundingBox().getHeight());

        for (Bullet bullet : bullets) {
            bullet.render(graphics);
        }

    }

    @Override
    public boolean intersects(Rectangle rect) {
        return this.getBoundingBox().contains(rect);
    }

    @Override
    public void shoot() {
        this.bullets.add(new Bullet(BulletType.Player, this.x + this.width/2, this.y));
    }
}
