package dev.java.game.items.usable;

import dev.java.game.items.Item;

import java.awt.image.BufferedImage;

public class HealItem extends UsableItems {

    private int hpRegen;

    public HealItem(BufferedImage texture, String name, int id, int hpRegen) {
        super(texture, name, id);
        this.hpRegen = hpRegen;
    }

    @Override
    protected void onUse() {
        System.out.println("used"); // placeholder
        //handler.getWorld().getPlayer().changeHealth(hpRegen); //ideally do this, but leave for now
    }

    public HealItem createNew(int x, int y, int count){
        HealItem i = new HealItem(texture, name, id, hpRegen);
        i.count = count;
        i.setPosition(x, y);
        return i;
    }

    @Override
    public Item addToInv(int count) {
        HealItem i = new HealItem(texture, name, id, hpRegen);
        i.setPickedUP(true);
        i.count = count;
        return i;
    }
}
