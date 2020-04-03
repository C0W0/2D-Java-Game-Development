package dev.java.game.inventory;

import dev.java.game.Handler;
import dev.java.game.gfx.Assets;
import dev.java.game.gfx.ImageLoader;
import dev.java.game.gfx.Text;
import dev.java.game.items.Item;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;

public class Inventory {

    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> inventoryItems;
    private int invHeight, invWidth;
    private int invListCentreX, invListCentreY, invListSpacing;
    private int invImageX, invImageY;
    private int invImageWidth, invImageHeight;
    private int invCountX, invCountY;

    private int selectedItem;

    public Inventory(Handler handler){
        this.handler = handler;
        inventoryItems = new ArrayList<Item>();

        invHeight = handler.getHeight()-50;
        invWidth = handler.getWidth()-100;
        invListCentreX = (int)(324.f/980*invWidth+50);
        invListCentreY = (int)(346.f/670*invHeight+25);
        invListSpacing = (int)(52.f/670*invHeight);
        invImageX = (int)(748.f/980*invWidth+50);
        invImageY = (int)(58.f/670*invHeight+25);
        invImageWidth = (int)(112.f/980*invWidth);
        invImageHeight = (int)(112.f/670*invHeight);
        invCountX = (int)(802.5f/980*invWidth+50);
        invCountY = (int)(220.f/670*invHeight+25);
        selectedItem = 0;
    }

    public void update(){
        if(handler.getKeyManager().keyJustPressed(VK_I)){
            active = !active;
        }
        if(!active){
            return;
        }

        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)){
            selectedItem --;
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)){
            selectedItem ++;
        }

        if(selectedItem < 0){
            selectedItem = inventoryItems.size() - 1;
        } else if(selectedItem >= inventoryItems.size()){
            selectedItem = 0;
        }
    }

    public void render(Graphics graphics){
        if(!active){
            return;
        }
        graphics.drawImage(Assets.inventoryScreen,handler.getWidth()/2 - invWidth/2,handler.getHeight()/2 - invHeight/2, invWidth, invHeight,null);

        int len = inventoryItems.size();
        if(len == 0){
            return;
        }
        for(int i = -5; i < 6; i++){
            if(selectedItem + i < 0 || selectedItem + i >= len){
                continue;
            }
            if(i == 0){
                Text.drawString(graphics, ">  "+inventoryItems.get(selectedItem).getName()+"  <",
                        invListCentreX, invListCentreY + i*invListSpacing, true, Color.yellow, Assets.font28);

            } else{
                Text.drawString(graphics, inventoryItems.get(selectedItem + i).getName(),
                        invListCentreX, invListCentreY + i*invListSpacing, true, Color.white, Assets.font28);
            }
        }
        Item item = inventoryItems.get(selectedItem);
        graphics.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
        Text.drawString(graphics, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.white, Assets.font28);
    }

    //inventory methods
    public void addItem(Item item){
        for(int i = 0; i < inventoryItems.size(); i++){
            Item tempItem = inventoryItems.get(i);
            if(tempItem.getId() == item.getId()){
                inventoryItems.get(i).setCount(tempItem.getCount() + item.getCount());
                return;
            }
        }
        inventoryItems.add(item);
    }


    //getters and setters
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public boolean isActive() {
        return active;
    }

    public ArrayList<Item> getInventoryItems() {
        return inventoryItems;
    }
}
