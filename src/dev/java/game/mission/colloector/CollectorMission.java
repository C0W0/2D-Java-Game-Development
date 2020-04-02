package dev.java.game.mission.colloector;

import dev.java.game.items.Item;
import dev.java.game.mission.Mission;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class CollectorMission extends Mission {

    protected int[] targetItemID, finalProgress, progress;

    public CollectorMission(String title, String desc, int id, int subMissions) {
        super(title, desc, id);
        targetItemID = new int[subMissions];
        finalProgress = new int[subMissions];
        progress = new int[subMissions];
        for(int i = 0; i < subMissions; i++){
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
                        progress[j] = tempItem.getCount();
                    }else{
                        progress[j] = finalProgress[j];
                    }
                }
            }
        }
        return Arrays.equals(progress,finalProgress);
    }
}
