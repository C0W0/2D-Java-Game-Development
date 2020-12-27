package dev.java.game.entities.statics;

import dev.java.game.gfx.Assets;
import dev.java.game.items.Item;
import dev.java.game.tiles.Tile;

import java.awt.*;

public class Pier extends StaticEntity {

    public Pier() {
        super(Tile.TILEWIDTH*2, Tile.TILEHEIGHT*2, 720);
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 1;
        bounds.height = 1;
    }

    @Override
    public void receiveDamage(int num) {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.pier, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()),
                width, height, null);
    }

    @Override
    public void die() {

    }

    @Override
    public boolean isBackground() {
        return true;
    }
}
