package dev.java.game.entities.statics;

import dev.java.game.gfx.Assets;
import dev.java.game.tiles.Tile;

import java.awt.*;

public class Rock1 extends StaticEntity {

    public Rock1() {
        super(Tile.TILEWIDTH, Tile.TILEHEIGHT, 730);
        bounds.x = 0;
        bounds.y = 0;
        bounds.height = height;
        bounds.width = width;
        health = 5;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.rock, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()),
                width, height, null);
    }

    @Override
    public void die() {

    }
}
