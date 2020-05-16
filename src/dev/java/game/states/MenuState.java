package dev.java.game.states;

import dev.java.game.Handler;
import dev.java.game.gfx.Assets;
import dev.java.game.ui.clicker.StateSwitchingClicker;
import dev.java.game.ui.functionUI.UIImageButton;
import dev.java.game.ui.UIManager;

import java.awt.Graphics;

public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(Handler handler){
        super(handler);
        uiManager = handler.getUIManager();
        init();
    }

    @Override
    public void update(){
        uiManager.update();
    }

    @Override
    public void render(Graphics graphics) {
        uiManager.render(graphics);
    }

    @Override
    public void init() {
        uiManager.addUIObject(new UIImageButton((int)((float)800/1024*handler.getWidth()), (int)((float)400/768*handler.getHeight()),
                (int)((float)128/1024*handler.getWidth()), (int)((float)64/768*handler.getHeight()), Assets.button_SDK,
                new StateSwitchingClicker(handler, handler.getGame().sdkState,3)));
        uiManager.addUIObject(new UIImageButton((int)((float)800/1024*handler.getWidth()), (int)((float)500/768*handler.getHeight()),
                (int)((float)128/1024*handler.getWidth()), (int)((float)64/768*handler.getHeight()), Assets.button_start,
                new StateSwitchingClicker(handler, handler.getGame().gameState,3)));
        uiManager.addUIObject(new UIImageButton((int)((float)800/1024*handler.getWidth()), (int)((float)600/768*handler.getHeight()),
                (int)((float)128/1024*handler.getWidth()), (int)((float)64/768*handler.getHeight()), Assets.button_settings,
                new StateSwitchingClicker(handler, handler.getGame().settingsState,3)));
    }
}
