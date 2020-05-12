package dev.java.game.entities.properties.attack;

import dev.java.game.Handler;
import dev.java.game.entities.Entity;
import dev.java.game.entities.creatures.active.Active;
import dev.java.game.utils.Utils;

import java.awt.*;

public abstract class Attacks {

    protected float x, y;
    protected Handler handler;
    protected int type;
    protected int baseDamage;
    protected final Active carrier;
    protected Entity target;
    //TODO: implement the reworked attack animation

    public Attacks(Handler handler, int baseDamage, int type, Active carrier){
        this.handler = handler;
        this.baseDamage = baseDamage;
        this.type = type;
        this.carrier = carrier;
    }

    public Attacks(Handler handler, int baseDamage, int type){
        this.handler = handler;
        this.baseDamage = baseDamage;
        this.type = type;
        this.carrier = null;
    }

    public void update(){
        if(carrier != null) {
            target = carrier.getTarget();
            x = carrier.getX();
            y = carrier.getY();
        }else {
            x = handler.getWorld().getPlayer().getX();
            y = handler.getWorld().getPlayer().getY();
        }
    }

    //leave empty until the attack animation is fixed
    public void render(Graphics graphics){

    }

    public abstract void dealDamage();


}
