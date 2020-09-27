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
    public static Tile horizontalPath = new Tile(Assets.pathHorizontalTop, 4, false);
    public static Tile verticalPath = new Tile(Assets.pathVerticalLeft, 5, false);
    public static Tile pathUpRight = new Tile(Assets.pathCornerUpRight, 6, false);
    public static Tile pathUpLeft = new Tile(Assets.pathCornerUpLeft, 7, false);
    public static Tile pathDownRight = new Tile(Assets.pathCornerDownRight, 8, false);
    public static Tile pathDownLeft = new Tile(Assets.pathCornerDownLeft, 9, false);
    public static Tile pathCross = new Tile(Assets.pathCross, 10, false);
//    public static Tile waterTile = new Tile(new Animation(100, Assets.water, false), 10, true);



    //default values
    public static final int TILEHEIGHT = 64;
    public static final int TILEWIDTH = 64;

    public static void init(){

        new Tile(Assets.tt1_roofTop, 50, true);
        new Tile(Assets.tt1_roofCommon, 51, true);
        new ComponentTile(Assets.tt1_roofCommon, 52, true,
                new TileAddonComponent(Assets.tt1_SmallARoof));
        new ComponentTile(Assets.grass, 53, true,
                new TileAddonComponent(Assets.tt1_roofLeftTop));
        new ComponentTile(Assets.tt1_roofTop, 54, true,
                new TileAddonComponent(Assets.tt1_roofLeftTop));
        new ComponentTile(Assets.tt1_roofCommon, 55, true,
                new TileAddonComponent(Assets.tt1_roofLeftTop));

        new Tile(Assets.tt1_roofLeftMid, 56, true);
        new ComponentTile(Assets.tt1_roofLeftMid, 57, true,
                new TileAddonComponent(Assets.tt1_smokestack));
        new ComponentTile(Assets.tt1_wallCommon1, 58, true,
                new TileAddonComponent(Assets.tt1_roofLeftBottom));
        new ComponentTile(Assets.grass, 59, true,
                new TileAddonComponent(Assets.tt1_roofRightTop));
        new ComponentTile(Assets.tt1_roofTop, 60, true,
                new TileAddonComponent(Assets.tt1_roofRightTop));
        new ComponentTile(Assets.tt1_roofCommon, 61, true,
                new TileAddonComponent(Assets.tt1_roofRightTop));

        new Tile(Assets.tt1_roofRightMid, 62, true);
        new ComponentTile(Assets.tt1_roofRightMid, 63, true,
                new TileAddonComponent(Assets.tt1_smokestack));
        new ComponentTile(Assets.tt1_wallCommon1, 64, true,
                new TileAddonComponent(Assets.tt1_roofRightBottom));
        new Tile(Assets.tt1_wallLeftTop, 65, true);
        new Tile(Assets.tt1_wallLeftMid, 66, true);
        new Tile(Assets.tt1_wallLeftBottom, 67, true);
        new Tile(Assets.tt1_wallMidTop, 68, true);
        new ComponentTile(Assets.tt1_wallMidTop, 69, true,
                new TileAddonComponent(Assets.tt1_halfRoofTop));
        new Tile(Assets.tt1_wallCommon1, 70, true);
        new Tile(Assets.tt1_wallMidBottom, 71, true);
        new ComponentTile(Assets.tt1_wallMidBottom, 72, true,
                new TileAddonComponent(Assets.tt1_balcony));
        new Tile(Assets.tt1_wallRightTop, 73, true);
        new Tile(Assets.tt1_wallRightMid, 74, true);
        new Tile(Assets.tt1_wallRightBottom, 75, true);
        new Tile(Assets.tt1_window, 76, true);
        new Tile(Assets.tt1_doorTop, 77, true);
        new Tile(Assets.tt1_doorBottom, 78, false);

        new Tile(Assets.a1_tiles[0][0], 79, true);
        new Tile(Assets.a1_tiles[0][1], 80, true);
        new Tile(Assets.a1_tiles[0][2], 81, true);
        new Tile(Assets.a1_tiles[0][3], 82, true);
        new Tile(Assets.a1_tiles[1][3], 83, true);
        new Tile(Assets.a1_tiles[2][3], 84, true);

        new Tile(Assets.a1_tiles[1][2], 85, false);
        new Tile(Assets.a1_tiles[2][2], 86, false);
        new Tile(Assets.a1_tiles[2][1], 87, true);

        new Tile(Assets.a1_tiles[1][0], 88, false);
        new Tile(Assets.a1_tiles[1][1], 89, false);
        new Tile(Assets.a1_tiles[2][0], 90, false);

    }


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
