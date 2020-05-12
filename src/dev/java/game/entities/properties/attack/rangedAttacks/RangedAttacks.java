package dev.java.game.entities.properties.attack.rangedAttacks;

import dev.java.game.Handler;
import dev.java.game.entities.Entity;
import dev.java.game.entities.creatures.active.Active;
import dev.java.game.entities.properties.attack.Attacks;
import dev.java.game.gfx.animations.attackAnimations.RangedAttackAnimations.RangedAttackAnimation;
import dev.java.game.utils.Utils;

import java.awt.*;
import java.util.ArrayList;

public abstract class RangedAttacks extends Attacks {


//    protected int dX, dY;
    protected float travelSpeed;
    protected ArrayList<RangedAttackCollision> collisionQueue = new ArrayList<>();
    protected ArrayList<RangedAttackAnimation> animations = new ArrayList<>();
    protected int range;
    protected int coverArea;

    public RangedAttacks(Handler handler, int baseDamage, int type, int range,
                         int travelSpeed, Active carrier) {
        super(handler, baseDamage, type, carrier);
        this.range = range;
        this.travelSpeed = travelSpeed;
    }

    @Override
    public void update() {
        super.update();
        for(int i = 0; i < collisionQueue.size(); i++){
            collisionQueue.get(i).update();
            if(collisionQueue.get(i).getDistance() >= range || collisionQueue.get(i).isHit()){
                collisionQueue.remove(i);
                animations.remove(i);
                i --;
            }
        }
        for(int i = 0; i < animations.size(); i++) {
            animations.get(i).update();
        }
    }

    @Override
    public void dealDamage() {
        for(RangedAttackCollision r : collisionQueue){
            for(Entity e : handler.getWorld().getEntityManager().getEntities()){
                if(r.intersects(e.getCollisionBounds(0,0))) {
                    if (carrier == null && e.equals(handler.getWorld().getPlayer()))
                        continue;
                    if (e.equals(carrier))
                        continue;
                    if (carrier != null) {
                        if (e.getFaction() != carrier.getFaction()) {
                            e.receiveDamage(baseDamage);
                            r.setHit(true);
                        }
                    }
                    if (carrier == null) {
                        e.receiveDamage(baseDamage);
                        System.out.println("called");
                        r.setHit(true);
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        for(RangedAttackAnimation a : animations){
            graphics.drawImage(a.getCurrentFrame(), (int) (a.getAnimationX()-handler.getGameCamera().getxOffset()),
                    (int) (a.getAnimationY()-handler.getGameCamera().getyOffset()), 64, 64, null);
        }
    }

    //    public void setdX(int dX) {
//        this.dX = dX;
//    }
//
//    public int getdY() {
//        return dY;
//    }

    public abstract void generateCollisionBox(int dX, int dY);
}
