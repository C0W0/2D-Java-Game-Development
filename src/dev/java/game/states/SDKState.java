package dev.java.game.states;

import dev.java.game.Handler;
import dev.java.game.gfx.Assets;
import dev.java.game.ui.*;
import dev.java.game.tiles.Tile;
import dev.java.game.ui.clicker.EntityEditingClicker;
import dev.java.game.ui.clicker.MapSizingClicker;
import dev.java.game.ui.clicker.TileEditingClicker;
import dev.java.game.worlds.World;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class SDKState extends State {

    private World world;
    private UIManager uiManager;
    private boolean isHovering;
    private Slider heightSlider, widthSlider;

    public SDKState(Handler handler){
        super(handler);
        uiManager = handler.getUIManager();
    }

    private void leftClick(int x, int y){
//        x = (int)((x+handler.getGameCamera().getxOffset()) / Tile.TILEWIDTH);
//        y = (int)((y+handler.getGameCamera().getyOffset()) / Tile.TILEHEIGHT);
        if(handler.getWorld().isEntityEditing()){
            handler.getWorld().setLocationEntity(x, y, 0);
        } else{
            handler.getWorld().setTile(x, y);
        }
    }

    private void rightClick(int x, int y){
        x = (int)((x+handler.getGameCamera().getxOffset()) / Tile.TILEWIDTH);
        y = (int)((y+handler.getGameCamera().getyOffset()) / Tile.TILEHEIGHT);
        if(handler.getWorld().isEntityEditing()){
            handler.getWorld().removeLocationEntity(x, y);
        } else {
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
            }

        }
        isHovering = false;
    }

    @Override
    public void render(Graphics graphics) {
        if(world!= null){
            world.render(graphics);
        }
        uiManager.render(graphics);
    }

    @Override
    public void init() {
        SliderAdjuster heightUp, heightDown, widthUp, widthDown, spawnXUp, spawnXDown, spawnYUp, spawnYDown;
        Slider spawnXSlider, spawnYSlider, entityOffsetXSlider, entityOffsetYSlider;
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
        uiManager.addUIObject(new MapEditorButton(16,16,32,32,Assets.grass_SDK,new TileEditingClicker(handler, 0),true));
        uiManager.addUIObject(new MapEditorButton(16,48,32,32,Assets.grassStone_SDK,new TileEditingClicker(handler, 1),false));
        uiManager.addUIObject(new MapEditorButton(16,80,32,32,Assets.dirt_SDK,new TileEditingClicker(handler, 2),false));
        uiManager.addUIObject(new MapEditorButton(16,112,32,32,Assets.dirtStone_SDK,new TileEditingClicker(handler, 3),false));
        uiManager.addUIObject(new MapEditorButton(16,144,32,32,Assets.pathH_SDK,new TileEditingClicker(handler, 4),false));
        uiManager.addUIObject(new MapEditorButton(16,176,32,32,Assets.pathV_SDK,new TileEditingClicker(handler, 5),false));
        uiManager.addUIObject(new MapEditorButton(16,208,32,32,Assets.pathUpRight_SDK,new TileEditingClicker(handler, 6),false));
        uiManager.addUIObject(new MapEditorButton(16,240,32,32,Assets.pathUpLeft_SDK,new TileEditingClicker(handler, 7),false));
        uiManager.addUIObject(new MapEditorButton(16,272,32,32,Assets.pathDownRight_SDK,new TileEditingClicker(handler, 8),false));
        uiManager.addUIObject(new MapEditorButton(16,304,32,32,Assets.pathDownLeft_SDK,new TileEditingClicker(handler, 9),false));
        uiManager.addUIObject(new MapEditorButton(handler.getWidth()-48, 16, 32, 32,Assets.tree_SDK,new EntityEditingClicker(handler, 2), false));
        uiManager.addUIObject(entityOffsetXSlider);
        uiManager.addUIObject(entityOffsetYSlider);
        uiManager.addUIObject(new SliderAdjuster(handler.getWidth()-256-16, 32, 16, 16, 1, Assets.button_up, entityOffsetXSlider));
        uiManager.addUIObject(new SliderAdjuster(handler.getWidth()-256-16, 48, 16, 16, -1, Assets.button_down, entityOffsetXSlider));
        uiManager.addUIObject(new SliderAdjuster(handler.getWidth()-256-16, 96, 16, 16, 1, Assets.button_up, entityOffsetYSlider));
        uiManager.addUIObject(new SliderAdjuster(handler.getWidth()-256-16, 112, 16, 16, -1, Assets.button_down, entityOffsetYSlider));

        uiManager.addUIObject(new UIImageButton(80,8,64,32,Assets.button_new,new MapSizingClicker(handler,
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
