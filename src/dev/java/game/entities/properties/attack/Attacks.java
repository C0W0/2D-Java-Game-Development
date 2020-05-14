package dev.java.game.entities.properties.attack;

import dev.java.game.Handler;
import dev.java.game.entities.Entity;
import dev.java.game.entities.creatures.active.Active;
import dev.java.game.gfx.animations.Animation;
import dev.java.game.utils.Utils;

import java.awt.*;

public abstract class Attacks {

    protected float x, y;
    protected Handler handler;
    protected int type;
    protected int baseDamage;
    protected final Active carrier;
    protected Animation carrierAnimationA, carrierAnimationB, carrierAnimationC,
            carrierAnimationD, carrierAnimationE, carrierAnimationF;

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

    public Animation getOverridingAnimationA(){
        return carrierAnimationA;
    }

    public Animation getOverridingAnimationB(){
        return carrierAnimationB;
    }

    public Animation getOverridingAnimationC(){
        return carrierAnimationC;
    }

    public Animation getOverridingAnimationD(){
        return carrierAnimationD;
    }

    public Animation getOverridingAnimationE(){
        return carrierAnimationE;
    }

    public Animation getOverridingAnimationF(){
        return carrierAnimationF;
    }

}
