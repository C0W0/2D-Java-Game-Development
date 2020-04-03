package dev.java.game.entities.creatures;


import dev.java.game.Handler;
import dev.java.game.entities.Entity;
import dev.java.game.gfx.Animation;
import dev.java.game.gfx.Assets;
import dev.java.game.gfx.AttackAnimation;
import dev.java.game.inventory.Inventory;
import dev.java.game.mission.Mission;
import dev.java.game.mission.MissionManager;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Creature{

    //animations
    private Animation downAnim;
    private Animation upAnim;
    private Animation rightAnim;
    private Animation leftAnim;
    private AttackAnimation downAttack;
    private AttackAnimation upAttack;
    private AttackAnimation rightAttack;
    private AttackAnimation leftAttack;
    private String attackDirection;
    private boolean attackInAction;
    private float deltaAttack;
    //attack speed
    private long lastAttackTime, attackCooldown, attackTimer;
    //inventory
    private Inventory inventory;
    private MissionManager missionManager;



    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

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


        downAttack = new AttackAnimation(800, Assets.attack_down, true,1,"down");
        upAttack = new AttackAnimation(800, Assets.attack_up, true,1,"up");
        rightAttack = new AttackAnimation(800, Assets.attack_right, true,1,"right");
        leftAttack = new AttackAnimation(800, Assets.attack_left, true,1,"left");

        attackInAction = false;
        deltaAttack = 0.0f;

        inventory = new Inventory(handler);
        missionManager = new MissionManager(handler);

        //only for temp. use
        //TODO: put them to somewhere appropriate for giving missions (e.g., NPC)
        
        missionManager.addMission(Mission.collect10Woods);
        missionManager.addMission(Mission.collect5Woods);
        missionManager.addMission(Mission.collect10Apples);
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
        attackInAction = false;

        Rectangle collisionBox = getCollisionBounds(0,0);
        Rectangle attackRectangle = new Rectangle();
        int arSize = 20;
        attackRectangle.width = arSize;
        attackRectangle.height = arSize;

        if(handler.getKeyManager().aUp){
            attackRectangle.x = collisionBox.x + collisionBox.width/2 - arSize/2;
            attackRectangle.y = collisionBox.y - arSize;
            attackDirection = "up";
            attackInAction = true;
        } else if(handler.getKeyManager().aDown){
            attackRectangle.x = collisionBox.x + collisionBox.width/2 - arSize/2;
            attackRectangle.y = collisionBox.y + collisionBox.height;
            attackDirection = "down";
            attackInAction = true;
        } else if(handler.getKeyManager().aLeft){
            attackRectangle.x = collisionBox.x - arSize;
            attackRectangle.y = collisionBox.y + collisionBox.height/2 - arSize/2;
            attackDirection = "left";
            attackInAction = true;
        } else if(handler.getKeyManager().aRight){
            attackRectangle.x = collisionBox.x + collisionBox.width;
            attackRectangle.y = collisionBox.y + collisionBox.height/2 - arSize/2;
            attackDirection = "right";
            attackInAction = true;
        }else{
            attackInAction = false;
            return;
        }

        attackTimer = 0;

        for(int i = 0; i < handler.getWorld().getEntityManager().getEntities().size(); i++) { // this needs to be changed to a more efficient method
            Entity e = handler.getWorld().getEntityManager().getEntities().get(i);
            if (e.equals(this)) {
                continue;
            }
            if (e.getCollisionBounds(0, 0).intersects(attackRectangle)) {
                e.receiveDamage(1);
                return;
            }
        }
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if(inventory.isActive() || missionManager.isActive()){
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

    private BufferedImage getCurrentAttackFrame(){
        return getCurrentAttackDirection().getCurrentFrame();
    }

    private AttackAnimation getCurrentAttackDirection(){
        if(attackDirection.equals("up")){
            return upAttack;
        }else if(attackDirection.equals("down")){
            return downAttack;
        }else if(attackDirection.equals("left")){
            return leftAttack;
        }else if(attackDirection.equals("right")){
            return rightAttack;
        }else{
            return downAttack;
        }

    }


    @Override
    public void update() {

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
        if(attackInAction){
            deltaAttack = deltaAttack + 50/((float)attackCooldown/1000*60);
        }
        if(deltaAttack >= 10 || !attackInAction){
            attackInAction = false;
            deltaAttack = 0;
        }

        //inventory
        inventory.update();

        //missions
        missionManager.update();
    }

    @Override
    public void render(Graphics graphics) {
        if(attackInAction){
            graphics.drawImage(getCurrentAttackFrame(),(int)(getCurrentAttackDirection().getDeltaX((int)deltaAttack)+x - handler.getGameCamera().getxOffset()),
                    (int)(getCurrentAttackDirection().getDeltaY((int)deltaAttack)+y - handler.getGameCamera().getyOffset()), width, height,null);
        }
        if(handler.getKeyManager().aUp){
            graphics.drawImage(upAnim.getCurrentFrame(),(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()), width, height, null);
        } else if(handler.getKeyManager().aLeft){
            graphics.drawImage(leftAnim.getCurrentFrame(),(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()), width, height, null);
        } else{
            graphics.drawImage(getCurrentActionFrame(),(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()), width, height, null);
        }

    }

    @Override
    public void die() {
        System.out.println("You lose");
    }

    public void postRender(Graphics graphics){
        inventory.render(graphics);
        missionManager.render(graphics);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public MissionManager getMissionManager(){
        return missionManager;
    }
}
