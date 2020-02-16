package dev.java.game.tiles;

import dev.java.game.gfx.Assets;

public class GrassRockTile extends Tile {

    public GrassRockTile(int id) {
        super(Assets.grassStone, id);
    }

    @Override
    public boolean isBarrier() {
        return true;
    }
}
