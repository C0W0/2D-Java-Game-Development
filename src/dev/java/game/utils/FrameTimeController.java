package dev.java.game.utils;

public class FrameTimeController {

    private int frames, target;
    private boolean timeControllerOn;

    public FrameTimeController(){
        frames = 0;
        target = 0;
        timeControllerOn = false;
    }

    /**
     * @param target the target frame number since calling the start method
     */
    public void start(int target){
        this.target = target;
        timeControllerOn = true;
    }

    /**
     * if only counting frames is needed
     */
    public void start(){
        timeControllerOn = true;
    }

    public void update(){
        if(!timeControllerOn)
            return;
        frames ++;
        if(atTarget()){
            reset();
        }
    }

    public boolean atTarget(){
        return frames >= target;
    }

    public int getStatus(){
        return frames;
    }

    public void reset(){
        timeControllerOn = false;
        frames = 0;
        target = 0;
    }
}
