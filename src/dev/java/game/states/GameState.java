package dev.java.game.states;

import dev.java.game.Handler;
import dev.java.game.worlds.World;

import java.awt.Graphics;

public class GameState extends State {

    private World world;

    public GameState(Handler handler){
        super(handler);
    }

    @Override
    public void update() {
        if(world != null)
            world.update();
    }

    @Override
    public void render(Graphics graphics) {
        if(world != null){
            world.render(graphics);
        }
    }

    @Override
    public void init() {
        world = new World(handler,"res/worlds/world1");
        handler.setWorld(world);
    }
}
