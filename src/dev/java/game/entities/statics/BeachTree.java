package dev.java.game.entities.statics;

import dev.java.game.gfx.Assets;
import dev.java.game.items.Item;
import dev.java.game.tiles.Tile;

import java.awt.*;

public class BeachTree extends StaticEntity {

    public BeachTree() {
        super(Tile.TILEWIDTH*3, Tile.TILEHEIGHT*3, 705);
        bounds.x = 20;
        bounds.y = Tile.TILEHEIGHT*2;
        bounds.width = 24;
        bounds.height = Tile.TILEHEIGHT;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.tree3, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()),
                width, height, null);
    }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int)(x + width/2 - Item.ITEMWIDTH/2), (int)(y + height - Item.ITEMHEIGHT),
                (int)(Math.random()*5)+1));
    }
}
