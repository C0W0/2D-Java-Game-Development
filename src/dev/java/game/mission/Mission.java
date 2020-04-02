package dev.java.game.mission;

import dev.java.game.Handler;
import dev.java.game.mission.colloector.CollectWood;

import java.awt.*;

public abstract class Mission {

    public static Mission[] missions = new Mission[256];
    public static Mission collectWoods = new CollectWood("", "", 0);

    protected int status;
    /** 0 - not active
     *  1 - in progress
     *  2 - completed
     *  any mission that cannot be completed multiple times
     *  will receive a status of 2 after completion
     */

    protected int stage;

    protected Handler handler;
    protected final int id;
    protected final String title, desc;

    public Mission(String title, String desc, int id){
        status = 0;
        this.id = id;
        this.title = title;
        this.desc = desc;
        missions[id] = this;
    }

    public void update(){
        if(isCompleted()){
            status = 2;
        }
    }

    public void assignMission(){
        handler.getWorld().getPlayer().getMissionManager().addMission(this);
    }

    public abstract void render(Graphics graphics);

    public boolean isCompleted(){
        return status == 2;
    }

    public int getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
