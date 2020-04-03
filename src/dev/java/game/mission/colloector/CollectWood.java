package dev.java.game.mission.colloector;

import dev.java.game.items.Item;

import java.awt.*;

public class CollectWood extends CollectorMission {

    public CollectWood(String title, String[] desc, int id, int count) {
        super(title, desc, id, 1);
        targetItemID[0] = Item.woodItem.getId(); // do it this way to make the code more readable
        finalProgress[0] = count;
    }

}
