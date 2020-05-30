package dev.java.game.entities;

import dev.java.game.Handler;
import dev.java.game.utils.Utils;

import java.awt.Graphics;
import java.awt.Rectangle;


public abstract class Entity {

    //Entities
    public static final int DEFAULT_HEALTH = 10;
    protected int health;
    protected int maxHP;
    protected boolean active;
    protected int faction;

    protected float x,y;
    protected Handler handler;
    protected int width, height; //the size of the entity
    protected Rectangle bounds; //collision detection

    protected int id;

    public Entity (Handler handler, float x, float y, int width, int height){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        active = true;
        health = DEFAULT_HEALTH; //TODO: CHANGE THIS!!!
        maxHP = health;

        bounds = new Rectangle(0, 0, width, height);//default
    }

    public abstract void update();

    public abstract void render(Graphics graphics);

    public abstract void die();


    public void receiveDamage(int num){
        health -= num;
        if(health <= 0){
            active = false;
            die();
        }
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int)(x + bounds.x + xOffset), (int)(y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public boolean checkEntityCollision(float xOffset, float yOffset){
        for(int i = 0; i < handler.getWorld().getEntityManager().getEntities().size(); i++){ // this needs to be changed to a more efficient method
            Entity e = handler.getWorld().getEntityManager().getEntities().get(i);
            if(e.equals(this)){
                continue;
            }
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
                return true;
            }
        }
        return false;
    }

    //utilities

    protected boolean isInRange(Entity e, int distance){
        return Utils.getDistance(this, e) <= distance;
    }

    //Getters and Setters

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getId() {
        return id;
    }

    public int getFaction() {
        return faction;
    }
}
