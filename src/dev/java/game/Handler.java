package dev.java.game;

import dev.java.game.display.Display;
import dev.java.game.display.GameCamera;
import dev.java.game.input.KeyManager;
import dev.java.game.input.MouseManager;
import dev.java.game.states.State;
import dev.java.game.ui.UIManager;
import dev.java.game.worlds.World;

public class Handler {

    private Game game;
    private World world;

    public Handler(Game game){
        this.game = game;
    }

    public GameCamera getGameCamera(){
        return game.getGameCamera();
    }

    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }

    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }

    public UIManager getUIManager(){
        return game.getUiManager();
    }

    public int getWidth(){
        return game.getWidth();
    }

    public int getHeight(){
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Display getDisplay(){
        return game.getDisplay();
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    //SDK stuff
    public State getSDKState(){
        return game.sdkState;
    }
}
