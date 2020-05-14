package dev.java.game.entities.properties.attack.meleeAttacks;

import dev.java.game.Handler;
import dev.java.game.entities.creatures.active.Active;
import dev.java.game.gfx.Assets;
import dev.java.game.gfx.animations.Animation;

public class SlimeBash extends MeleeAttacks {
    public SlimeBash(Handler handler,  Active carrier) {
        super(handler, 1, 1, carrier);
        carrierAnimationA = new Animation(100, Assets.slimeAttackLeft, false);
        carrierAnimationB = new Animation(100, Assets.slimeAttackRight, false);
    }
}
