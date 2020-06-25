package dev.java.game.entities.creatures.npc;

import dev.java.game.Handler;
import dev.java.game.entities.creatures.Creature;
import dev.java.game.tiles.Tile;

import java.awt.Rectangle;

public abstract class NPC extends Creature {

    protected int interRange;
    protected String[] messages;


    public NPC(int width, int height, int id) {
        super(width, height, id);
    }

    public boolean interactionCheck(){
//        return isInRangeOf(handler.getWorld().getPlayer(), interRange*64);
        // the circular distance ("actual" distance)
        return new Rectangle((int)(-interRange*Tile.TILEWIDTH+x), (int)(-interRange*Tile.TILEHEIGHT+y),
                interRange*Tile.TILEWIDTH*2+width, interRange*Tile.TILEHEIGHT*2+height).
                intersects(handler.getWorld().getPlayer().getCollisionBounds(0,0));
        // block distance
    }

    protected abstract void interact();

    protected abstract void assignMission(int missionID);

    @Override
    public void update() {
        if(interactionCheck()){
            interact();
        }
    }
}
