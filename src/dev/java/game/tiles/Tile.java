package dev.java.game.tiles;

import dev.java.game.gfx.Assets;
import dev.java.game.gfx.animations.Animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

    //static variables

    public static Tile[] tiles = new Tile[512];
    public static Tile grassTile = new Tile(Assets.grass, 0, false);
    public static Tile grassRockTile = new Tile(Assets.grassStone, 1, true);
    public static Tile dirtTile = new Tile(Assets.dirt, 2, false);
    public static Tile dirtRockTile = new Tile(Assets.dirtStone, 3, true);
    public static Tile horizontalPath = new Tile(Assets.pathHorizontal, 4, false);
    public static Tile verticalPath = new Tile(Assets.pathVertical, 5, false);
    public static Tile pathUpRight = new Tile(Assets.pathCornerUpRight, 6, false);
    public static Tile pathUpLeft = new Tile(Assets.pathCornerUpLeft, 7, false);
    public static Tile pathDownRight = new Tile(Assets.pathCornerDownRight, 8, false);
    public static Tile pathDownLeft = new Tile(Assets.pathCornerDownLeft, 9, false);
    public static Tile waterTile = new Tile(new Animation(100, Assets.water, false), 10, true);



    //default values
    public static final int TILEHEIGHT = 64;
    public static final int TILEWIDTH = 64;


    //tiles
    protected BufferedImage texture;
    protected Animation dynamicTexture;
    protected final int id;
    protected boolean isBarrier;

    public Tile(BufferedImage texture, int id, boolean isBarrier){
        this.texture = texture;
        this.id = id;
        this.isBarrier = isBarrier;

        tiles[id] = this;
    }

    public Tile(Animation dynamicTexture, int id, boolean isBarrier){
        this.dynamicTexture = dynamicTexture;
        this.id = id;
        this.isBarrier = isBarrier;

        tiles[id] = this;
    }


    //tick and render
    public void update(){
        if(dynamicTexture != null){
            dynamicTexture.update();
            texture = dynamicTexture.getCurrentFrame();
        }
    }

    public void render(Graphics graphics, int x, int y){
        graphics.drawImage(texture, x, y, TILEHEIGHT, TILEWIDTH, null);
    }


    //property methods
    public boolean isBarrier(){
        return isBarrier;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public BufferedImage getTexture(){
        if (dynamicTexture != null)
            return dynamicTexture.getCurrentFrame();
        else
            return texture;
    }

}
