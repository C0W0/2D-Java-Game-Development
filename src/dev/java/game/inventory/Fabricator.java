package dev.java.game.inventory;

import dev.java.game.Handler;
import dev.java.game.gfx.Assets;
import dev.java.game.items.Item;
import dev.java.game.utils.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.awt.event.KeyEvent.VK_C;

public class Fabricator {
    private boolean active = false;
    private Handler handler;
    private Inventory inventory;
    private HashMap<Integer,Recipe> recipes;
    private int selectedX = 0, selectedY = 0, scroll;
    private int recipeBaseX, recipeBaseY, gridSize, iconSize, recipeDConstant;
    private int craftingWindowBaseX, craftingWindowBaseY;
    private int[][] recipeLocations;
//    private String filePath;

    public Fabricator(Handler handler, Inventory inventory, String recipeFilePath){
        this.handler = handler;
        this.inventory = inventory;
        recipes = new HashMap<>();
        recipeBaseX = (int)(54.f/512*inventory.invWidth+50);
        recipeBaseY = (int)(54.f/384*inventory.invHeight+25);
        gridSize = (int)(40.f/512*inventory.invWidth);
        iconSize = (int)(32.f/512*inventory.invWidth);
        recipeDConstant = (int)(41.f/512*inventory.invWidth);
        craftingWindowBaseX = (int)(321.f/512*inventory.invWidth+50);
        craftingWindowBaseY = (int)(50.f/384*inventory.invHeight+25);
        scroll = 0;
        recipeLocations = new int[35][5];
//        filePath = recipeFilePath;
        loadRecipes(recipeFilePath);
    }

    private void loadRecipes(String filePath){
        ArrayList<String> loadedRecipes = Utils.loadFileAsArrayList(filePath+"/recipes.wld");
        int x = 0, y = 0;
        for(String str: loadedRecipes){
            String[] data = str.split("\\s+");
            recipes.put(Utils.parseInt(data[0]), new Recipe(data));
            recipeLocations[y][x] = Utils.parseInt(data[0]);
            x = x < 1?x+1:0;
            y = x == 0?y+1:y;
        }
    }

    private boolean isCraftable(){
        if(!recipes.get(recipeLocations[selectedY][selectedX]).isActive)
            return false;
        for(Map.Entry e: recipes.get(recipeLocations[selectedY][selectedX]).requitedItemsCount.entrySet()){
            if(inventory.getItemCount((int)e.getKey()) < (int)e.getValue())
                return false;
        }
        return true;
    }

    public void craft(){
        if(!isCraftable())
            return;
        for(Map.Entry e: recipes.get(recipeLocations[selectedY][selectedX]).requitedItemsCount.entrySet())
            for(int i = 0; i < inventory.getInventoryItems().size(); i++)
                if(inventory.getInventoryItems().get(i).getId() == (int)e.getKey()){
                    inventory.getInventoryItems().get(i).setCount(
                            inventory.getInventoryItems().get(i).getCount() - (int)e.getValue());
                    i = inventory.getInventoryItems().size();
                }
        inventory.addItem(Item.items[recipeLocations[selectedY][selectedX]]);
    }


    public void update(){
        if(handler.getKeyManager().keyJustPressed(VK_C)){
            active = !active;
        }
        if(!active){
            return;
        }
    }

    public void render(Graphics graphics){
        if(!active)
            return;
        graphics.drawImage(Assets.craftingScreen,handler.getWidth()/2 - inventory.invWidth/2,
                handler.getHeight()/2 - inventory.invHeight/2,
                inventory.invWidth, inventory.invHeight,null);
        for(int y = scroll; y < scroll+7; y++){
            for(int x = 0; x < 5; x++){
                if(recipeLocations[y][x] == 0)
                    break;
                graphics.drawImage(Item.items[recipeLocations[y][x]].getTexture(),
                    x*recipeDConstant + recipeBaseX, y*recipeDConstant + recipeBaseY,
                    iconSize, iconSize, null);
            }
        }
        if(handler.getMouseManager().isLeftPressed()) {
            int mouseX = handler.getMouseManager().getMouseX();
            int mouseY = handler.getMouseManager().getMouseY();
            if(mouseX > recipeBaseX && mouseX < recipeBaseX+5*recipeDConstant &&
            mouseY > recipeBaseY && mouseY < recipeBaseY+7*recipeDConstant){
                selectedX = computeSelectedLocationX(mouseX);
                selectedY = computeSelectedLocationY(mouseY);
            }
        }
        Recipe r = recipes.get(recipeLocations[selectedY][selectedX]);
        if(r == null)
            return;
        for(int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if(r.requiredItems[y][x] != -1){
                    graphics.drawImage(Item.items[r.requiredItems[y][x]].getTexture(),
                            x*recipeDConstant+craftingWindowBaseX, y*recipeDConstant+craftingWindowBaseY,
                            iconSize, iconSize, null);
                }
            }
        }
    }

    public static class Recipe{
        public int craftingLevel;
        public boolean isActive = true;
        public int[][] requiredItems = new int[3][3];
        public HashMap<Integer,Integer> requitedItemsCount = new HashMap<>();

        public Recipe(String[] data){
            //the format of data: id, recipe*9, level
            for(int y = 0; y < 3; y++){
                for(int x = 0; x < 3; x++){
                    requiredItems[y][x] = Integer.parseInt(data[y*3+x+1]);
                    if(requiredItems[y][x] == -1) //omits -1, as it represents nothing
                        continue;
                    if(requitedItemsCount.get(requiredItems[y][x]) != null){
                        requitedItemsCount.put(requiredItems[y][x], requitedItemsCount.get(requiredItems[y][x])+1);
                    }else{
                        requitedItemsCount.put(requiredItems[y][x], 1);
                    }
                }
            }
            craftingLevel = Utils.parseInt(data[10]);
        }
    }

    public boolean isActive() {
        return active;
    }

    private int computeSelectedLocationX(float x){
        x -= recipeBaseX;
        return Math.floorDiv((int)x, recipeDConstant);
    }

    private int computeSelectedLocationY(float y){
        y -= recipeBaseY;
        return Math.floorDiv((int)y, recipeDConstant);
    }
}
