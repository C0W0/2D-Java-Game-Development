package dev.java.game.worlds;

import dev.java.game.Handler;
import dev.java.game.entities.Entity;
import dev.java.game.entities.EntityManager;
import dev.java.game.entities.creatures.Player;
import dev.java.game.items.ItemManager;
import dev.java.game.tiles.Tile;
import dev.java.game.utils.Utils;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
    private ArrayList<String> loadedEntities = new ArrayList<>();

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
        String[] tokens = Utils.loadFileAsString(mapPath).split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        worldTiles = new int[width][height];

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                worldTiles[x][y] = Utils.parseInt(tokens[x + y * width + 4]);
            }
        }

        //loading the entity file
        loadedEntities = Utils.loadFileAsArrayList(entityPath);
        for(int i = 0; i < loadedEntities.size(); i++){
            String[] entities = loadedEntities.get(i).split("\\s+");
            entityManager.addEntity(getEntityWithID(Utils.parseInt(entities[0]), // id
                    Utils.parseInt(entities[1]), Utils.parseInt(entities[2]), // initial x and y
                    Utils.parseInt(entities[3]), Utils.parseInt(entities[4]), // offset x and y
                    Utils.parseInt(entities[5]))); // status
        }



    }

    /**
     *
     * @param id the id of the entity
     * @param x the initial x position of the entity
     * @param y the initial y position of the entity
     * @param ox the current x position (offset x) of the entity, pass in 0 if not applicable
     * @param oy the current y position (offset y) of the entity, pass in 0 if not applicable
     * @param status the status of the entity, pass in 0 if not applicable
     */
    private Entity getEntityWithID(int id, int x, int y, int ox, int oy, int status){
        Entity e = Entity.entityList[id].clone();
        e.initialize(handler, x, y, ox, oy);
        return e;
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
        if(x < 0 || y < 0 || x >= width*64 || y >= height*64){
            return null;
        }

        for(int i = 0; i < entityManager.getEntities().size(); i++){
            if(entityManager.getEntities().get(i).getX() == x &&
            entityManager.getEntities().get(i).getY() == y){
                return entityManager.getEntities().get(i);
            }
        }
        return null;
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
                getTile(x,y).render(graphics,(int)(x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int)(y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
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

    /**
     *
     * @param status pass in 0 if not applicable
     */
    public void setLocationEntity(int entityX, int entityY, int status){
        if(getEntity(entityX, entityY) != null && getEntity(entityX, entityY).getId() == sdkEntityID)
            return;
        removeLocationEntity(entityX, entityY, 0);
        String e = sdkEntityID +" "+entityX+" "+entityY+" "+entityX+" "+entityY+" "+status;
        loadedEntities.add(e);
        entityManager.getEntities().add(getEntityWithID(sdkEntityID, entityX, entityY, 0, 0, status));
    }

    public void removeLocationEntity(int entityX, int entityY, int precision){
        for(int i = 0; i < entityManager.getEntities().size(); i++){
            if( ((int)(entityManager.getEntities().get(i).getX()) >= entityX - precision &&
                    (int)(entityManager.getEntities().get(i).getX()) <= entityX + precision )
                    &&
                    ((int)(entityManager.getEntities().get(i).getY()) >= entityY - precision &&
                            (int)(entityManager.getEntities().get(i).getY()) <= entityY + precision ))
            {
                if (entityManager.getEntities().get(i) != player) {
                    entityManager.getEntities().get(i).setActive(false);
                }
            }
        }
        int k = 0;
        for(int i = 0; i < loadedEntities.size() - k; i++){
            if( (Utils.parseInt(loadedEntities.get(i).split("\\s+")[1]) >= entityX - precision &&
                    (Utils.parseInt(loadedEntities.get(i).split("\\s+")[1])) <= entityX + precision )
                    &&
                    (Utils.parseInt(loadedEntities.get(i).split("\\s+")[2])) >= entityY - precision &&
                            (Utils.parseInt(loadedEntities.get(i).split("\\s+")[2])) <= entityY + precision )
            {
                loadedEntities.remove(i);
                i --;
                k ++;
            }
        }
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
        removeLocationEntity(entityX, entityY, 32);
        ArrayList<String> originalEntity;
        originalEntity = Utils.loadFileAsArrayList(entityPath);
        for(int i = 0; i < originalEntity.size(); i++){
            if((Utils.parseInt(originalEntity.get(i).split("\\s+")[1]) == entityX) &&
                    (Utils.parseInt(originalEntity.get(i).split("\\s+")[2]) == entityY))
            {
                loadedEntities.add(originalEntity.get(i));
                String[] temp = originalEntity.get(i).split("\\s+");
                entityManager.getEntities().add(getEntityWithID(Utils.parseInt(temp[0]),
                        Utils.parseInt(temp[1]), Utils.parseInt(temp[2]),
                        Utils.parseInt(temp[3]), Utils.parseInt(temp[4]),
                        Utils.parseInt(temp[5])));
            }
        }

    }

    public void saveMap(){

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
            mapEditor.println((int)player.getX()/64+" "+(int)player.getX()/64);

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
            for(int i = 0; i < loadedEntities.size(); i++){
                entityEditor.println(loadedEntities.get(i));
            }
            entityEditor.close();



        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void exportWorldToAndroid(String path){
        File androidMap = new File(path+"/export/tiles.wld");
        File androidEntity = new File(path+"/export/entity.wld");
        androidMap.delete();
        androidEntity.delete();
        try {
            androidMap.createNewFile();
            PrintWriter fileEditor = new PrintWriter(androidMap);
            fileEditor.println(width+" "+height);
            for(int y = 0; y < height; y++){
                for(int x = 0; x < width; x++){
                    fileEditor.print(worldTiles[x][y]+" ");
                }
                fileEditor.println();
            }
            fileEditor.close();

            androidEntity.createNewFile();
            fileEditor = new PrintWriter(androidEntity);
            for(String line: loadedEntities){
                String[] tokens = line.split("\\s+");
                fileEditor.print(tokens[0]+" ");
                for(int i = 1; i < tokens.length-1; i++){
                    int value = Utils.parseInt(tokens[i])*2;
                    fileEditor.print(value+" ");
                }
                fileEditor.println(tokens[tokens.length-1]);
            }
            fileEditor.close();
        }catch (IOException e){
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

        entityManager.getEntities().clear();

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
