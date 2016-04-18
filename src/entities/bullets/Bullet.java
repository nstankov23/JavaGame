package entities.bullets;

import entities.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends Entity {
    private BufferedImage image;
    private int velocityModifier;

    public Bullet(BufferedImage img, int x, int y) {
        super(x, y, 9, 23);
        this.image = img;
        this.velocityModifier = 3;
    }

    @Override
    public void tick() {
        this.y += this.getVelocity() * this.velocityModifier;
        this.getBoundingBox().setBounds(this.x, this.y, this.width, this.height);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(this.image,this.x, this.y,9,23, null);
    }
}
