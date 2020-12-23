package dev.java.game.ui;

import dev.java.game.Handler;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class UIManager {

    private Handler handler;
    private ArrayList<UIObject> uiObjects;

    public UIManager(Handler handler){
        this.handler = handler;
        uiObjects = new ArrayList<UIObject>();
    }

    public void update(){
        for(int i = 0; i < uiObjects.size(); i++){
            UIObject o = uiObjects.get(i);
            o.update();
        }
    }

    public void render(Graphics graphics){
        for(int i = 0; i < uiObjects.size(); i++){
            UIObject o = uiObjects.get(i);
            o.render(graphics);
        }
    }

    public void onMouseMove(MouseEvent e){
        for(int i = 0; i < uiObjects.size(); i++){
            UIObject o = uiObjects.get(i);
            o.onMouseMove(e);
        }
    }

    public void onMouseRelease(MouseEvent e){
        for(int i = 0; i < uiObjects.size(); i++){
            UIObject o = uiObjects.get(i);
            o.onMouseRelease(e);
        }
    }

    public void addUIObject(UIObject o){
        uiObjects.add(o);
    }

    public void addUIObject(UIObject[] objects){
        uiObjects.addAll(Arrays.asList(objects));
    }

    public void removeUIObject(UIObject o){
        uiObjects.remove(o);
    }


    //getters and setters
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<UIObject> getUiObjects() {
        return uiObjects;
    }

    public void setUiObjects(ArrayList<UIObject> uiObjects) {
        this.uiObjects = uiObjects;
    }
}
