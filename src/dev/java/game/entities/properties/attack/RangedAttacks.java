package dev.java.game.entities.properties.attack;

import dev.java.game.Handler;
import dev.java.game.entities.Entity;
import dev.java.game.entities.creatures.active.Active;
import dev.java.game.utils.Utils;

public abstract class RangedAttacks extends Attacks {

    protected int range;
    protected int coverArea;
    protected boolean active = false;

    public RangedAttacks(Handler handler, int baseDamage, int type, int range, Active carrier) {
        super(handler, baseDamage, type, carrier);
        this.range = range;
    }

    public void triggerOn(){
        directionFunction = generateFunction();
        active = true;
    }

    public void triggerOff(){
        active = false;
        directionFunction = null;
    }

    @Override
    public void dealDamage() {
        for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if(Utils.getDistance(e, carrier) <= range) {
                if(Utils.vecDir(e.getX()-carrier.getX()) == Utils.vecDir(target.getX()-carrier.getX()) &&
                Utils.vecDir(e.getY()-carrier.getY()) == Utils.vecDir(target.getY()-carrier.getY())){
                    if (directionFunction.findDistance(e.getX(), e.getY()) < coverArea){
                        e.receiveDamage(baseDamage);
                    }
                }
            }
        }
    }
}
