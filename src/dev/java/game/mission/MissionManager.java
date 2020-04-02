package dev.java.game.mission;

import dev.java.game.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class MissionManager {

    private Handler handler;
    private ArrayList<Mission> missions;
    private boolean active = false;

    public MissionManager(Handler handler){
        this.handler = handler;
        missions = new ArrayList<Mission>();
    }

    public void addMission(Mission mission){
        mission.setHandler(handler);
        mission.setStatus(1);
        missions.add(mission);
    }

    public void update(){

        Iterator<Mission> it = missions.iterator();
        while(it.hasNext()){
            Mission mission = it.next();
            mission.update();
            if(mission.getStatus() == 2){
                it.remove();
            }
        }
    }

    public void render(Graphics graphics){
        if(!active){
            return;
        }
        for(int i = 0; i < missions.size(); i++){
            Mission mission = missions.get(i);
            mission.render(graphics);
        }
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
