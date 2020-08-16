package dev.java.game.tiles;

import dev.java.game.gfx.animations.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TileAddonComponent {


    private BufferedImage texture;
    private Animation dynamicTexture;

    public TileAddonComponent(BufferedImage texture){
        this.texture = texture;

    }

    public TileAddonComponent(Animation dynamicTexture){
        this.dynamicTexture = dynamicTexture;
    }

    public void update() {
        if(dynamicTexture != null)
            dynamicTexture.update();
    }

    public void render(Graphics graphics, int x, int y) {
        if(dynamicTexture != null)
            graphics.drawImage(dynamicTexture.getCurrentFrame(), x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
        else
            graphics.drawImage(texture, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
    }
}
