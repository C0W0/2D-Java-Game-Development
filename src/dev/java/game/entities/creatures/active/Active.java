package dev.java.game.entities.creatures.active;

import dev.java.game.Handler;
import dev.java.game.entities.creatures.Creature;
import dev.java.game.utils.Utils;

import java.awt.*;

public abstract class Active extends Creature {

    protected int spottingRange, attackingRange, giveUpRange, maxIdealRange, minIdealRange, patrolRange;
    protected int damage, defence;
    protected int faction; // for now, 1 is hostile

    protected final int oX, oY; // o stands for original

    public Active(Handler handler, float x, float y, int width, int height, int oX, int oY) {
        super(handler, x, y, width, height);
        this.oX = oX;
        this.oY = oY;
        xMove = 0;
        yMove = 0;
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
        y+yMove < oY-patrolRange || y+yMove > oY+patrolRange)
            randomizePath();
    }

    @Override
    public void update() {
        if(faction == 1){
            if(isInRange(handler.getWorld().getPlayer(), spottingRange) &&
                    Utils.getDistance(this, oX, oY) < giveUpRange){

                // movement calculations
                float rX = (handler.getWorld().getPlayer().getX() - x)/
                        (Utils.getDistance(this, handler.getWorld().getPlayer()));
                float rY = (handler.getWorld().getPlayer().getY() - y)/
                        (Utils.getDistance(this, handler.getWorld().getPlayer()));

                if(!isInRange(handler.getWorld().getPlayer(), maxIdealRange)){
                    xMove = speed*rX;
                    yMove = speed*rY;
                }else if(isInRange(handler.getWorld().getPlayer(), minIdealRange)){
                    xMove = -speed*rX;
                    yMove = -speed*rY;
                }else{
                    xMove = 0;
                    yMove = 0;
                    attack();
                }
            }else{
                patrol();
            }
        }else{
            patrol();
        }
        move();
    }

    private void randomizePath(){

    }

    @Override
    public void render(Graphics graphics) {
        xMove = (float)(speed*Math.random());
        yMove = Utils.Py.getB(xMove, speed);
    }

    @Override
    public void die() {

    }
}
