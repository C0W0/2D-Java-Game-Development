package dev.java.game.tiles;

import dev.java.game.gfx.animations.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ComponentTile extends Tile{

    private TileAddonComponent component;

    public ComponentTile(BufferedImage texture, int id, boolean barrier, TileAddonComponent component) {
        super(texture, id, barrier);
        this.component = component;
    }

    public ComponentTile(Animation dynamicTexture, int id, boolean barrier, TileAddonComponent component) {
        super(dynamicTexture, id, barrier);
        this.component = component;
    }

    @Override
    public void update() {
        super.update();
        component.update();
    }

    @Override
    public void render(Graphics graphics, int x, int y) {
        super.render(graphics, x, y);
        component.render(graphics, x, y);
    }
}
