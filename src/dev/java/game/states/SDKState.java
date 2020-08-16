package dev.java.game.states;

import dev.java.game.Handler;
import dev.java.game.gfx.Assets;
import dev.java.game.ui.*;
import dev.java.game.tiles.Tile;
import dev.java.game.ui.clicker.EntityEditingClicker;
import dev.java.game.ui.clicker.MapSizingClicker;
import dev.java.game.ui.clicker.TileEditingClicker;
import dev.java.game.ui.functionUI.MapEditorButton;
import dev.java.game.ui.functionUI.Slider;
import dev.java.game.ui.functionUI.SliderAdjuster;
import dev.java.game.ui.functionUI.UIImageButton;
import dev.java.game.worlds.World;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class SDKState extends State {

    private World world;
    private UIManager uiManager;
    private boolean isHovering;
    private Slider heightSlider, widthSlider, entityOffsetXSlider, entityOffsetYSlider;

    public SDKState(Handler handler){
        super(handler);
        uiManager = handler.getUIManager();
    }

    private void leftClick(int x, int y){
        x = (int)(x+handler.getGameCamera().getxOffset());
        y = (int)(y+handler.getGameCamera().getyOffset());
        if(handler.getWorld().isEntityEditing()){
            x = x + entityOffsetXSlider.getValue();
            y = y + entityOffsetYSlider.getValue();
            handler.getWorld().setLocationEntity(x, y, 0);
        } else{
            x = x / Tile.TILEWIDTH;
            y = y / Tile.TILEHEIGHT;
            handler.getWorld().setTile(x, y);
        }
    }

    private void rightClick(int x, int y){
        x = (int)(x+handler.getGameCamera().getxOffset());
        y = (int)(y+handler.getGameCamera().getyOffset());
        if(handler.getWorld().isEntityEditing()){
            x = x + entityOffsetXSlider.getValue();
            y = y + entityOffsetYSlider.getValue();
            handler.getWorld().removeLocationEntity(x, y, 32);
        } else {
            x = x / Tile.TILEWIDTH;
            y = y / Tile.TILEHEIGHT;
            handler.getWorld().resetTile(x, y);
        }
    }

    @Override
    public void update() {
        if(world != null) {
            world.update();
        }

        uiManager.update();

        for(int i = 0; i < uiManager.getUiObjects().size(); i++){
            if(uiManager.getUiObjects().get(i).isHovering() && uiManager.getUiObjects().get(i).isActive()){
                isHovering = true;
            }
        }

        boolean isLeftPressed = handler.getMouseManager().isLeftPressed();
        if(isLeftPressed && !isHovering){
            leftClick(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY());
        }
        boolean isRightPressed = handler.getMouseManager().isRightPressed();
        if(isRightPressed && !isHovering){
            rightClick(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY());
        }

        if(handler.getKeyManager().ctrl){

            if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)){
                world.saveMap();
            }else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)){
                world.exportWorldToAndroid("res/worlds");
            }

        }
        isHovering = false;
    }

    @Override
    public void render(Graphics graphics) {
        if(world != null){
            world.render(graphics);
        }
        uiManager.render(graphics);
        if(world != null){
            world.getPlayer().postRender(graphics);
        }
    }

    @Override
    public void init() {
        SliderAdjuster heightUp, heightDown, widthUp, widthDown, spawnXUp, spawnXDown, spawnYUp, spawnYDown;
        Slider spawnXSlider, spawnYSlider;
        entityOffsetXSlider = new Slider(true, handler.getWidth()-256, 32, 128, 32, 32, -32, 8, "x-offset:");
        entityOffsetYSlider = new Slider(true, handler.getWidth()-256, 96, 128, 32, 32, -32, 8, "y-offset:");
        world = new World(handler,"res/worlds/worldSDK");
        handler.setWorld(world);
        heightSlider = new Slider(true,80,32,256,32,100,0,10,"height:");
        widthSlider = new Slider(true,80,96,256,32,100,0,10,"width:");
        spawnXSlider = new Slider(true,384,32,64,16,100,0,20,"spawn x:");
        spawnYSlider = new Slider(true,384,96,64,16,100,0,20,"spawn y:");
        heightUp = new SliderAdjuster(64,32,16,16,1,Assets.button_up,heightSlider);
        heightDown = new SliderAdjuster(64,48,16,16,-1,Assets.button_down,heightSlider);
        widthUp = new SliderAdjuster(64,96,16,16,1,Assets.button_up,widthSlider);
        widthDown = new SliderAdjuster(64,112,16,16,-1,Assets.button_down,widthSlider);
        spawnXUp = new SliderAdjuster(368,32,16,16,1,Assets.button_up,spawnXSlider);
        spawnXDown = new SliderAdjuster(368,48,16,16,-1,Assets.button_down,spawnXSlider);
        spawnYUp = new SliderAdjuster(368,96,16,16,1,Assets.button_up,spawnYSlider);
        spawnYDown = new SliderAdjuster(368,112,16,16,-1,Assets.button_down,spawnYSlider);
        uiManager.addUIObject(new MapEditorButton(handler,16,16,32,32,0, true));
        uiManager.addUIObject(new MapEditorButton(handler,16,48,32,32,4,false));
        for(int i = 0; i < 15; i++)
            uiManager.addUIObject(new MapEditorButton(handler, 16, 80+32*i, 32, 32, 50+i, false));
        for(int i = 0; i < 14; i++)
            uiManager.addUIObject(new MapEditorButton(handler, 48, 16+32*i, 32, 32, 65+i, false));
//        uiManager.addUIObject(new MapEditorButton(handler,16,80,32,32,50,false));
//        uiManager.addUIObject(new MapEditorButton(handler,16,112,32,32,51,false));
//        uiManager.addUIObject(new MapEditorButton(handler,16,144,32,32,52,false));
//        uiManager.addUIObject(new MapEditorButton(handler,16,176,32,32,53,false));
//        uiManager.addUIObject(new MapEditorButton(handler,16,208,32,32,54,false));
//        uiManager.addUIObject(new MapEditorButton(handler,16,240,32,32,55,false));
//        uiManager.addUIObject(new MapEditorButton(handler,16,272,32,32,56,false));
//        uiManager.addUIObject(new MapEditorButton(handler,16,304,32,32,57,false));
//        uiManager.addUIObject(new MapEditorButton(handler,handler.getWidth()-48, 16, 32, 32,Assets.tree_SDK,new EntityEditingClicker(handler, 2), false));
        uiManager.addUIObject(entityOffsetXSlider);
        uiManager.addUIObject(entityOffsetYSlider);
        uiManager.addUIObject(new SliderAdjuster(handler.getWidth()-256-16, 32, 16, 16, 1, Assets.button_up, entityOffsetXSlider));
        uiManager.addUIObject(new SliderAdjuster(handler.getWidth()-256-16, 48, 16, 16, -1, Assets.button_down, entityOffsetXSlider));
        uiManager.addUIObject(new SliderAdjuster(handler.getWidth()-256-16, 96, 16, 16, 1, Assets.button_up, entityOffsetYSlider));
        uiManager.addUIObject(new SliderAdjuster(handler.getWidth()-256-16, 112, 16, 16, -1, Assets.button_down, entityOffsetYSlider));

        uiManager.addUIObject(new UIImageButton(160,8,64,32,Assets.button_new,new MapSizingClicker(handler,
                () -> widthSlider.getValue(), () -> heightSlider.getValue(), () -> spawnXSlider.getValue(), () -> spawnYSlider.getValue())));
        uiManager.addUIObject(heightSlider);
        uiManager.addUIObject(widthSlider);
        uiManager.addUIObject(spawnXSlider);
        uiManager.addUIObject(spawnYSlider);
        uiManager.addUIObject(heightUp);
        uiManager.addUIObject(heightDown);
        uiManager.addUIObject(widthUp);
        uiManager.addUIObject(widthDown);
        uiManager.addUIObject(spawnXUp);
        uiManager.addUIObject(spawnXDown);
        uiManager.addUIObject(spawnYUp);
        uiManager.addUIObject(spawnYDown);
        heightSlider.setActive();
        widthSlider.setActive();
        spawnXSlider.setActive();
        spawnYSlider.setActive();
        heightUp.setActive();
        heightDown.setActive();
        widthUp.setActive();
        widthDown.setActive();
        spawnXUp.setActive();
        spawnXDown.setActive();
        spawnYUp.setActive();
        spawnYDown.setActive();
    }

    //getter and setters
    public Slider getHeightSlider() {
        return heightSlider;
    }

    public Slider getWidthSlider() {
        return widthSlider;
    }
}
