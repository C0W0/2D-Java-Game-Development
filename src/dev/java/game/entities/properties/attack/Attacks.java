package dev.java.game.entities.properties.attack;

import dev.java.game.Handler;
import dev.java.game.entities.Entity;
import dev.java.game.entities.creatures.active.Active;
import dev.java.game.utils.Utils;

import java.awt.*;

public abstract class Attacks {

    protected Handler handler;
    protected int type;
    protected int baseDamage;
    protected final Active carrier;
    protected Entity target;
    protected Utils.LinearFunction directionFunction;
    //TODO: implement the reworked attack animation

    public Attacks(Handler handler, int baseDamage, int type, Active carrier){
        this.handler = handler;
        this.baseDamage = baseDamage;
        this.type = type;
        this.carrier = carrier;

    }

    public void update(){
        target = carrier.getTarget();
//        System.out.println(target);
    }

    //leave empty until the attack animation is fixed
    public void render(Graphics graphics){

    }

    public abstract void dealDamage();

    public Utils.LinearFunction generateFunction(){
        return new Utils.LinearFunction(carrier.getX(), carrier.getY(), target.getX(), target.getY());
    }

}
