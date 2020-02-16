package dev.java.game.states;

import dev.java.game.Handler;
import java.awt.Graphics;


public abstract class State {

    private static State currentState = null;

    //States

    protected Handler handler;

    public State(Handler handler){
        this.handler = handler;
    }

    public abstract void update();

    public abstract void render(Graphics graphics);

    public abstract void init();

    //getters and setters

    public static void setState(State state){
        currentState = state;
    }

    public static State getCurrentState(){
        return currentState;
    }

}
