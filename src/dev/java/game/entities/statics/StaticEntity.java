package dev.java.game.entities.statics;

import dev.java.game.Handler;
import dev.java.game.entities.Entity;

public abstract class StaticEntity extends Entity {

    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }
}
