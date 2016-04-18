package entities;

import contracts.Updatable;

import java.awt.*;

public abstract class Entity implements Updatable {
    protected int x, y;
    protected  int width, height;
    private int velocity;

    private Rectangle boundingBox;

    public Entity(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velocity = 3;
        this.setBoundingBox(new Rectangle(this.x, this.y, this.width, this.height));
    }
    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(Rectangle boundingBox) {
        this.boundingBox = boundingBox;
    }
}
