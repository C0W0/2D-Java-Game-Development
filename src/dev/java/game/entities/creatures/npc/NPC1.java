package dev.java.game.entities.creatures.npc;

import dev.java.game.Handler;
import dev.java.game.entities.creatures.Creature;
import dev.java.game.gfx.Animation;
import dev.java.game.gfx.Assets;
import dev.java.game.tiles.Tile;

import java.awt.Rectangle;
import java.awt.Graphics;

public class NPC1 extends NPC {

    private Animation dynamicTexture;

    public NPC1(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_WIDTH);
        interRange = 1;
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = Tile.TILEWIDTH;
        bounds.height = Tile.TILEHEIGHT;
        dynamicTexture = new Animation(5, Assets.npcCrab, false);
        id = 3;
    }

    @Override
    public void receiveDamage(int num) {}

    @Override
    protected void interact() {
        assignMission(1);
    }

    @Override
    protected void assignMission(int missionID) {

    }

    @Override
    public void update() {
        super.update();
        dynamicTexture.update();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(dynamicTexture.getCurrentFrame(), (int)(x - handler.getGameCamera().getxOffset()),
                (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die() {

    }
}
