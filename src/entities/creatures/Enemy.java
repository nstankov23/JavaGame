package entities.creatures;

import contracts.Intersectable;
import gfx.Assets;

import java.awt.*;

public class Enemy extends Creature implements Intersectable {
    private final  int DEFAULT_DAMAGE = 5;
    private int velocityModifier;

    public Enemy(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.setCreatureDamage(DEFAULT_DAMAGE);
        this.setVelocityModifier(1);
    }

    @Override
    public void tick() {
        this.x -= this.getVelocity();
        this.getBoundingBox().setBounds(this.x, this.y, this.width, this.height);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.enemy.crop(0,0,40,30), this.x, this.y, null);
    }

    @Override
    public boolean intersects(Rectangle rect) {
        return false;
    }

    public int getVelocityModifier() {
        return velocityModifier;
    }

    public void setVelocityModifier(int velocityModifier) {
        this.velocityModifier = velocityModifier;
    }
}
