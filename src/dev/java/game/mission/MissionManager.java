package dev.java.game.mission;

import dev.java.game.Handler;
import dev.java.game.gfx.Assets;
import dev.java.game.gfx.Text;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import static java.awt.event.KeyEvent.*;

public class MissionManager {

    private int misHeight, misWidth;
    private int misListCentreX, misListCentreY, misListSpacing;
    private int misTtlX, misTtlY;
    private int selectedMission;

    private Handler handler;
    private ArrayList<Mission> missions;
    private boolean active = false;

    public MissionManager(Handler handler){
        this.handler = handler;
        missions = new ArrayList<Mission>();
        misHeight = handler.getHeight()-50;
        misWidth = handler.getWidth()-100;
        misListCentreX = (int)(324.f/980*misWidth+50);
        misListSpacing = (int)(52.f/670*misHeight);
        misListCentreY = (int)(346.f/670*misHeight - 5*misListSpacing+25);
        misTtlX = (int)(745.f/924*misWidth+50);
        misTtlY = (int)(125.f/718*misHeight+25);
    }

    public void addMission(Mission mission){
        mission.setHandler(handler);
        mission.setStatus(1);
        missions.add(mission);
    }

    public void update(){
        if(handler.getKeyManager().keyJustPressed(VK_Q)){
            active = !active;
        }
        if(active) {
            if(handler.getKeyManager().keyJustPressed(VK_W)){
                selectedMission --;
            }
            if(handler.getKeyManager().keyJustPressed(VK_S)){
                selectedMission ++;
            }
            if(selectedMission < 0){
                selectedMission = missions.size()-1;
            } else if(selectedMission >= missions.size()){
                selectedMission = 0;
            }
        }

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
        graphics.drawImage(Assets.missionScreen,handler.getWidth()/2 - misWidth/2,
                handler.getHeight()/2 - misHeight/2, misWidth, misHeight,null);

        int len = missions.size();
        int roll = 0;
        if(len == 0){
            return;
        }
        for(int i = 0; i < 11; i++){
            if(roll + i >= len){
                continue;
            }
            if(selectedMission >= roll+11){
                roll ++;
            }else if(selectedMission < roll){
                roll --;
            }
            if(roll + i == selectedMission){
                Text.drawString(graphics, ">  "+missions.get(selectedMission).getTitle()+"  <",
                        misListCentreX, misListCentreY+i*misListSpacing, true, Color.blue, Assets.font28);
            }else{
                Text.drawString(graphics, missions.get(roll + i).getTitle(),
                        misListCentreX, misListCentreY+i*misListSpacing, true, Color.black, Assets.font28);
            }
        }
        //desc
        for(int i = 0; i < missions.get(selectedMission).getDesc().length; i++){
            Text.drawString(graphics, missions.get(selectedMission).getDesc()[i],
                    misTtlX, misTtlY+32*i, true, Color.black, Assets.font20);
        }

        //progress
        for(int i = 0; i < missions.get(selectedMission).getFinalProgress().length; i++){
            Text.drawString(graphics, missions.get(selectedMission).getProgress()[i]+" / "+
                    missions.get(selectedMission).getFinalProgress()[i], misTtlX,
                    missions.get(selectedMission).getDesc().length*32+32 + misTtlY, true,
                    Color.red, Assets.font20);
        }

        //complete
        if(missions.get(selectedMission).isCompleted()){
            Text.drawString(graphics, "Complete", misTtlX,
                    missions.get(selectedMission).getDesc().length*32+32 + misTtlY+50, true,
                    Color.RED, Assets.font28);
        }
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public boolean isActive() {
        return active;
    }
}
