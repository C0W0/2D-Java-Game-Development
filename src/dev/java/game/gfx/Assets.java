package dev.java.game.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {


    private static final int width = 64;
    private static final int height = 64;

    public static Font font28, font20;

    //tiles
    public static BufferedImage grass, grassStone, dirt, dirtStone, tt2_grass, tt3_grass;
    public static BufferedImage pathVerticalLeft, pathVerticalRight, pathHorizontalTop, pathHorizontalBottom,
            pathCornerUpRight, pathCornerUpLeft, pathCornerDownLeft, pathCornerDownRight, pathCross;
    public static BufferedImage tt2_pathVerticalLeft, tt2_pathVerticalRight, tt2_pathHorizontalTop, tt2_pathHorizontalBottom,
            tt2_pathCornerUpRight, tt2_pathCornerUpLeft, tt2_pathCornerDownLeft, tt2_pathCornerDownRight, tt2_pathCross;
    public static BufferedImage [] water;
    public static BufferedImage tt1_roofTop, tt1_roofCommon, tt1_roofLeftTop, tt1_roofLeftMid, tt1_roofLeftBottom,
            tt1_roofRightTop, tt1_roofRightMid, tt1_roofRightBottom, tt1_halfRoofTop, tt1_SmallARoof;
    public static BufferedImage tt1_window, tt1_smokestack, tt1_balcony, tt1_doorTop, tt1_doorBottom,
            tt1_wallLeftTop, tt1_wallLeftMid, tt1_wallLeftBottom, tt1_wallMidTop, tt1_wallCommon1, tt1_wallMidBottom,
            tt1_wallRightTop, tt1_wallRightMid, tt1_wallRightBottom;
    public static BufferedImage tt2_roofTop, tt2_roofCommon, tt2_roofLeftTop, tt2_roofLeftMid, tt2_roofLeftBottom,
            tt2_roofRightTop, tt2_roofRightMid, tt2_roofRightBottom, tt2_halfRoofTop, tt2_SmallARoof;
    public static BufferedImage tt2_window, tt2_smokestack, tt2_balcony, tt2_doorTop, tt2_doorBottom,
            tt2_wallLeftTop, tt2_wallLeftMid, tt2_wallLeftBottom, tt2_wallMidTop, tt2_wallCommon1, tt2_wallMidBottom,
            tt2_wallRightTop, tt2_wallRightMid, tt2_wallRightBottom;
    public static BufferedImage tt3_pathVerticalLeft, tt3_pathVerticalRight, tt3_pathHorizontalTop, tt3_pathHorizontalBottom,
            tt3_pathCornerUpRight, tt3_pathCornerUpLeft, tt3_pathCornerDownLeft, tt3_pathCornerDownRight, tt3_pathCross;
    public static BufferedImage tt3_roofTop, tt3_roofCommon, tt3_roofLeftTop, tt3_roofLeftMid, tt3_roofLeftBottom,
            tt3_roofRightTop, tt3_roofRightMid, tt3_roofRightBottom, tt3_halfRoofTop, tt3_SmallARoof;
    public static BufferedImage tt3_window, tt3_smokestack, tt3_balcony, tt3_doorTop, tt3_doorBottom,
            tt3_wallLeftTop, tt3_wallLeftMid, tt3_wallLeftBottom, tt3_wallMidTop, tt3_wallCommon1, tt3_wallMidBottom,
            tt3_wallRightTop, tt3_wallRightMid, tt3_wallRightBottom;

    public static BufferedImage[][] a1_tiles;
    public static BufferedImage islandShape;
    public static BufferedImage beachDrySand, beachDryCrater, beachTransitionUp, beachTransitionDown,
            beachWetSand, beachWetCrater, beachShoreUp1, beachShoreUp2, beachOcean1, beachOcean2,
            beachShoreDown1, beachShoreDown2, beachGrassFlower, beachGrass1, beachGrass2, beachVerticalEast, beachVerticalWest,
        beachOceanTransition;
    public static BufferedImage[][] beach_diagonals, beach_Verticals;

    //entities
    public static BufferedImage invisible, tree;
    public static BufferedImage wood, apple, stone, potion, shield, sword;
    public static BufferedImage[] npcCrab;
    public static BufferedImage[] npcSlime;
    public static BufferedImage[] slimeMovementLeft;
    public static BufferedImage[] slimeMovementRight;
    public static BufferedImage[] slimeAttackLeft;
    public static BufferedImage[] slimeAttackRight;
    public static BufferedImage[] iceSlimeMovementLeft;

    //player
    public static BufferedImage player_neutral;
    public static BufferedImage [] player_down, player_up, player_left, player_right;
    public static BufferedImage [] attack_down, attack_up, attack_left, attack_right;

    //UI
    public static BufferedImage [] button_start, button_settings, button_back;
    public static BufferedImage [] button_up, button_down;
    public static BufferedImage [] craftButton;
    public static BufferedImage horizontalSlideTrack, horizontalSlider, horizontalTickMark, verticalSlideTrack, verticalSlider, verticalTickMark;
    public static BufferedImage inventoryScreen, missionScreen, craftingScreen;
    public static BufferedImage redSqr, blueSqr;

    //SDK stuff
    public static BufferedImage [] button_SDK, button_new, button_save;
//    public static BufferedImage [] grass_SDK, grassStone_SDK, dirt_SDK, dirtStone_SDK, pathV_SDK, pathH_SDK, pathUpRight_SDK, pathUpLeft_SDK,
//            pathDownRight_SDK, pathDownLeft_SDK;
    public static BufferedImage [] tree_SDK;
    //


    public static void init(){
        font28 = FontLoader.loadFont("res/fonts/BLKCHCRY.ttf", 28);
        font20 = FontLoader.loadFont("res/fonts/BLKCHCRY.ttf", 20);

        SpriteSheet slimeMovement = new SpriteSheet(ImageLoader.loadImage("/texture/slimeMovement.png"));
        SpriteSheet slimeAttack = new SpriteSheet(ImageLoader.loadImage("/texture/slimeAttack.png"));
        SpriteSheet iceSlimeMovement = new SpriteSheet(ImageLoader.loadImage("/texture/ice_slime_movement.png"));
        SpriteSheet idleSlime = new SpriteSheet(ImageLoader.loadImage("/texture/idleSlime.png"));
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/texture/SpriteSheet.png"));
        SpriteSheet sheet1 = new SpriteSheet(ImageLoader.loadImage("/texture/SpriteSheet1.png"));
        SpriteSheet townTiles = new SpriteSheet(ImageLoader.loadImage("/texture/RemixTownTiles.png"));
        SpriteSheet crabSheet = new SpriteSheet(ImageLoader.loadImage("/texture/crab.png"));
        SpriteSheet newTownTiles = new SpriteSheet(ImageLoader.loadImage("/texture/PortTilesSDK.png"));
        SpriteSheet oldTownTiles = new SpriteSheet(ImageLoader.loadImage("/texture/TownTilesO.png"));
        SpriteSheet harbourTiles = new SpriteSheet(ImageLoader.loadImage("/texture/FarHarbour.png"));
        SpriteSheet area1Tiles = new SpriteSheet(ImageLoader.loadImage("/texture/Area1Tiles.png"));
        SpriteSheet beachIslandTile1 = new SpriteSheet(ImageLoader.loadImage("/texture/beach.png"));


        //UI
        inventoryScreen = ImageLoader.loadImage("/texture/inventory_screen_new.png");
        missionScreen = ImageLoader.loadImage("/texture/MissionScreen.png");
        craftingScreen = ImageLoader.loadImage("/texture/CraftScreen.png");
        redSqr = ImageLoader.loadImage("/texture/NoEnoughMaterial.png");
        blueSqr = ImageLoader.loadImage("/texture/Selected.png");
        craftButton = new BufferedImage[]{sheet1.crop(320, 384, 128, 128),
                sheet1.crop(320, 384, 128, 128)};

        //player
        button_start = new BufferedImage[2];
        button_start[0] = sheet1.crop(0,0,width*2,height);
        button_start[1] = sheet1.crop(width*2,0,width*2,height);
        button_settings = new BufferedImage[2];
        button_settings[0] = sheet1.crop(width*4,0,width*2,height);
        button_settings[1] = sheet1.crop(width*6,0,width*2,height);
        button_back = new BufferedImage[2];
        button_back[0] = sheet1.crop(0,height*6,width*2,height);
        button_back[1] = sheet1.crop(width*2,height*6,width*2,height);

        horizontalSlideTrack = sheet1.crop(width*4,height*4,width*2,height);
        horizontalSlider = sheet1.crop(width*6,height*4,width,height);
        horizontalTickMark = sheet1.crop(width*7,height*4,width,height);
        verticalSlideTrack = sheet1.crop(width*4,height*5,width,height*2);
        verticalSlider = sheet1.crop(width*5,height*5,width,height);
        verticalTickMark = sheet1.crop(width*6,height*5,width,height);

        button_up = new BufferedImage[2];
        button_up[0] = sheet1.crop(0,height*5,width,height);
        button_up[1] = sheet1.crop(width,height*5,width,height);
        button_down = new BufferedImage[2];
        button_down[0] = sheet1.crop(width*2,height*5,width,height);
        button_down[1] = sheet1.crop(width*3,height*5,width,height);

        player_down = new BufferedImage[2];
        player_down[0] = sheet.crop(0,0,width,height);
        player_down[1] = sheet.crop(width,0,width,height);
        player_up = new BufferedImage[2];
        player_up[0] = sheet.crop(width*2,0,width,height);
        player_up[1] = sheet.crop(width*3,0,width,height);
        player_right = new BufferedImage[2];
        player_right[0] = sheet.crop(0,height,width,height);
        player_right[1] = sheet.crop(width,height,width,height);
        player_left = new BufferedImage[2];
        player_left[0] = sheet.crop(width*2,height,width,height);
        player_left[1] = sheet.crop(width*3,height,width,height);
        player_neutral = sheet.crop(0,height*2,width,height);

        attack_down = new BufferedImage[3];
        attack_down[0] = sheet.crop(width,height*2,width,height);
        attack_down[1] = sheet.crop(width,height*2,width,height);
        attack_down[2] = sheet.crop(width,height*2,width,height);
        attack_up = new BufferedImage[3];
        attack_up[0] = sheet.crop(width*2,height*2,width,height);
        attack_up[1] = sheet.crop(width*2,height*2,width,height);
        attack_up[2] = sheet.crop(width*2,height*2,width,height);
        attack_right = new BufferedImage[3];
        attack_right[0] = sheet.crop(width*3,height*2,width,height);
        attack_right[1] = sheet.crop(width*3,height*2,width,height);
        attack_right[2] = sheet.crop(width*3,height*2,width,height);
        attack_left = new BufferedImage[3];
        attack_left[0] = sheet.crop(0,height*3,width,height);
        attack_left[1] = sheet.crop(0,height*3,width,height);
        attack_left[2] = sheet.crop(0,height*3,width,height);

        //npc
        npcCrab = loadSpriteAsArray(crabSheet, 6, 4, 64, 64);
        npcSlime = loadSpriteAsArray(idleSlime, 2, 1, 64, 64);

        //entity
        tree = townTiles.crop(width*5,height*3,width,height*2);
        slimeMovementLeft = new BufferedImage[3];
        slimeMovementLeft[0] = slimeMovement.crop(0,0,width,height);
        slimeMovementLeft[1] = slimeMovement.crop(width,0,width,height);
        slimeMovementLeft[2] = slimeMovement.crop(width*2,0,width,height);
        slimeMovementRight = new BufferedImage[3];
        slimeMovementRight[0] = slimeMovement.crop(0,height,width,height);
        slimeMovementRight[1] = slimeMovement.crop(width,height,width,height);
        slimeMovementRight[2] = slimeMovement.crop(width*2,height,width,height);

        iceSlimeMovementLeft = new BufferedImage[3];
        iceSlimeMovementLeft[0] = iceSlimeMovement.crop(0, 0, width, height);
        iceSlimeMovementLeft[1] = iceSlimeMovement.crop(width, 0, width, height);
        iceSlimeMovementLeft[2] = iceSlimeMovement.crop(width*2, 0, width, height);


        slimeAttackLeft = new BufferedImage[3];
        slimeAttackLeft[0] = slimeAttack.crop(0,0,width,height);
        slimeAttackLeft[1] = slimeAttack.crop(width,0,width,height);
        slimeAttackLeft[2] = slimeAttack.crop(width*2,0,width,height);
        slimeAttackRight = new BufferedImage[3];
        slimeAttackRight[0] = slimeAttack.crop(0,height,width,height);
        slimeAttackRight[1] = slimeAttack.crop(width,height,width,height);
        slimeAttackRight[2] = slimeAttack.crop(width*2,height,width,height);

        //tiles
        grass = newTownTiles.crop(width*11,0,width,height);
        grassStone = sheet1.crop(width*3,height,width,height);
        dirt = townTiles.crop(width,height,width,height);
        dirtStone = sheet1.crop(width*6,height,width,height);

        pathVerticalLeft = newTownTiles.crop(width*6,height,width,height);
        pathVerticalRight = newTownTiles.crop(width*8,height,width,height);
        pathHorizontalTop = newTownTiles.crop(width*7,0,width,height);
        pathHorizontalBottom = newTownTiles.crop(width*7,height*2,width,height);
        pathCornerUpRight = newTownTiles.crop(width*6,0,width,height);
        pathCornerUpLeft = newTownTiles.crop(width*8,0,width,height);
        pathCornerDownLeft = newTownTiles.crop(width*6,height*2,width,height);
        pathCornerDownRight = newTownTiles.crop(width*8,height*2,width,height);
        pathCross = newTownTiles.crop(width*11,height,width,height);

        tt1_roofTop = newTownTiles.crop(0, 0, width, height);
        tt1_roofCommon = newTownTiles.crop(width, 0, width, height);
        tt1_roofLeftTop = newTownTiles.crop(width*9, 0, width, height);
        tt1_roofLeftMid = newTownTiles.crop(width*9, height, width, height);
        tt1_roofLeftBottom = newTownTiles.crop(width*9, height*2, width, height);
        tt1_roofRightTop = newTownTiles.crop(width*10, 0, width, height);
        tt1_roofRightMid = newTownTiles.crop(width*10, height, width, height);
        tt1_roofRightBottom = newTownTiles.crop(width*10, height*2, width, height);
        tt1_halfRoofTop = newTownTiles.crop(width, height, width, height);
        tt1_SmallARoof = newTownTiles.crop(0, height, width, height);
        tt1_window = newTownTiles.crop(0, height*2, width, height);
        tt1_smokestack = newTownTiles.crop(width*5, height*2, width, height);
        tt1_balcony = newTownTiles.crop(width, height*2, width, height);
        tt1_doorTop = newTownTiles.crop(width*5, 0, width, height);
        tt1_doorBottom = newTownTiles.crop(width*5, height, width, height);
        tt1_wallLeftTop = newTownTiles.crop(width*2, 0, width, height);
        tt1_wallLeftMid = newTownTiles.crop(width*2, height, width, height);
        tt1_wallLeftBottom = newTownTiles.crop(width*2, height*2, width, height);
        tt1_wallMidTop = newTownTiles.crop(width*3, 0, width, height);
        tt1_wallCommon1 = newTownTiles.crop(width*3, height, width, height);
        tt1_wallMidBottom = newTownTiles.crop(width*3, height*2, width, height);
        tt1_wallRightTop = newTownTiles.crop(width*4, 0, width, height);
        tt1_wallRightMid = newTownTiles.crop(width*4, height, width, height);
        tt1_wallRightBottom = newTownTiles.crop(width*4, height*2, width, height);

        tt2_grass = oldTownTiles.crop(width*11,0,width,height);

        tt2_pathVerticalLeft = oldTownTiles.crop(width*6,height,width,height);
        tt2_pathVerticalRight = oldTownTiles.crop(width*8,height,width,height);
        tt2_pathHorizontalTop = oldTownTiles.crop(width*7,0,width,height);
        tt2_pathHorizontalBottom = oldTownTiles.crop(width*7,height*2,width,height);
        tt2_pathCornerUpRight = oldTownTiles.crop(width*6,0,width,height);
        tt2_pathCornerUpLeft = oldTownTiles.crop(width*8,0,width,height);
        tt2_pathCornerDownLeft = oldTownTiles.crop(width*6,height*2,width,height);
        tt2_pathCornerDownRight = oldTownTiles.crop(width*8,height*2,width,height);
        tt2_pathCross = oldTownTiles.crop(width*11,height,width,height);

        tt2_roofTop = oldTownTiles.crop(0, 0, width, height);
        tt2_roofCommon = oldTownTiles.crop(width, 0, width, height);
        tt2_roofLeftTop = oldTownTiles.crop(width*9, 0, width, height);
        tt2_roofLeftMid = oldTownTiles.crop(width*9, height, width, height);
        tt2_roofLeftBottom = oldTownTiles.crop(width*9, height*2, width, height);
        tt2_roofRightTop = oldTownTiles.crop(width*10, 0, width, height);
        tt2_roofRightMid = oldTownTiles.crop(width*10, height, width, height);
        tt2_roofRightBottom = oldTownTiles.crop(width*10, height*2, width, height);
        tt2_halfRoofTop = oldTownTiles.crop(width, height, width, height);
        tt2_SmallARoof = oldTownTiles.crop(0, height, width, height);
        tt2_window = oldTownTiles.crop(0, height*2, width, height);
        tt2_smokestack = oldTownTiles.crop(width*5, height*2, width, height);
        tt2_balcony = oldTownTiles.crop(width, height*2, width, height);
        tt2_doorTop = oldTownTiles.crop(width*5, 0, width, height);
        tt2_doorBottom = oldTownTiles.crop(width*5, height, width, height);
        tt2_wallLeftTop = oldTownTiles.crop(width*2, 0, width, height);
        tt2_wallLeftMid = oldTownTiles.crop(width*2, height, width, height);
        tt2_wallLeftBottom = oldTownTiles.crop(width*2, height*2, width, height);
        tt2_wallMidTop = oldTownTiles.crop(width*3, 0, width, height);
        tt2_wallCommon1 = oldTownTiles.crop(width*3, height, width, height);
        tt2_wallMidBottom = oldTownTiles.crop(width*3, height*2, width, height);
        tt2_wallRightTop = oldTownTiles.crop(width*4, 0, width, height);
        tt2_wallRightMid = oldTownTiles.crop(width*4, height, width, height);
        tt2_wallRightBottom = oldTownTiles.crop(width*4, height*2, width, height);

        tt3_grass = harbourTiles.crop(width*11,0,width,height);

        tt3_pathVerticalLeft = harbourTiles.crop(width*6,height,width,height);
        tt3_pathVerticalRight = harbourTiles.crop(width*8,height,width,height);
        tt3_pathHorizontalTop = harbourTiles.crop(width*7,0,width,height);
        tt3_pathHorizontalBottom = harbourTiles.crop(width*7,height*2,width,height);
        tt3_pathCornerUpRight = harbourTiles.crop(width*6,0,width,height);
        tt3_pathCornerUpLeft = harbourTiles.crop(width*8,0,width,height);
        tt3_pathCornerDownLeft = harbourTiles.crop(width*6,height*2,width,height);
        tt3_pathCornerDownRight = harbourTiles.crop(width*8,height*2,width,height);
        tt3_pathCross = harbourTiles.crop(width*11,height,width,height);

        tt3_roofTop = harbourTiles.crop(0, 0, width, height);
        tt3_roofCommon = harbourTiles.crop(width, 0, width, height);
        tt3_roofLeftTop = harbourTiles.crop(width*9, 0, width, height);
        tt3_roofLeftMid = harbourTiles.crop(width*9, height, width, height);
        tt3_roofLeftBottom = harbourTiles.crop(width*9, height*2, width, height);
        tt3_roofRightTop = harbourTiles.crop(width*10, 0, width, height);
        tt3_roofRightMid = harbourTiles.crop(width*10, height, width, height);
        tt3_roofRightBottom = harbourTiles.crop(width*10, height*2, width, height);
        tt3_halfRoofTop = harbourTiles.crop(width, height, width, height);
        tt3_SmallARoof = harbourTiles.crop(0, height, width, height);
        tt3_window = harbourTiles.crop(0, height*2, width, height);
        tt3_smokestack = harbourTiles.crop(width*5, height*2, width, height);
        tt3_balcony = harbourTiles.crop(width, height*2, width, height);
        tt3_doorTop = harbourTiles.crop(width*5, 0, width, height);
        tt3_doorBottom = harbourTiles.crop(width*5, height, width, height);
        tt3_wallLeftTop = harbourTiles.crop(width*2, 0, width, height);
        tt3_wallLeftMid = harbourTiles.crop(width*2, height, width, height);
        tt3_wallLeftBottom = harbourTiles.crop(width*2, height*2, width, height);
        tt3_wallMidTop = harbourTiles.crop(width*3, 0, width, height);
        tt3_wallCommon1 = harbourTiles.crop(width*3, height, width, height);
        tt3_wallMidBottom = harbourTiles.crop(width*3, height*2, width, height);
        tt3_wallRightTop = harbourTiles.crop(width*4, 0, width, height);
        tt3_wallRightMid = harbourTiles.crop(width*4, height, width, height);
        tt3_wallRightBottom = harbourTiles.crop(width*4, height*2, width, height);

        a1_tiles = new BufferedImage[3][4];
        for(int y = 0; y < a1_tiles.length; y++){
            for(int x = 0; x < a1_tiles[y].length; x++){
                a1_tiles[y][x] = area1Tiles.crop(x*width, y*height, width, height);
            }
        }

        beachDryCrater = beachIslandTile1.crop(0, 0, width, height);
        beachDrySand = beachIslandTile1.crop(width, 0, width, height);
        beachTransitionUp = beachIslandTile1.crop(width*2, 0, width, height);
        beachTransitionDown = beachIslandTile1.crop(width*3, 0, width, height);
        beachWetCrater = beachIslandTile1.crop(0, height, width, height);
        beachWetSand = beachIslandTile1.crop(width, height, width, height);
        beachShoreUp1 = beachIslandTile1.crop(width*2, height, width, height);
        beachShoreUp2 = beachIslandTile1.crop(width*3, height, width, height);
        beachOcean1 = beachIslandTile1.crop(0, height*2, width, height);
        beachOcean2 = beachIslandTile1.crop(width, height*2, width, height);
        beachOceanTransition = beachIslandTile1.crop(0, height*3, width, height);
        beachShoreDown1 = beachIslandTile1.crop(width*2, height*2, width, height);
        beachShoreDown2 = beachIslandTile1.crop(width*3, height*2, width, height);
        beachGrassFlower = beachIslandTile1.crop(width, height*3, width, height);
        beachGrass1 = beachIslandTile1.crop(width*2, height*3, width, height);
        beachGrass2 = beachIslandTile1.crop(width*3, height*3, width, height);
        beach_diagonals = new BufferedImage[4][4];
        for(int y = 0; y < 4; y++){
            for(int x = 0; x < 4; x++){
                beach_diagonals[y][x] = beachIslandTile1.crop(width*(x+4), height*y, width, height);
            }
        }
        beach_Verticals = new BufferedImage[4][2];
        for(int y = 0; y < 4; y++){
            for(int x = 0; x < 2; x++){
                beach_Verticals[y][x] = beachIslandTile1.crop(width*(x+4), height*(y+4), width, height);
            }
        }
        beachVerticalWest = beachIslandTile1.crop(width*6, height*4, width, height);
        beachVerticalEast = beachIslandTile1.crop(width*7, height*4, width, height);

        islandShape = ImageLoader.loadImage("/texture/island.png");

        water = new BufferedImage[4];
        water[0] = townTiles.crop(0,height*4,width,height);
        water[1] = townTiles.crop(width,height*4,width,height);
        water[2] = townTiles.crop(width*2,height*4,width,height);
        water[3] = townTiles.crop(width*3,height*4,width,height);


        //items
        wood = sheet1.crop(width*7,height,width,height);
        apple = ImageLoader.loadImage("/texture/Apple.png");
        potion = ImageLoader.loadImage("/texture/Potion.png");

        //SDK stuff
        button_SDK = new BufferedImage[2];
        button_SDK[0] = sheet1.crop(0,height,width*2,height);
        button_SDK[1] = sheet1.crop(0,height*2,width*2,height);

        button_new = new BufferedImage[2];
        button_new[0] = sheet1.crop(0,height*4,width*2,height);
        button_new[1] = sheet1.crop(width*2,height*4,width*2,height);
        button_save = new BufferedImage[2];
        button_save[0] = sheet1.crop(width*3,height*3,width*2,height);
        button_save[1] = sheet1.crop(width*5,height*3,width*2,height);


//        grass_SDK = new BufferedImage[2];
//        grass_SDK[0] = grass;
//        grass_SDK[1] = sheet1.crop(width*2,height*2,width,height);
//        grassStone_SDK = new BufferedImage[2];
//        grassStone_SDK[0] = grassStone;
//        grassStone_SDK[1] = sheet1.crop(width*2,height,width,height);
//        dirt_SDK = new BufferedImage[2];
//        dirt_SDK[0] = dirt;
//        dirt_SDK[1] = sheet1.crop(width*3,height*2,width,height);
//        dirtStone_SDK = new BufferedImage[2];
//        dirtStone_SDK[0] = dirtStone;
//        dirtStone_SDK[1] = sheet1.crop(width*5,height,width,height);
//        pathV_SDK = new BufferedImage[2];
//        pathV_SDK[0] = pathVertical;
//        pathV_SDK[1] = sheet1.crop(width*4,height*2,width,height);
//        pathH_SDK = new BufferedImage[2];
//        pathH_SDK[0] = pathHorizontal;
//        pathH_SDK[1] = sheet1.crop(width*5,height*2,width,height);
//        pathUpRight_SDK = new BufferedImage[2];
//        pathUpRight_SDK[0] = pathCornerUpRight;
//        pathUpRight_SDK[1] = sheet1.crop(width*6,height*2,width,height);
//        pathUpLeft_SDK = new BufferedImage[2];
//        pathUpLeft_SDK[0] = pathCornerUpLeft;
//        pathUpLeft_SDK[1] = sheet1.crop(width*7,height*2,width,height);
//        pathDownRight_SDK = new BufferedImage[2];
//        pathDownRight_SDK[0] = pathCornerDownRight;
//        pathDownRight_SDK[1] = sheet1.crop(0,height*3,width,height);
//        pathDownLeft_SDK = new BufferedImage[2];
//        pathDownLeft_SDK[0] = pathCornerDownLeft;
//        pathDownLeft_SDK[1] = sheet1.crop(width,height*3,width,height);
//        tree_SDK = new BufferedImage[2];
//        tree_SDK[0] = tree;
//        tree_SDK[1] = sheet.crop(width,height*3,width,height);
    }
    /** Loading an entire SpriteSheet as a BufferedImage array (for animations)
     * @param xBlocks number of sub images in the width of the SpriteSheet
     * @param yBlocks number of sub images in the height of the SpriteSheet
     * @param height the height of a sub image, in pixels
     * @param width the width of a sub image, in pixels
     * @return the array of sub images in order
     */
    public static BufferedImage[] loadSpriteAsArray(SpriteSheet spriteSheet, int xBlocks, int yBlocks, int height, int width){
        BufferedImage[] images = new BufferedImage[xBlocks*yBlocks];
        for(int y = 0; y < yBlocks; y++){
            for(int x = 1; x <= xBlocks; x++){
                images[y * xBlocks + x - 1] = spriteSheet.crop((x-1)*width, y*height, width, height);
            }
        }
        return images;
    }

}
