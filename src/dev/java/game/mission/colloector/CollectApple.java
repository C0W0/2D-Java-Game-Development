package dev.java.game.mission.colloector;

import dev.java.game.items.Item;

public class CollectApple extends CollectorMission {

    public CollectApple(String title, String[] desc, int id, int count) {
        super(title, desc, id, 1);
        targetItemID[0] = Item.appleItem.getId();
        finalProgress[0] = count;
    }
}
