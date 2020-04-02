package dev.java.game.mission.colloector;

import java.awt.*;

public class CollectWood extends CollectorMission {

    public CollectWood(String title, String desc, int id) {
        super(title, desc, id, 1);
        targetItemID[0] = 0;
        finalProgress[0] = 1;
    }

    @Override
    public void render(Graphics graphics) {

    }

    @Override
    public void update() {
        super.update();
    }
}
