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
    private HashMap<Integer,Integer> missingItems;
    private int selectedX = 0, selectedY = 0, scroll;
    private int recipeBaseX, recipeBaseY, gridSize, iconSize, recipeDXConstant, recipeDYConstant;
    private int craftingWindowBaseX, craftingWindowBaseY;
    private int[][] recipeLocations;
    private int lastSelection; // efficiency mechanic

    public Fabricator(Handler handler, Inventory inventory, String recipeFilePath){
        this.handler = handler;
        this.inventory = inventory;
        recipes = new HashMap<>();
        missingItems = new HashMap<>();
        recipeBaseX = (int)(54.f/512*inventory.invWidth+50);
        recipeBaseY = (int)(54.f/384*inventory.invHeight+25);
        gridSize = (int)(40.f/512*inventory.invWidth);
        iconSize = (int)(32.f/512*inventory.invWidth);
        recipeDXConstant = (int)(41.f/512*inventory.invWidth);
        recipeDYConstant = (int)(41.f/384*inventory.invHeight);
        craftingWindowBaseX = (int)(321.f/512*inventory.invWidth+50);
        craftingWindowBaseY = (int)(50.f/384*inventory.invHeight+25);
        scroll = 0; // WIP
        lastSelection = -1;
        recipeLocations = new int[35][5];
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
        if(recipes.get(recipeLocations[selectedY][selectedX]) == null ||
                !recipes.get(recipeLocations[selectedY][selectedX]).isActive)
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
            lastSelection = -1;
        }
        if(!active){
            return;
        }
        if(handler.getMouseManager().isLeftPressed()) {
            int mouseX = handler.getMouseManager().getMouseX();
            int mouseY = handler.getMouseManager().getMouseY();
            if(mouseX > recipeBaseX && mouseX < recipeBaseX+5*recipeDXConstant &&
                    mouseY > recipeBaseY && mouseY < recipeBaseY+7*recipeDYConstant){
                selectedX = computeSelectedLocationX(mouseX);
                selectedY = computeSelectedLocationY(mouseY);
            }
        }
        if(lastSelection == recipeLocations[selectedY][selectedX])
            return;
        else
            lastSelection = recipeLocations[selectedY][selectedX];
        if(!isCraftable())
            missingItems = computeMissingItemCount();
        else
            missingItems.clear();
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
                if(x == selectedX && y == selectedY)
                    graphics.drawImage(Assets.bluesqr,
                            x*recipeDXConstant + recipeBaseX - 2, (y-scroll)*recipeDYConstant + recipeBaseY - 2,
                            iconSize + 4, iconSize + 4, null);
                graphics.drawImage(Item.items[recipeLocations[y][x]].getTexture(),
                    x*recipeDXConstant + recipeBaseX, (y-scroll)*recipeDYConstant + recipeBaseY,
                    iconSize, iconSize, null);
            }
        }
        Recipe r = recipes.get(recipeLocations[selectedY][selectedX]);
        if(r == null)
            return;
        HashMap<Integer, Integer> tempItems = (HashMap<Integer, Integer>)missingItems.clone();
        for(int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if(r.requiredItems[y][x] != -1){
                    if (tempItems.get(r.requiredItems[y][x]) != null &&
                            tempItems.get(r.requiredItems[y][x]) != 0) {
                        graphics.drawImage(Assets.redSqr,
                                x * recipeDXConstant + craftingWindowBaseX, y * recipeDYConstant + craftingWindowBaseY,
                                iconSize, iconSize, null);
                        tempItems.put(r.requiredItems[y][x], tempItems.get(r.requiredItems[y][x]) - 1);
                    }
                    graphics.drawImage(Item.items[r.requiredItems[y][x]].getTexture(),
                            x*recipeDXConstant+craftingWindowBaseX, y*recipeDYConstant+craftingWindowBaseY,
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
        return Math.floorDiv((int)x, recipeDXConstant);
    }

    private int computeSelectedLocationY(float y){
        y -= recipeBaseY;
        return Math.floorDiv((int)y, recipeDYConstant)+scroll;
    }

    private HashMap<Integer,Integer> computeMissingItemCount(){
        HashMap<Integer,Integer> missingItemCount = new HashMap<>();
        if(recipeLocations[selectedY][selectedX] == 0)
            return missingItemCount;
        for(Map.Entry e: recipes.get(recipeLocations[selectedY][selectedX]).requitedItemsCount.entrySet()){
            int count = -(inventory.getItemCount((int)e.getKey()) - (int)e.getValue());
            if(count > 0){
                missingItemCount.put((int)e.getKey(), count);
            }
        }
        return missingItemCount;
    }
}
