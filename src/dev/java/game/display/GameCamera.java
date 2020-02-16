package dev.java.game.display;

import dev.java.game.Handler;
import dev.java.game.entities.Entity;
import dev.java.game.tiles.Tile;

public class GameCamera {

    private float xOffset, yOffset;
    private Handler handler;

    public GameCamera(Handler handler, float xOffset, float yOffset){
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void hideBlankSpace(){

        if(xOffset < 0){
            xOffset = 0;
        } else if(xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()){
            xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
        }
        if(yOffset < 0){
            yOffset = 0;
        } else if(yOffset > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()){
            yOffset = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
        }
    }

    public void centerOnEntity(Entity e){
        xOffset = e.getX() - (float)handler.getWidth()/2 + (float)e.getWidth() /2;
        yOffset = e.getY() - (float)handler.getHeight()/2 + (float)e.getHeight() /2;
        hideBlankSpace();
    }

    public void move(float xAmt, float yAmt){
        xOffset += xAmt;
        yOffset += yAmt;
        hideBlankSpace();
    }

    //getters and setters
    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
}
