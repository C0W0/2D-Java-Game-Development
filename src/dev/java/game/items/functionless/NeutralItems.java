package dev.java.game.items.functionless;

import dev.java.game.items.Item;

import java.awt.image.BufferedImage;

public class NeutralItems extends Item {
    public NeutralItems(BufferedImage texture, String name, int id) {
        super(texture, name, id);
    }

    @Override
    public void itemActivity() {}

    public NeutralItems createNew(int x, int y, int count){
        NeutralItems i = new NeutralItems(texture, name, id);
        i.count = count;
        i.setPosition(x, y);
        return i;
    }
}
