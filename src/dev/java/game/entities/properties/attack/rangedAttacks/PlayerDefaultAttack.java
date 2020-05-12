package dev.java.game.entities.properties.attack.rangedAttacks;

import dev.java.game.Handler;
import dev.java.game.entities.creatures.active.Active;
import dev.java.game.gfx.animations.attackAnimations.RangedAttackAnimations.PlayerDefaultAttackAnimation;

public class PlayerDefaultAttack extends RangedAttacks {

    public PlayerDefaultAttack(Handler handler) {
        super(handler, 1, 1, 256, 3, null);
    }

    @Override
    public void generateCollisionBox(int dX, int dY) {
        RangedAttackCollision rc = new RangedAttackCollision((int)x, (int)y, 64, 64, dX, dY, (int) travelSpeed);
        collisionQueue.add(rc);
        animations.add(new PlayerDefaultAttackAnimation((int)x, (int)y, dX, dY, (int)travelSpeed));
    }

    @Override
    public void update() {
        super.update();
        dealDamage();
    }
}
