package dev.java.game.items.equipment;

import dev.java.game.items.Item;

import java.awt.image.BufferedImage;

public abstract class Equipments extends Item {
    public Equipments(BufferedImage texture, String name, int id) {
        super(texture, name, id);
    }

    @Override
    public void itemActivity() {
        onEquip();
        for(Item i: handler.getWorld().getPlayer().getInventory().getInventoryItems())
            if(i.getId() == id)
                i.setCount(i.getCount()-1);
    }

    public abstract void onEquip();

    public abstract void onRemove();
}
