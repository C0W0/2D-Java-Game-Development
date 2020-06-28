package dev.java.game.inventory;

import dev.java.game.Handler;
import dev.java.game.gfx.Assets;
import dev.java.game.gfx.Text;
import dev.java.game.items.Item;

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;

public class Inventory {

    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> inventoryItems;
    int invHeight, invWidth;
    private int selectedX = 0, selectedY = 0, scroll;
    private int itemBaseX, itemBaseY, iconSize, itemDXConstant, itemDYConstant;
    private int invImageX, invImageY;
    private int invImageWidth, invImageHeight;

    public Inventory(Handler handler){
        this.handler = handler;
        inventoryItems = new ArrayList<>();

        invHeight = handler.getHeight()-50;
        invWidth = handler.getWidth()-100;
        itemDXConstant = (int)(41.f/512*invWidth);
        itemDYConstant = (int)(41.f/384*invHeight);
        scroll = 0; // WIP
        iconSize = (int)(32.f/512*invWidth);
        itemBaseX = (int)(54.f/512*invWidth+50);
        itemBaseY = (int)(54.f/384*invHeight+25);
        invImageX = (int)(748.f/980*invWidth+50);
        invImageY = (int)(58.f/670*invHeight+25);
        invImageWidth = (int)(112.f/980*invWidth);
        invImageHeight = (int)(112.f/670*invHeight);
    }

    public void update(){
        if(handler.getKeyManager().keyJustPressed(VK_I)){
            active = !active;
        }
        if(!active){
            return;
        }

        for(int i = 0; i < inventoryItems.size(); i++) {
            if (inventoryItems.get(i).getCount() == 0) {
                inventoryItems.remove(i);
                i--;
            }
        }
        if(handler.getMouseManager().isLeftPressed()) {
            int mouseX = handler.getMouseManager().getMouseX();
            int mouseY = handler.getMouseManager().getMouseY();
            if(mouseX > itemBaseX && mouseX < itemBaseX+5*itemDXConstant &&
                    mouseY > itemBaseY && mouseY < itemBaseY +7*itemDYConstant){
                selectedX = computeSelectedLocationX(mouseX);
                selectedY = computeSelectedLocationY(mouseY);
            }
        }
    }

    public void render(Graphics graphics){
        if(!active){
            return;
        }
        graphics.drawImage(Assets.inventoryScreen,handler.getWidth()/2 - invWidth/2,handler.getHeight()/2 - invHeight/2, invWidth, invHeight,null);

        for(int y = scroll; y < scroll+7; y++){
            for(int x = 0; x < 5; x++){
                if(inventoryItems.size() <= y*5+x)
                    break;
                if(x == selectedX && y == selectedY)
                    graphics.drawImage(Assets.blueSqr,
                            x*itemDXConstant + itemBaseX - 2, (y-scroll)*itemDYConstant + itemBaseY - 2,
                            iconSize + 4, iconSize + 4, null);
                graphics.drawImage(inventoryItems.get(y*5+x).getTexture(),
                        x*itemDXConstant + itemBaseX, (y-scroll)*itemDYConstant + itemBaseY,
                        iconSize, iconSize, null);
            }
        }
//        Item item = inventoryItems.get(selectedY*5+selectedX);
//        graphics.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
//        Text.drawString(graphics, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.white, Assets.font28);
    }

    private int computeSelectedLocationX(float x){
        x -= itemBaseX;
        return Math.floorDiv((int)x, itemDXConstant);
    }

    private int computeSelectedLocationY(float y){
        y -= itemBaseY;
        return Math.floorDiv((int)y, itemDYConstant)+scroll;
    }

    //inventory methods
    public void addItem(Item item){
        if(item.getHandler() == null)
            item.setHandler(handler);
        for(int i = 0; i < inventoryItems.size(); i++){
            Item tempItem = inventoryItems.get(i);
            if(tempItem.getId() == item.getId()){
                inventoryItems.get(i).setCount(tempItem.getCount() + item.getCount());
                return;
            }
        }
        inventoryItems.add(item);
    }

    public int getItemCount(int id){
        for(Item i : inventoryItems)
            if(i.getId() == id)
                return i.getCount();

        return 0;
    }

    public void deductItem(int id, int count){
        for(Item i : inventoryItems)
            if(i.getId() == id)
                i.setCount(i.getCount()-count);
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
