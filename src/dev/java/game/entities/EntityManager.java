package dev.java.game.entities;

import dev.java.game.Handler;
import dev.java.game.entities.creatures.Player;
import dev.java.game.tiles.Tile;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {

    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;

    //new: fix this
    private Comparator<Entity> renderComparator = new Comparator<>() {
        @Override
        public int compare(Entity a, Entity b) {
            if(a.isBackground() && !b.isBackground())
                return -1;
            if (!b.isBackground() && a.getY() + a.getHeight() < b.getY() + b.getHeight()) {
                return -1;
            }
            return 1;
        }
    };


    public EntityManager(Handler handler, Player player){
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        addEntity(player);
    }

    public void addEntity(Entity e){
        entities.add(e);
    }

    public void update(){
        Iterator<Entity> it = entities.iterator();

        while(it.hasNext()){
            Entity e = it.next();
            e.update();
            if(!e.isActive()){
                handler.getWorld().removeLocationEntity((int)(e.getX()),(int)(e.getY()), 0);
                it.remove();
            }
        }
        entities.sort(renderComparator);

    }

    public void render(Graphics graphics){

        for(int i = 0; i < entities.size(); i++){
            Entity e = entities.get(i);
            e.render(graphics);
        }

    }

    //getters and setters
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
