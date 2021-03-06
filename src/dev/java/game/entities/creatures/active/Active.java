package dev.java.game.entities.creatures.active;

import dev.java.game.Handler;
import dev.java.game.entities.Entity;
import dev.java.game.entities.creatures.Creature;
import dev.java.game.entities.properties.attack.Attacks;
import dev.java.game.utils.Utils;

import java.awt.*;

public abstract class Active extends Creature {

    //assign values in the children class
    protected int spottingRange, giveUpRange, maxIdealRange, minIdealRange, patrolRange;
    protected int defence;
    protected Entity target;
    //TODO: complete rework for factions.


    //attack system
    protected Attacks attack;
    protected long lastAttackTime, attackCooldown, attackTimer;

    public Active(int width, int height, long attackCooldown, int id) {
        super(width, height, id);
        oX = 0;
        oY = 0;
        xMove = 0;
        yMove = 0;
        this.attackCooldown = attackCooldown;
        attackTimer = attackCooldown;
    }

    protected abstract void attack();

    protected void patrol(){
        // initial load
        if(xMove == 0 && yMove == 0){
            randomizePath();
        }
        if(x < oX-patrolRange || x > oX+patrolRange ||
                y < oY-patrolRange || y > oY+patrolRange){
            xMove = speed*((oX - x)/
                    (Utils.getDistance(this, oX, oY)));
            yMove = speed*((oY - y)/
                    (Utils.getDistance(this, oX, oY)));
            return;
        }

        while (x+xMove < oX-patrolRange || x+xMove > oX+patrolRange ||
        y+yMove < oY-patrolRange || y+yMove > oY+patrolRange) {
            randomizePath();
        }
    }

    @Override
    public void update() {
        if(target == null)
            target = handler.getWorld().getPlayer();
        if(faction == 1 && target != null){ // for now, 1 is hostile
            if(isInRange(target, spottingRange) &&
                    Utils.getDistance(target, oX, oY) < giveUpRange &&
                    Utils.getDistance(this, oX, oY) < giveUpRange){
                // movement calculations
                float rX = (target.getX() - x)/
                        (Utils.getDistance(this, target));
                float rY = (target.getY() - y)/
                        (Utils.getDistance(this, target));

                if(!isInRange(target, maxIdealRange)){
                    xMove = speed*rX;
                    yMove = speed*rY;
                }else if(isInRange(target, minIdealRange)){
                    xMove = -speed*rX;
                    yMove = -speed*rY;
                }else{
                    xMove = 0;
                    yMove = 0;
                    attackTimer += System.currentTimeMillis() - lastAttackTime;
                    lastAttackTime = System.currentTimeMillis();
                    if(attackTimer > attackCooldown){
                        attack();
                        attackTimer = 0;
                    }
                }
            }else{
                patrol();
            }
        }else{
            patrol();
        }
        move();
        attack.update();
    }

    private void randomizePath(){
        xMove = (float)(speed*Math.random())*Utils.pickNumber(-1, 1);
        yMove = Utils.Py.getB(xMove, speed)*Utils.pickNumber(-1, 1);
    }

    @Override
    public void die() {

    }

    public Entity getTarget() {
        return target;
    }
}
