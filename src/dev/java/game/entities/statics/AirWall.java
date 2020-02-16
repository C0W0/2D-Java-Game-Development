package dev.java.game.entities.statics;

import dev.java.game.Handler;
import dev.java.game.tiles.Tile;

import java.awt.Graphics;

public class AirWall extends StaticEntity{
    public AirWall(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        bounds.x = 0;
        bounds.y = 0;
        bounds.width = Tile.TILEWIDTH;
        bounds.height = Tile.TILEHEIGHT;
        id = 1;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {

    }

    @Override
    public void die() {}

    @Override
    public void receiveDamage(int num) {}
}
