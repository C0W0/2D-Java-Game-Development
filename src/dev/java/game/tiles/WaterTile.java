package dev.java.game.tiles;

import dev.java.game.gfx.Animation;
import dev.java.game.gfx.Assets;

public class WaterTile extends Tile {

    public WaterTile(int id) {
        super(new Animation(300, Assets.water, false), id);
    }

    @Override
    public boolean isBarrier() {
        return true;
    }
}
