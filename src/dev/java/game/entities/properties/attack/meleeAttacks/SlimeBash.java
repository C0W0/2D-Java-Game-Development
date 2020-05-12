package dev.java.game.entities.properties.attack.meleeAttacks;

import dev.java.game.Handler;
import dev.java.game.entities.creatures.active.Active;

public class SlimeBash extends MeleeAttacks {
    public SlimeBash(Handler handler,  Active carrier) {
        super(handler, 1, 1, carrier);
    }
}
