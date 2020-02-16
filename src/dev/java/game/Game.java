package dev.java.game;

import dev.java.game.display.Display;
import dev.java.game.gfx.Assets;
import dev.java.game.display.GameCamera;
import dev.java.game.input.KeyManager;
import dev.java.game.input.MouseManager;
import dev.java.game.states.*;
import dev.java.game.ui.UIManager;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{

    private Display display;
    private FPSTimer timer;
    private Thread thread;

    private BufferStrategy bufferStrategy;
    private Graphics graphics;
    private int fps;

    //input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //camera
    private GameCamera gameCamera;

    //handler
    private Handler handler;

    //states
    public State gameState;
    public State menuState;
    public State settingsState;

    //SDK stuff
    public State sdkState;
    //

    //ui
    private UIManager uiManager;

    private int width, height;
    public String title;


    private boolean running = false;


    public Game(String title,int width,int height,int fps){
        this.width = width;
        this.height = height;
        this.title = title;
        this.fps = fps;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    private void init(){
        display = new Display(title,width,height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        timer = new FPSTimer(fps);
        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler,0,0);
        uiManager = new UIManager(handler);
        mouseManager.setUiManager(uiManager);

        settingsState = new SettingsState(handler);
        gameState = new GameState(handler);
        //SDK stuff
        sdkState = new SDKState(handler);
        //
        menuState = new MenuState(handler);

        State.setState(menuState);

    }

    private void update(){

        keyManager.update();

        if(State.getCurrentState() != null){
            State.getCurrentState().update();
        }
    }

    private void render(){
        bufferStrategy = display.getCanvas().getBufferStrategy();
        if(bufferStrategy == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        graphics = bufferStrategy.getDrawGraphics();
        graphics.clearRect(0,0,width,height); //clear the screen
        //Draw Below

        if(State.getCurrentState() != null){
            State.getCurrentState().render(graphics);
        }

        //End Drawing
        bufferStrategy.show();
        graphics.dispose();
    }

    public void run(){

        init();

        while(running){

            if (timer.check()){
                update();
                render();
            }

        }
        stop();
    }


    public synchronized void start(){
        if(running){
            return;
        }

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running){
            return;
        }

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //getters and setters
    public KeyManager getKeyManager(){
        return keyManager;
    }

    public MouseManager getMouseManager(){
        return mouseManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public UIManager getUiManager() {
        return uiManager;
    }

    public Display getDisplay() {
        return display;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
