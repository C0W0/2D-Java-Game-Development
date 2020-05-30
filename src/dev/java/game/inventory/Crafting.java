package dev.java.game.inventory;

import dev.java.game.Handler;
import dev.java.game.items.Item;
import dev.java.game.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Crafting {
    private Handler handler;
    private Inventory inventory;
    private HashMap<Integer,Recipe> recipes;
    private int selected;

    public Crafting(Handler handler, Inventory inventory, String recipeFilePath){
        this.handler = handler;
        this.inventory = inventory;
    }

    private void loadRecipes(String filePath){
        ArrayList<String> loadedRecipes = Utils.loadFileAsArrayList(filePath+"/recipes.wld");
        for(String str: loadedRecipes){
            String[] data = str.split("\\s+");
            recipes.put(Utils.parseInt(data[0]), new Recipe(data));
        }
    }

    private boolean isCraftable(){
        if(!recipes.get(selected).isActive)
            return false;
        for(Map.Entry e: recipes.get(selected).requitedItemsCount.entrySet()){
            if(inventory.getItemCount((int)e.getKey()) < (int)e.getValue())
                return false;
        }
        return true;
    }

    public void craft(){
        if(!isCraftable())
            return;
        for(Map.Entry e: recipes.get(selected).requitedItemsCount.entrySet())
            for(int i = 0; i < inventory.getInventoryItems().size(); i++)
                if(inventory.getInventoryItems().get(i).getId() == (int)e.getKey()){
                    inventory.getInventoryItems().get(i).setCount(
                            inventory.getInventoryItems().get(i).getCount() - (int)e.getValue());
                    i = inventory.getInventoryItems().size();
                }
        inventory.addItem(Item.items[selected]);
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public static class Recipe{
        public int craftingLevel;
        public boolean isActive = true;
        public int[][] requiredItems = new int[3][3];
        public HashMap<Integer,Integer> requitedItemsCount = new HashMap<>();

        public Recipe(String[] data){
            int[][] requiredItems = new int[3][3];
            for(int y = 0; y < 3; y++){
                for(int x = 0; x < 3; x++){
                    requiredItems[y][x] = Integer.parseInt(data[y*3+x+1]);
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

}
