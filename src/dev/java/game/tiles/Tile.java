package dev.java.game.tiles;

import dev.java.game.gfx.Animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Tile {

    //static variables

    public static Tile[] tiles = new Tile[512];
    public static Tile grassTile = new GrassTile(0);
    public static Tile grassRockTile = new GrassRockTile(1);
    public static Tile dirtTile = new DirtTile(2);
    public static Tile dirtRockTile = new DirtRockTile(3);
    public static Tile horizontalPath = new HorizontalPath(4);
    public static Tile verticalPath = new VerticalPath(5);
    public static Tile pathUpRight = new PathUpRight(6);
    public static Tile pathUpLeft = new PathUpLeft(7);
    public static Tile pathDownRight = new PathDownRight(8);
    public static Tile pathDownLeft = new PathDownLeft(9);
    public static Tile waterTile = new WaterTile(10);



    //default values
    public static final int TILEHEIGHT = 64;
    public static final int TILEWIDTH = 64;


    //tiles
    protected BufferedImage texture;
    protected Animation dynamicTexture;
    protected final int id;

    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public Tile(Animation dynamicTexture, int id){
        this.dynamicTexture = dynamicTexture;
        this.id = id;

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
        return false;
    }


    //getters and setters
    public int getId() {
        return id;
    }

}
