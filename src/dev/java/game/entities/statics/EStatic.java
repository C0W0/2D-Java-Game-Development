package dev.java.game.entities.statics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EStatic extends StaticEntity {

    private BufferedImage image;

    public EStatic(int width, int height, BufferedImage image, int id) {
        super(width, height, id);
        this.image = image;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(image, (int)(x-handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die() {

    }
}
