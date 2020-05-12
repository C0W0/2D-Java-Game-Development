package dev.java.game.gfx.animations.attackAnimations.RangedAttackAnimations;

import dev.java.game.gfx.Assets;

public class PlayerDefaultAttackAnimation extends RangedAttackAnimation {

    public PlayerDefaultAttackAnimation(int oX, int oY, int dX, int dY, int moveSpeed) {
        super(800, Assets.attack_left, moveSpeed, oX, oY, dX, dY);
    }

}
