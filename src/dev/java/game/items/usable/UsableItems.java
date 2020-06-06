package dev.java.game.items.usable;

import dev.java.game.items.Item;

import java.awt.image.BufferedImage;

public abstract class UsableItems extends Item {

    public UsableItems(BufferedImage texture, String name, int id) {
        super(texture, name, id);
    }

    @Override
    public void onActive(){
        onUse();
        for(Item i: handler.getWorld().getPlayer().getInventory().getInventoryItems())
            if(i.getId() == id)
                i.setCount(i.getCount()-1);
    }

    protected abstract void onUse();
}
