package dev.java.game.tiles;

import dev.java.game.gfx.Assets;

public class DirtRockTile extends Tile {

    public DirtRockTile(int id) {
        super(Assets.dirtStone, id);
    }

    @Override
    public boolean isBarrier() {
        return true;
    }
}
