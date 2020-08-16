package dev.java.game.entities.creatures;


import dev.java.game.Handler;
import dev.java.game.entities.properties.attack.rangedAttacks.PlayerDefaultAttack;
import dev.java.game.entities.properties.attack.rangedAttacks.RangedAttacks;
import dev.java.game.gfx.animations.Animation;
import dev.java.game.gfx.Assets;
import dev.java.game.inventory.Fabricator;
import dev.java.game.inventory.Inventory;
import dev.java.game.mission.MissionManager;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Creature{

    //animations
    private Animation downAnim;
    private Animation upAnim;
    private Animation rightAnim;
    private Animation leftAnim;
    private RangedAttacks attack;
    //attack speed
    private long lastAttackTime, attackCooldown, attackTimer;
    //inventory
    private Inventory inventory;
    private Fabricator fabricator;
    private MissionManager missionManager;



    public Player(Handler handler, float x, float y) {
        super(Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT, 0);

        this.x = x;
        this.y = y;
        this.handler = handler;

        bounds.x = 21;
        bounds.y = 30;
        bounds.width = 32;
        bounds.height = 32;

        //attack timer
        attackCooldown = 800;
        attackTimer = attackCooldown;

        //animation
        downAnim = new Animation(150, Assets.player_down, false);
        upAnim = new Animation(150, Assets.player_up, false);
        rightAnim = new Animation(150, Assets.player_right, false);
        leftAnim = new Animation(150, Assets.player_left, false);


        attack = new PlayerDefaultAttack(handler);


        inventory = new Inventory(handler);
        fabricator = new Fabricator(handler, inventory, "res/worlds/worldSDK");
        missionManager = new MissionManager(handler);

        //only for temp. use

//        missionManager.addMission(Mission.collect10Woods);
//        missionManager.addMission(Mission.collect5Woods);
//        missionManager.addMission(Mission.collect10Apples);
    }

    private void checkAttacks(){

        attackTimer += System.currentTimeMillis() - lastAttackTime;
        lastAttackTime = System.currentTimeMillis();
        if(attackTimer < attackCooldown){
            return;
        }
        if(inventory.isActive()){
            return;
        }

        if(handler.getKeyManager().aUp){
            attack.generateAttack(0, -256);
        } else if(handler.getKeyManager().aDown){
            attack.generateAttack(0, 256);
        } else if(handler.getKeyManager().aLeft){
            attack.generateAttack(-256,0);
        } else if(handler.getKeyManager().aRight){
            attack.generateAttack(256,0);
        }

        attackTimer = 0;

    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if(inventory.isActive() || missionManager.isActive() || fabricator.isActive()){
            return;
        }
        //SDK stuff
        if(handler.getKeyManager().ctrl)
            return;
        //

        if(handler.getKeyManager().up){
            yMove = -speed;
        }
        if(handler.getKeyManager().down){
            yMove = speed;
        }
        if(handler.getKeyManager().left){
            xMove = -speed;
        }
        if(handler.getKeyManager().right){
            xMove = speed;
        }
    }

    private BufferedImage getCurrentActionFrame(){
        if(yMove < 0){
            return upAnim.getCurrentFrame();
        } else if(xMove > 0){
            return rightAnim.getCurrentFrame();
        } else if(xMove < 0){
            return leftAnim.getCurrentFrame();
        } else if(yMove > 0){
            return downAnim.getCurrentFrame();
        } else {
            return Assets.player_neutral;
        }
    }


    @Override
    public void update() {
        System.out.println((int)x/64+" "+(int)y/64);

        //animation
        downAnim.update();
        upAnim.update();
        rightAnim.update();
        leftAnim.update();

        //movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);

        //attack
        checkAttacks();
        attack.update();

        //inventory
        inventory.update();
        fabricator.update();

        //missions
        missionManager.update();

//        System.out.println(x+","+y);
    }

    @Override
    public void render(Graphics graphics) {
        if(handler.getKeyManager().aUp){
            graphics.drawImage(upAnim.getCurrentFrame(),(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if(handler.getKeyManager().aLeft){
            graphics.drawImage(leftAnim.getCurrentFrame(),(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()), width, height, null);
        } else{
            graphics.drawImage(getCurrentActionFrame(),(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()), width, height, null);
        }
        attack.render(graphics);

    }

    @Override
    public void die() {
        System.out.println("You lose");
    }

    public void postRender(Graphics graphics){
        inventory.render(graphics);
        missionManager.render(graphics);
        fabricator.render(graphics);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Fabricator getFabricator() {
        return fabricator;
    }

    public MissionManager getMissionManager(){
        return missionManager;
    }
}
