package dev.java.game.items;

import dev.java.game.Handler;
import dev.java.game.gfx.Assets;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Item {

    //handler

    public static Item[] items = new Item[256];
    public static Item woodItem = new Item(Assets.wood, "wood", 0);
    public static Item appleItem = new Item(Assets.apple, "apple", 1);
    public static Item stoneItem = new Item(Assets.stone, "stone", 2);
    public static Item potionItem = new Item(Assets.potion, "potion", 3);
    public static Item shieldItem = new Item(Assets.shield, "shield", 4);
    public static Item swordItem = new Item(Assets.sword, "sword", 5);

    //class
    public static final int ITEMWIDTH = 32;
    public static final int ITEMHEIGHT = 32;

    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int id;

    protected Rectangle bounds;

    protected int x, y;
    protected int count;
    protected boolean pickedUP;

    public Item(BufferedImage texture, String name, int id){
        this.texture = texture;
        this.name = name;
        this.id = id;

        bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
        pickedUP = false;

        items[id] = this;
    }

    public void update(){

        if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)){
            pickedUP = true;
            handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
        }

    }

    public void render(Graphics graphics){
        if(handler == null){
            return;
        }
        render(graphics, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()));
    }

    public void render(Graphics graphics, int x, int y){
        graphics.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }

    public Item createNew(int count){
        Item i = new Item(texture, name, id);
        i.setPickedUP(true);
        i.count = count;
        return i;
    }

    public Item createNew(int x, int y, int count){
        Item i = new Item(texture, name, id);
        i.count = count;
        i.setPosition(x, y);
        return i;
    }


    //getters and setters
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPickedUP() {
        return pickedUP;
    }

    public void setPickedUP(boolean pickedUP) {
        this.pickedUP = pickedUP;
    }
}
