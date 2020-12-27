package dev.java.game.entities.statics;

import dev.java.game.gfx.Assets;
import dev.java.game.tiles.Tile;

import java.awt.*;

public class MagicalTree extends StaticEntity {

    public MagicalTree() {
        super(Tile.TILEWIDTH*3, Tile.TILEHEIGHT*4, 706);
        bounds.x = 58;
        bounds.y = Tile.TILEHEIGHT*2;
        bounds.width = 32;
        bounds.height = Tile.TILEHEIGHT*2;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.tree2, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()),
                width, height, null);
    }

    @Override
    public void die() {

    }
}
