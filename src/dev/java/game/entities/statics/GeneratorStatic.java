package dev.java.game.entities.statics;

import dev.java.game.gfx.Assets;
import dev.java.game.gfx.Text;

import java.awt.*;

public class GeneratorStatic extends StaticEntity {

    private int generationRange;

    public GeneratorStatic(int generationRange, int id) {
        super(1, 1, id);
        this.generationRange = generationRange;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.drawRect((int)(x-generationRange-handler.getGameCamera().getxOffset()), (int)(y-generationRange-handler.getGameCamera().getyOffset()),
                generationRange*2, generationRange*2);
        Text.drawString(graphics, "Generator Range", (int)(x-handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()),
                true, Color.WHITE, Assets.font20);
    }

    @Override
    public void die() {

    }
}

