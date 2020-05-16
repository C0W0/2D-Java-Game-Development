package dev.java.game.ui.functionUI;

import dev.java.game.ui.UIObject;
import dev.java.game.ui.clicker.ClickListener;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MapEditorButton extends UIObject {

    private BufferedImage[] images;
    private ClickListener clicker;

    public MapEditorButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker, boolean init) {
        super(x, y, width, height);
        this.images = images;
        this.clicker = clicker;
        selected = init;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {

        if(selected){
            graphics.drawImage(images[1], (int)x, (int)y, width, height, null);
        } else{
            graphics.drawImage(images[0], (int)x, (int)y, width, height, null);
        }

    }

    @Override
    public void onClick() {
        clicker.onClick();
        selected = true;
    }
}
