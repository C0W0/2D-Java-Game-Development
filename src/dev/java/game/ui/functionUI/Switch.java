package dev.java.game.ui.functionUI;

import dev.java.game.gfx.Assets;
import dev.java.game.gfx.Text;
import dev.java.game.ui.UIObject;

import java.awt.*;

public class Switch extends UIObject {

    private boolean isOn;

    public Switch(float x, float y, int width, int height) {
        super(x, y, width, height);
        isOn = false;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {
        Text.drawString(graphics, isOn?"ON":"OFF", (int)x+width/2, (int)y+height/2,
                true, isOn?Color.GREEN:Color.RED, Assets.font28);
    }

    @Override
    public void onClick() {
        isOn = !isOn;
    }

    public boolean isOn() {
        return isOn;
    }
}
