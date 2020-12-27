package dev.java.game.entities.statics;

import dev.java.game.gfx.Assets;
import dev.java.game.tiles.Tile;

import java.awt.*;

public class Boat1 extends StaticEntity {


    public Boat1() {
        super(Tile.TILEWIDTH*3, Tile.TILEHEIGHT*2, 721);
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height;
    }

    @Override
    public void receiveDamage(int num) {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.boat1, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()),
                width, height, null);
    }

    @Override
    public void die() {

    }

}
