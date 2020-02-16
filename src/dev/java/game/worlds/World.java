package dev.java.game.worlds;

import dev.java.game.Handler;
import dev.java.game.entities.Entity;
import dev.java.game.entities.EntityManager;
import dev.java.game.entities.creatures.Player;
import dev.java.game.entities.statics.AirWall;
import dev.java.game.entities.statics.Tree;
import dev.java.game.items.ItemManager;
import dev.java.game.tiles.Tile;
import dev.java.game.utils.Utils;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class World {

    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int [][] worldTiles;//a 2d array which associates the type of tiles to the location (x,y)

    private int xStart;
    private int xEnd;
    private int yStart;
    private int yEnd;

    //entities
    private EntityManager entityManager;
    private Player player;
    private Entity[][] worldEntities;
    private int[] loadedEntities;

    //items
    private ItemManager itemManager;

    //SDK stuff
    private File mapFile, entityFile;
    private String mapPath, entityPath, folderPath;
    private int sdkTileID, sdkEntityID;
    private boolean entityEditing;

    public World(Handler handler, String path){
        player = new Player(handler,Tile.TILEWIDTH,Tile.TILEHEIGHT);
        entityManager = new EntityManager(handler, player);
        folderPath = path;
        mapPath = (path+"/world.wld");
        entityPath = (path+"/entity.wld");
        this.handler = handler;
        loadWorld(path);
        //SDK stuff
        mapFile = new File(mapPath);
        entityFile = new File(entityPath);
        sdkTileID = 0;
        sdkEntityID = 0;
        player.setX(spawnX*Tile.TILEWIDTH);
        player.setY(spawnY*Tile.TILEHEIGHT);
        entityEditing = false;
        //

        itemManager = new ItemManager(handler);

    }

    private void loadWorld(String path){
        //loading the map file
        String file = Utils.loadFileAsString(mapPath);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        worldTiles = new int[width][height];
        worldEntities = new Entity[width][height];

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                worldTiles[x][y] = Utils.parseInt(tokens[x + y * width + 4]);
            }
        }

        //loading the entity file
        file = Utils.loadFileAsString(entityPath);
        String[] entities = file.split("\\s+");
        loadedEntities = new int[entities.length];
        for(int i = 0; i < entities.length; i++){
            loadedEntities[i] = Utils.parseInt(entities[i]);
        }


        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                worldEntities[x][y] = getEntityWithID(loadedEntities[x+y*width], x, y);
            }
        }

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                if(worldEntities[x][y] != null){
                    Entity tempEntity = worldEntities[x][y];
                    entityManager.addEntity(tempEntity);
                }
            }
        }

    }

    public Entity getEntityWithID(int id, int x, int y){
        if(id == 1) {
            return new AirWall(handler, x*Tile.TILEWIDTH, y*Tile.TILEHEIGHT);
        }else if(id == 2) {
            return new Tree(handler, x*Tile.TILEWIDTH, y*Tile.TILEHEIGHT);
        }else{
            return null;
        }
    }

    public Tile getTile(int x, int y){
        if(x < 0 || y < 0 || x >= width || y >= height){
            return Tile.dirtTile;
        }

        Tile t = Tile.tiles[worldTiles[x][y]];//get the tile type located at (x,y)
        if(t == null){
            return Tile.dirtTile;
        }
        return t;
    }

    public Entity getEntity(int x, int y){
        if(x < 0 || y < 0 || x >= width || y >= height){
            return null;
        }

        Entity e = worldEntities[x][y];//get the tile type located at (x,y)
        if(e == null){
            return null;
        }
        return e;
    }

    public void update(){

        xStart = (int) Math.max(0,handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        xEnd = (int) Math.min(width,(handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        yStart = (int) Math.max(0,handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        yEnd = (int) Math.min(height,(handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        for(int y = yStart; y < yEnd; y++){
            for(int x = xStart; x < xEnd; x++){
                getTile(x,y).update();
            }
        }

        entityManager.update();
        itemManager.update();

    }

    public void render(Graphics graphics){

        for(int y = yStart; y < yEnd; y++){
            for(int x = xStart; x < xEnd; x++){
                getTile(x,y).render(graphics,(int)(x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),(int)(y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }

        itemManager.render(graphics);
        entityManager.render(graphics);

    }

    //SDK stuff

    public void setTile(int tileX, int tileY){

        if(worldTiles[tileX][tileY] == sdkTileID){
            return;
        }
        worldTiles[tileX][tileY] = sdkTileID;

    }

    public void setLocationEntity(int entityX, int entityY){
        if(worldEntities[entityX][entityY] != null){
            if(worldEntities[entityX][entityY].getId() == sdkEntityID){
                return;
            }
        }
        removeLocationEntity(entityX, entityY);
        worldEntities[entityX][entityY] = getEntityWithID(sdkEntityID, entityX, entityY);
        entityManager.getEntities().add(worldEntities[entityX][entityY]);

    }

    public void removeLocationEntity(int entityX, int entityY){
        for(int i = 0; i < entityManager.getEntities().size(); i++){
            if((int)(entityManager.getEntities().get(i).getX() / Tile.TILEWIDTH) == entityX &&
                    (int)(entityManager.getEntities().get(i).getY() / Tile.TILEHEIGHT) == entityY)
            {
                if(entityManager.getEntities().get(i) != player){
                    entityManager.getEntities().get(i).setActive(false);
                }
            }
        }
        worldEntities[entityX][entityY] = null;
    }

    public void resetTile(int tileX, int tileY){

        String[] tokens = Utils.loadFileAsString(mapPath).split("\\s+");

        int[][] savedTiles = new int[width][height];

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                savedTiles[x][y] = Utils.parseInt(tokens[x + y * width + 4]);
            }
        }

        if(worldTiles[tileX][tileY] == savedTiles[tileX][tileY]){
            return;
        }
        worldTiles[tileX][tileY] = savedTiles[tileX][tileY];

    }

    public void resetLocationEntity(int entityX, int entityY){

        String[] tokens = Utils.loadFileAsString(entityPath).split("\\s+");
        for(int i = 0; i < tokens.length; i++){
            loadedEntities[i] = Utils.parseInt(tokens[i]);
        }
        removeLocationEntity(entityX, entityY);
        worldEntities[entityX][entityY] = getEntityWithID(loadedEntities[entityX+entityY*width], entityX, entityY);
        entityManager.getEntities().add(worldEntities[entityX][entityY]);

    }

    public void saveMap(){

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                if(worldEntities[x][y] == null){
                    loadedEntities[x+y*width] = 0;
                } else{
                    loadedEntities[x+y*width] = worldEntities[x][y].getId();
                }
            }
        }

        if(mapFile.exists()){
            mapFile.delete();
        }
        if(entityFile.exists()){
            entityFile.delete();
        }

        try {
            //map
            mapFile.createNewFile();
            PrintWriter mapEditor = new PrintWriter(mapFile);
            mapEditor.println(width+" "+height);
            mapEditor.println(spawnX+" "+spawnY);

            for(int y = 0; y < height; y++){
                for(int x = 0; x < width; x++){
                   mapEditor.print(worldTiles[x][y]+" ");
                }
                mapEditor.println();
            }
            mapEditor.close();

            //entity
            entityFile.createNewFile();

            PrintWriter entityEditor = new PrintWriter(entityFile);
            for(int y = 0; y < height; y++){
                for(int x = 0; x < width; x++){
                    entityEditor.print(loadedEntities[x+y*width]+" ");
                }
                entityEditor.println();
            }
            entityEditor.close();



        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void setSDKTile(int id){
        sdkTileID = id;
    }

    public void setSDKEntity(int id){
        sdkEntityID = id;
    }

    public void generateNewMap(int width, int height, int spawnX, int spawnY){

        if(mapFile.exists()){
            mapFile.delete();
        }
        if(entityFile.exists()){
            entityFile.delete();
        }

        for(int y = 0; y < this.height; y++){
            for(int x = 0; x < this.width; x++){
                if(worldEntities[x][y] != null){
                    entityManager.getEntities().remove(worldEntities[x][y]);
                }
            }
        }

        try {
            mapFile.createNewFile();
            PrintWriter mapEditor = new PrintWriter(mapFile);
            mapEditor.println(width+" "+height);
            mapEditor.println(spawnX+" "+spawnY);

            for(int y = 0; y < height; y++){
                for(int x = 0; x < width; x++){
                    mapEditor.print(sdkTileID+" ");
                }
                mapEditor.println();
            }
            mapEditor.close();

            //entity
            entityFile.createNewFile();

            PrintWriter entityEditor = new PrintWriter(entityFile);
            for(int y = 0; y < height; y++){
                for(int x = 0; x < width; x++){
                    entityEditor.print(0+" ");
                }
                entityEditor.println();
            }
            entityEditor.close();

        } catch (IOException e){
            e.printStackTrace();
        }
        loadWorld(folderPath);

        player.setX(spawnX*Tile.TILEWIDTH);
        player.setY(spawnY*Tile.TILEHEIGHT);
    }

    //

    //getters and setters
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSdkEntityID() {
        return sdkEntityID;
    }

    public int getSdkTileID() {
        return sdkTileID;
    }

    public Player getPlayer() {
        return player;
    }

    public void setEntityEditing(boolean entityEditing) {
        this.entityEditing = entityEditing;
    }

    public boolean isEntityEditing() {
        return entityEditing;
    }
}
