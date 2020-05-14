package dev.java.game.gfx.animations;

import dev.java.game.utils.FrameTimeController;

import java.awt.image.BufferedImage;

public class TestAnimation extends Animation {

    private FrameTimeController ftc = new FrameTimeController();
    private boolean b = false;

    public TestAnimation(int speed, BufferedImage[] frames, boolean initAction) {
        super(speed, frames, initAction);
    }

    @Override
    public void update() {
        super.update();
        if(index == 0)
            if (!b) {
                ftc.start();
                b = true;
            }
        ftc.update();
        System.out.println("i:"+index);
        System.out.println("ftc:"+ftc.getStatus());
    }
}
