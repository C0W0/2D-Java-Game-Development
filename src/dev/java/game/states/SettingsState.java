package dev.java.game.states;

import dev.java.game.Handler;
import dev.java.game.gfx.Assets;
import dev.java.game.ui.functionUI.Slider;
import dev.java.game.ui.functionUI.UIImageButton;
import dev.java.game.ui.UIManager;
import dev.java.game.ui.clicker.ClickListener;
import dev.java.game.ui.clicker.StateSwitchingClicker;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class SettingsState extends State {

    private UIManager uiManager;
    private ScreenSize[] screenSizes = new ScreenSize[3];
    private ScreenSize[] fps = new ScreenSize[3];//using the ScreenSize object because less code
    private int width, height, gameFps;
    private ScreenSizeSlider screenSizeSlider, fpsSlider;
    private SettingSaveClicker settingSaveClicker;

    public SettingsState(Handler handler){
        super(handler);
        uiManager = handler.getUIManager();
        screenSizes[0] = new ScreenSize(800,600,"800*600");
        screenSizes[1] = new ScreenSize(1024,768,"1024*768");
        screenSizes[2] = new ScreenSize(1920,1080,"1920*1080");
        fps[0] = new ScreenSize(45,0,"45");
        fps[1] = new ScreenSize(60,0,"60");
        fps[2] = new ScreenSize(75,0,"75");
        settingSaveClicker = new SettingSaveClicker(width,height,gameFps);
    }

    @Override
    public void update() {
        uiManager.update();
        if(screenSizeSlider != null && fpsSlider != null){
            width = screenSizes[screenSizeSlider.getValue()].width;
            height = screenSizes[screenSizeSlider.getValue()].height;
            gameFps = fps[fpsSlider.getValue()].width;
        }
        settingSaveClicker.update(width,height,gameFps);
    }

    @Override
    public void render(Graphics graphics) {
        uiManager.render(graphics);
    }

    @Override
    public void init() {
        screenSizeSlider = new ScreenSizeSlider(true,
                handler.getWidth()/2 - (int)((float)512/1024*handler.getWidth()/2),(int)((float)128/768*handler.getHeight()),
                (int)((float)512/1024*handler.getWidth()), (int)((float)32/768*handler.getHeight()),
                2,0,1,"Screen Size:",screenSizes);
        fpsSlider = new ScreenSizeSlider(true,
                handler.getWidth()/2 - (int)((float)512/1024*handler.getWidth()/2),(int)((float)256/768*handler.getHeight()),
                (int)((float)512/1024*handler.getWidth()), (int)((float)32/768*handler.getHeight()),
                2,0,1,"FPS:",fps);
        uiManager.addUIObject(screenSizeSlider);
        uiManager.addUIObject(fpsSlider);
        uiManager.addUIObject(new UIImageButton(handler.getWidth()/2.f - (int)((float)512/1024*handler.getWidth()/2), (int)((float)600/768*handler.getHeight()),
                (int)((float)128/1024*handler.getWidth()), (int)((float)64/768*handler.getHeight()), Assets.button_save,
                settingSaveClicker));
        uiManager.addUIObject(new UIImageButton(handler.getWidth()/2.f + (int)((float)512/1024*handler.getWidth()/2) - (int)((float)128/1024*handler.getWidth()/2), (int)((float)600/768*handler.getHeight()),
                (int)((float)128/1024*handler.getWidth()), (int)((float)64/768*handler.getHeight()), Assets.button_back,
                new StateSwitchingClicker(handler, handler.getGame().menuState,4)));
    }

    private class ScreenSize{

        private int width;
        private int height;
        private String title;

        public ScreenSize(int width, int height, String title){
            this.width = width;
            this.height = height;
            this.title = title;
        }
    }

    private class SettingSaveClicker implements ClickListener {

        private File settingsFile = new File("res/settings/settings.set");
        private int width, height, fps;

        public SettingSaveClicker(int width, int height, int fps){
            this.width = width;
            this.height = height;
            this.fps = fps;
        }

        public void update(int width,int height, int fps){
            this.width = width;
            this.height = height;
            this.fps = fps;
        }

        @Override
        public void onClick() {
            if(settingsFile.exists()){
                settingsFile.delete();
            }

            try {
                settingsFile.createNewFile();
                PrintWriter printWriter = new PrintWriter(settingsFile);
                printWriter.println(width+" "+height+" "+fps);
                printWriter.close();

            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private class ScreenSizeSlider extends Slider{

        private ScreenSize[] screenSize;

        public ScreenSizeSlider(boolean horizontal, int x, int y, int width, int height, int max, int min, int tickSpacing, String name, ScreenSize[] screenSizes) {
            super(horizontal, x, y, width, height, max, min, tickSpacing, name);
            this.screenSize = screenSizes;
        }

        @Override
        public void update() {
            label = (name+screenSize[value].title);
        }
    }

}
