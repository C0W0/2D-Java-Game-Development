package dev.java.game.items;

import dev.java.game.Handler;

import java.util.ArrayList;
import java.awt.Graphics;
import java.util.Iterator;

public class ItemManager {

    private Handler handler;
    private ArrayList<Item> items;

    public ItemManager(Handler handler){
        this.handler = handler;
        items = new ArrayList<Item>();
    }

    public void addItem(Item item){
        item.setHandler(handler);
        items.add(item);
    }

    public void update(){

        Iterator<Item> it = items.iterator();
        while(it.hasNext()){
            Item item = it.next();
            item.update();
            if(item.isPickedUP()){
                it.remove();
            }
        }

    }

    public void render(Graphics graphics){
        for(int i = 0; i < items.size(); i++){ //again, need efficiency changes
            Item item = items.get(i);
            item.render(graphics);
        }

    }

    //getters and setters
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
