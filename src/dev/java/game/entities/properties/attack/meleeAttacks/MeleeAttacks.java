package dev.java.game.entities.properties.attack.meleeAttacks;

import dev.java.game.Handler;
import dev.java.game.entities.Entity;
import dev.java.game.entities.creatures.active.Active;
import dev.java.game.entities.properties.attack.Attacks;
import dev.java.game.gfx.animations.Animation;

public abstract class MeleeAttacks extends Attacks {
    protected Entity target;

    public MeleeAttacks(Handler handler, int baseDamage, int type, Active carrier) {
        super(handler, baseDamage, type, carrier);
    }

    @Override
    public void update() {
        super.update();
        if(carrier != null)
             target = carrier.getTarget();
    }

    @Override
    public void dealDamage() {
//        System.out.println(target);
        if(target != null)
            target.receiveDamage(baseDamage);
    }
}
