package dev.java.game.entities.statics;

import dev.java.game.gfx.Assets;
import dev.java.game.items.Item;
import dev.java.game.tiles.Tile;

import java.awt.*;

public class ForestTree extends StaticEntity {

    public ForestTree() {
        super(Tile.TILEWIDTH*2, Tile.TILEHEIGHT*4, 703);
        bounds.x = 0;
        bounds.y = Tile.TILEHEIGHT*2;
        bounds.width = Tile.TILEWIDTH*2;
        bounds.height = Tile.TILEHEIGHT*2;
    }

    public ForestTree(int id){
        super(Tile.TILEWIDTH*2, Tile.TILEHEIGHT*4, id);
        bounds.x = 0;
        bounds.y = Tile.TILEHEIGHT*2;
        bounds.width = Tile.TILEWIDTH*2;
        bounds.height = Tile.TILEHEIGHT*2;
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
        graphics.drawImage(Assets.tree1, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()),
                width, height, null);
    }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int)(x + width/2 - Item.ITEMWIDTH/2), (int)(y + height - Item.ITEMHEIGHT), (int)(Math.random()*5)+1));
    }
}
