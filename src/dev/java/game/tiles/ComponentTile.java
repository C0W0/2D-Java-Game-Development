package dev.java.game.tiles;

import dev.java.game.gfx.animations.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ComponentTile extends Tile{

    private TileAddonComponent[] components;

    public ComponentTile(BufferedImage texture, int id, boolean barrier, TileAddonComponent... components) {
        super(texture, id, barrier);
        this.components = components;
    }

    public ComponentTile(Animation dynamicTexture, int id, boolean barrier, TileAddonComponent... components) {
        super(dynamicTexture, id, barrier);
        this.components = components;
    }

    @Override
    public void update() {
        super.update();
        for(TileAddonComponent c: components)
            c.update();
    }

    @Override
    public void render(Graphics graphics, int x, int y) {
        super.render(graphics, x, y);
        for(TileAddonComponent c: components)
            c.render(graphics, x, y);
    }
}
