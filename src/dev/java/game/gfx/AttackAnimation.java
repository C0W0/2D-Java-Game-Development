package dev.java.game.gfx;

import java.awt.image.BufferedImage;

public class AttackAnimation extends Animation{

    private float distance;
    private String direction;
    private boolean isHit;

    public AttackAnimation(int speed, BufferedImage[] frames, boolean initAction, float distance, String direction) {
        super(speed, frames, initAction);
        this.distance = distance;
        this.direction = direction;
        isHit = false;
    }

    public int getDeltaX(int j){
        if(direction.equals("up") || direction.equals("down")){
            return 0;
        } else if(direction.equals("left")){
            return (int)(-j*distance);
        } else if(direction.equals("right")){
            return (int)(j*distance);
        } else{
            return 0;
        }
    }

    public int getDeltaY(int j){
        if(direction.equals("left") || direction.equals("right")){
            return 0;
        } else if(direction.equals("up")){
            return (int)(-j*distance);
        } else if(direction.equals("down")){
            return (int)(j*distance);
        } else{
            return 0;
        }
    }

    public void targetHit(){
        isHit = true;
    }

    @Override
    public void update() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if(index == 0){
            index ++;
        } else if(index == 1 && isHit){
            index ++;
        }
    }
}
