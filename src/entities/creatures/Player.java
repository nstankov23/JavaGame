package entities.creatures;

import contracts.Intersectable;
import game.GameEngine;
import gfx.Assets;

import java.awt.*;

public class Player extends Creature implements Intersectable {
    private final  int DEFAULT_DAMAGE = 10;


    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.setCreatureDamage(DEFAULT_DAMAGE);

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

        this.getBoundingBox().setBounds(this.x, this.y, this.width, this.height);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.player.crop(0,0,64,29),this.x,this.y,null);
        graphics.drawRect((int) this.getBoundingBox().getX(), (int) this.getBoundingBox().getY(),
                (int) this.getBoundingBox().getWidth(), (int) this.getBoundingBox().getHeight());

    }

    @Override
    public boolean intersects(Rectangle rect) {
        return this.getBoundingBox().contains(rect);
    }
}
