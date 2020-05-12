package dev.java.game.gfx.animations.attackAnimations.RangedAttackAnimations;

import dev.java.game.gfx.animations.Animation;
import dev.java.game.utils.Utils;

import java.awt.image.BufferedImage;
import java.util.function.BooleanSupplier;

public abstract class RangedAttackAnimation extends Animation {

    private float xMove, yMove;
    protected float x, y, oX, oY;


    public RangedAttackAnimation(int speed, BufferedImage[] frames, int animationMovementSpeed,
                                 int oX, int oY, int dX, int dY) {
        super(speed, frames, false);
        this.oX = oX;
        this.oY = oY;
        x = oX;
        y = oY;
        xMove = dX/ Utils.Py.getC(dX, dY) * animationMovementSpeed;
        yMove = dY/ Utils.Py.getC(dX, dY) * animationMovementSpeed;
    }

    @Override
    public void update() {
        super.update();
        x += xMove;
        y += yMove;
    }

    public int getAnimationX(){
        return (int) x;
    }

    public int getAnimationY(){
        return (int)y;
    }

}
