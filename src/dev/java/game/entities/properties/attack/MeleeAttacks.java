package dev.java.game.entities.properties.attack;

import dev.java.game.Handler;
import dev.java.game.entities.Entity;
import dev.java.game.entities.creatures.active.Active;

public abstract class MeleeAttacks extends Attacks{

    public MeleeAttacks(Handler handler, int baseDamage, int type, Active carrier) {
        super(handler, baseDamage, type, carrier);
    }

    @Override
    public void dealDamage() {
        target.receiveDamage(baseDamage);
    }
}
