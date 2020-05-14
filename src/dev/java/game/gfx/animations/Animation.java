package dev.java.game.gfx.animations;

import java.awt.image.BufferedImage;

public class Animation {

    // NOTE: the frames it took to cycle through an animation once can be calculated with the following formula:
    // tms/1000*FPS*fra
    // tms: animation delay time between frames in millisecond (the 'speed' variable)
    // FPS: the frame per second constant of the game
    // fra: the number of frames in one cycle of animation (frame.length)
    // the information above can be used to set up a correct FrameTimeController object to control animation overriding

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
        timer = initAction?speed:0;
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
