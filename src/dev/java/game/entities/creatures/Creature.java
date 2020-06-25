package dev.java.game.entities.creatures;

import dev.java.game.Handler;
import dev.java.game.entities.Entity;
import dev.java.game.tiles.Tile;

public abstract class Creature extends Entity {

    //default values
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64;
    public static final int DEFAULT_CREATURE_HEIGHT = 64;


    //creatures
    protected float speed;

    protected float xMove, yMove; // movement

    public Creature(int width, int height, int id) {
        super(width, height, id);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    //methods
    public void move(){
        if(!checkEntityCollision(xMove, 0f)){
            moveX();
        }
        if(!checkEntityCollision(0f, yMove)){
            moveY();
        }

    }

    //collision detection
    public void moveX(){
        if(xMove > 0){//right

            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

            if(!collisionWithTile(tx,(int)(y+bounds.y) / Tile.TILEHEIGHT) &&
            !collisionWithTile(tx,(int)(y+bounds.y+bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            } else{
                x = tx*Tile.TILEWIDTH - bounds.x - bounds.width - 1;
            }

        } else if(xMove < 0){//left

            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

            if(!collisionWithTile(tx,(int)(y+bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx,(int)(y+bounds.y+bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            } else{
                x = tx*Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
            }
        }
    }

    public void moveY(){
        if(yMove > 0){//down

            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

            if(!collisionWithTile((int)(x+bounds.x) / Tile.TILEHEIGHT,ty) &&
                    !collisionWithTile((int)(x+bounds.x+bounds.width) / Tile.TILEHEIGHT,ty)){
                y += yMove;
            } else{
               y = ty*Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
            }

        } else if(yMove < 0){//up

            int ty = (int) (y + yMove + bounds.y) / Tile.TILEWIDTH;

            if(!collisionWithTile((int)(x+bounds.x) / Tile.TILEHEIGHT,ty) &&
                    !collisionWithTile((int)(x+bounds.x+bounds.width) / Tile.TILEHEIGHT,ty)){
                y += yMove;
            } else{
                y = ty*Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y){
        return handler.getWorld().getTile(x,y).isBarrier();
    }

    public void changeHealth(int deltaHealth){
        health = Math.min(health + deltaHealth, maxHP);
    }

    //getters and setters
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }
}
