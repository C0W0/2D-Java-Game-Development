package dev.java.game.entities;

import dev.java.game.Handler;
import dev.java.game.entities.creatures.active.Slime;
import dev.java.game.entities.creatures.npc.NPC1;
import dev.java.game.entities.statics.*;
import dev.java.game.gfx.Assets;
import dev.java.game.utils.Utils;

import java.awt.Graphics;
import java.awt.Rectangle;


public abstract class Entity implements Cloneable{

    /*    (UPDATE HERE)
    ------------------------
    Hostile/neutral: 101 ~ 400
    Slime: 201
    IceSlime: 202
    ------------------------
    NPC: 401 ~ 700
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        traders: 401 ~ 500
        WandererCrab (trade crab): 401
        MushroomTrader: 402
        FoxKeeper: 403
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        mission: 501 ~ 600
        NPC1 (mission crab): 501
        CrabSmith: 502
        FoxKeeper: 503
        cactus: 504
        chicken: 505
        hermit: 506
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        other: 601 ~ 700
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    ------------------------
    Static: 701 ~ 1000
    AirWall: 701
    Tree: 702 (not used)
    Trees: 703 ~ 706
    Pier: 720
    Rock: 730
    ------------------------
    Special: 1001+
    WorldGate: 1001
    SlimeSpawner: 1101
    SpiritSpawner: 1102
    SpiritGap: 1103
     */
    public static Entity[] entityList = new Entity[2048];
    public static Slime slime = new Slime();
    public static EStatic iceSlime = new EStatic(64, 64, Assets.iceSlimeMovementLeft[2], 202);
    public static EStatic staticTraderCrab = new EStatic(64, 64, Assets.npcCrab[1], 401);

    public static EStatic staticCrab = new EStatic(64, 64, Assets.npcCrab[0], 501);
    public static NPC1 npc1 = new NPC1();
    public static AirWall airWall = new AirWall();
    public static Tree tree = new Tree();
    public static GeneratorStatic slimeSpawner = new GeneratorStatic(128, 1101);

    public static void init(){
        new ForestTree();
        new BarrierTree();
        new BeachTree();
        new MagicalTree();
        new Pier();
        new Rock1();
        new Boat1();
        new Boat2();
        new GeneratorStatic(128, 1102);
        new GeneratorStatic(128, 1103);
        new EStatic(64, 64, Assets.crabSmith[0], 502);
        new EStatic(128, 64, Assets.foxKeeper[0], 503);
        new EStatic(64, 64, Assets.npcCactus, 504);
        new EStatic(64, 64, Assets.npcChicken, 505);
        new EStatic(64, 64, Assets.hermit[0], 506);
    }

    //Entities
    public static final int DEFAULT_HEALTH = 10;
    protected int health;
    protected int maxHP;
    protected boolean active;
    protected int faction;

    protected float x,y;
    protected int oX, oY; // o stands for original
    protected Handler handler;
    protected int width, height; //the size of the entity
    protected Rectangle bounds; //collision detection

    protected int id;

    public Entity (int width, int height, int id){
        x = 0;
        y = 0;
        this.width = width;
        this.height = height;
        active = true;
        health = DEFAULT_HEALTH; //TODO: CHANGE THIS!!!
        maxHP = health;
        this.id = id;
        entityList[id] = this;

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

    @Override
    public Entity clone() {
        Entity result = null;
        try {
            result = (Entity)super.clone();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public void initialize(Handler handler, float x, float y, int oX, int oY){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.oX = oX;
        this.oY = oY;
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

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    //new: add this
    public boolean isBackground(){
        return false;
    }
}
