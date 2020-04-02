package dev.java.game.mission.colloector;

import dev.java.game.items.Item;
import dev.java.game.mission.Mission;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class CollectorMission extends Mission {

    protected int[] targetItemID, finalProgress, progress;

    public CollectorMission(String title, String desc, int id, int numberItems) {
        super(title, desc, id);
        targetItemID = new int[numberItems];
        finalProgress = new int[numberItems];
        progress = new int[numberItems];
        for(int i = 0; i < numberItems; i++){
            progress[i] = 0;
        }
    }

    @Override
    public boolean isCompleted() {
        ArrayList<Item> inventoryItems = handler.getWorld().getPlayer().getInventory().getInventoryItems();
        for(int i = 0; i < inventoryItems.size(); i++){
            Item tempItem = inventoryItems.get(i);
            for(int j = 0; j < targetItemID.length; j++){
                if(tempItem.getId() == targetItemID[j]){
                    if(tempItem.getCount() < finalProgress[j]){
                        finalProgress[j] = tempItem.getCount();
                    }else{
                        progress[j] = finalProgress[j];
                    }
                }
            }
        }
        return Arrays.equals(progress,finalProgress);
    }
}