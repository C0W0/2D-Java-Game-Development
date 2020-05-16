package dev.java.game.ui.functionUI;

import dev.java.game.ui.UIObject;
import dev.java.game.ui.clicker.ClickListener;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {

    private BufferedImage[] images;
    private ClickListener clicker;

    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
        super(x, y, width, height);
        this.images = images;
        this.clicker = clicker;
    }

    @Override
    public void update() {
        if(!active){
            return;
        }

    }

    @Override
    public void render(Graphics graphics) {
        if(!active){
            return;
        }

        if(hovering){
            graphics.drawImage(images[1], (int)x, (int)y, width, height, null);
        } else{
            graphics.drawImage(images[0], (int)x, (int)y, width, height, null);
        }

    }

    @Override
    public void onClick() {
        if(!active){
            return;
        }
        if(clicker != null){
            clicker.onClick();
        }
    }
}
