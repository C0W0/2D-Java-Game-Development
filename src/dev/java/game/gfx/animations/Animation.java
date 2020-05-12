package dev.java.game.gfx.animations;

import java.awt.image.BufferedImage;

public class Animation {

    private int speed;
    protected int index;
    protected long lastTime;
    protected long timer;
    private BufferedImage[] frames;

    public Animation(int speed, BufferedImage[] frames, boolean initAction){
        this.speed = speed;
        this.frames = frames;
        index = 0;
        lastTime = System.currentTimeMillis();
        timer = 0;
        if(initAction){
            timer = speed;
        }
    }

    public BufferedImage getCurrentFrame(){
        return frames[index];
    }

    public void update(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > speed){
            index ++;
            timer = 0;
            if(index >= frames.length){
                index = 0;
            }
        }

    }

}
