package dev.java.game.entities.creatures.active;

import dev.java.game.Handler;
import dev.java.game.entities.creatures.Creature;
import dev.java.game.entities.properties.attack.meleeAttacks.SlimeBash;
import dev.java.game.gfx.animations.Animation;
import dev.java.game.gfx.Assets;
import dev.java.game.tiles.Tile;
import dev.java.game.utils.FrameTimeController;

import java.awt.*;

public class Slime extends Active {

    private Animation leftMove, rightMove, idle, currentAnimation;
    private FrameTimeController animationCtrlTimer = new FrameTimeController();

    public Slime() {
        super(Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT, 1000, 4);
        spottingRange = 256;
        maxIdealRange = 64;
        minIdealRange = 0; // melee
        patrolRange = 256;
        giveUpRange = 650;
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = Tile.TILEWIDTH;
        bounds.height = Tile.TILEHEIGHT;
        faction = 1;
        leftMove = new Animation(100, Assets.slimeMovementLeft, false);
        rightMove = new Animation(100, Assets.slimeMovementRight, false);
        idle = new Animation(100, Assets.npcSlime, false);
        currentAnimation = idle;
        health = 1;
        attack = new SlimeBash(handler, this);
    }

    @Override
    protected void attack() {
        attack.dealDamage();
        animationCtrlTimer.start(18);
        currentAnimation = target.getX()-x < 0?attack.getOverridingAnimationA():attack.getOverridingAnimationB();
    }

    @Override
    public void update(){
        super.update();
        animationCtrlTimer.update();
        if(!animationCtrlTimer.isTimeControllerOn()){
            currentAnimation = xMove < 0?leftMove:rightMove;
        }
        currentAnimation.update();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(currentAnimation.getCurrentFrame(), (int)(x - handler.getGameCamera().getxOffset()),
                (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
    }
}
