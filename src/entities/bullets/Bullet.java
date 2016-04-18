package entities.bullets;

import entities.Entity;
import gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends Entity{
    private BufferedImage image;
    private int velosityModifier;

    public Bullet(BulletType type, int x, int y) {
        super(x + 15, y + 12, 10, 15);
        init(type);
    }

    private void init(BulletType type) {
        if (type == BulletType.Enemy) {
            this.image = Assets.enemyBullet;
            this.velosityModifier = 1;
        } else {
            this.image = Assets.playerBullet;
            this.velosityModifier = -1;
        }
    }

    @Override
    public void tick() {
        this.x -= this.getVelocity() * this.velosityModifier;
        this.getBoundingBox().setBounds(this.x, this.y, this.width, this.height);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(this.image, this.x, this.y, this.width, this.height, null);
    }
}
