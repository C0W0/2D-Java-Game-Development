package dev.java.game.entities.statics;

import dev.java.game.Handler;
import dev.java.game.gfx.Assets;
import dev.java.game.items.Item;
import dev.java.game.tiles.Tile;

import java.awt.Graphics;

public class Tree extends StaticEntity {

    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT*2);

        bounds.x = 25;
        bounds.y = 50;
        bounds.width = width - 50;
        bounds.height = height - 50;
        health = 2;
        id = 2;
    }

    @Override
    public void receiveDamage(int num) {
        super.receiveDamage(num);
        handler.getWorld().getItemManager().addItem(Item.appleItem.createNew((int)(x + width/2 - Item.ITEMWIDTH/2 + Math.random()*32-16),
                (int)(y + height - Item.ITEMHEIGHT + 32), (int)(Math.random()*2)+1));
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.tree, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int)(x + width/2 - Item.ITEMWIDTH/2), (int)(y + height - Item.ITEMHEIGHT), (int)(Math.random()*5)+1));
    }
}
