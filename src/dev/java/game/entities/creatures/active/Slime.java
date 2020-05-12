package dev.java.game.entities.creatures.active;

import dev.java.game.Handler;
import dev.java.game.entities.creatures.Creature;
import dev.java.game.entities.properties.attack.meleeAttacks.SlimeBash;
import dev.java.game.gfx.animations.Animation;
import dev.java.game.gfx.Assets;
import dev.java.game.tiles.Tile;

import java.awt.*;

public class Slime extends Active {

    private Animation dynamicTexture;

    public Slime(Handler handler, float x, float y, int oX, int oY) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT, oX, oY, 1000);
        spottingRange = 256;
        maxIdealRange = 64;
        minIdealRange = 0; // melee
        patrolRange = 256;
        giveUpRange = 650;
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = Tile.TILEWIDTH;
        bounds.height = Tile.TILEHEIGHT;
        faction = 1;
        dynamicTexture = new Animation(100, Assets.npcSlime, false);
        health = 1;
        attack = new SlimeBash(handler, this);
    }

    @Override
    protected void attack() {
        attack.dealDamage();
    }

    @Override
    public void update(){
        super.update();
        dynamicTexture.update();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(dynamicTexture.getCurrentFrame(), (int)(x - handler.getGameCamera().getxOffset()),
                (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
    }
}
