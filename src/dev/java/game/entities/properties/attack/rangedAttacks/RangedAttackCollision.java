package dev.java.game.entities.properties.attack.rangedAttacks;

import dev.java.game.Handler;
import dev.java.game.utils.Utils;

import java.awt.*;

public class RangedAttackCollision extends Rectangle {

    private int oX, oY;
    private float xMove, yMove;
    private boolean isHit;

    public RangedAttackCollision(int x, int y, int width, int height, int dX, int dY, int speed){
        super(x, y, width, height);
        oX = x;
        oY = y;
        xMove = dX/ Utils.Py.getC(dX, dY) * speed;
        yMove = dY/ Utils.Py.getC(dX, dY) * speed;
    }

    public float getDistance(){
        return Utils.Py.getC(x-oX, y-oY);
    }

    public void update(){
        x += xMove;
        y += yMove;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
}
