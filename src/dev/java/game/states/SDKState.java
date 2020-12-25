package dev.java.game.states;

import dev.java.game.Handler;
import dev.java.game.entities.Entity;
import dev.java.game.gfx.Assets;
import dev.java.game.ui.*;
import dev.java.game.tiles.Tile;
import dev.java.game.ui.clicker.MapSizingClicker;
import dev.java.game.ui.functionUI.*;
import dev.java.game.worlds.World;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SDKState extends State {

    private World world;
    private UIManager uiManager;
    private boolean isHovering;
    private Switch isBlockOriented;
    private Slider heightSlider, widthSlider, entityOffsetXSlider, entityOffsetYSlider;
    private MapEditorButton[][] buttonSets;
    private int listIndex;

    public SDKState(Handler handler){
        super(handler);
        uiManager = handler.getUIManager();
        listIndex = 1;
    }

    private void leftClick(int x, int y){
        x = (int)(x+handler.getGameCamera().getxOffset());
        y = (int)(y+handler.getGameCamera().getyOffset());
        if(handler.getWorld().isEntityEditing()){
            if(isBlockOriented.isOn()){
                x -= x%64;
                y -= y%64;
            }
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
                System.out.println("Map Saved");
                world.saveMap();
            }else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)){
                System.out.println("Map Exported");
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
        isBlockOriented = new Switch(handler.getWidth()-256, 128, 128, 64);
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
//        uiManager.addUIObject(new MapEditorButton(handler,16,16,32,32,0, true));
//        uiManager.addUIObject(new MapEditorButton(handler,16,48,32,32,4,false));
//        for(int i = 0; i < 20; i++)
//            uiManager.addUIObject(new MapEditorButton(handler, 16, 80+32*i, 32, 32, 50+i, false));
//        for(int i = 0; i < 20; i++)
//            uiManager.addUIObject(new MapEditorButton(handler, 48, 16+32*i, 32, 32, 70+i, false));
        ArrayList<MapEditorButton> buttons = new ArrayList<>();
        buttonSets = new MapEditorButton[10][];
        buttons.add(new MapEditorButton(handler,16,16,32,32,0, true));
        for(int i = 1; i < 8; i++)
            buttons.add(new MapEditorButton(handler, 80-64, 16+32*i, 32, 32, i+3, false));
        for(int i = 8; i < 20; i++)
            buttons.add(new MapEditorButton(handler, 80-64, 16+32*i, 32, 32, 42+i, false));
        for(int i = 0; i < 17; i++)
            buttons.add(new MapEditorButton(handler, 112-64, 16+32*i, 32, 32, 62+i, false));
        buttonSets[0] = new MapEditorButton[buttons.size()];
        for(int i = 0; i < buttons.size(); i++){
            MapEditorButton b = buttons.get(i);
            b.setActive();
            buttonSets[0][i] = b;
        }
        uiManager.addUIObject(buttonSets[0]);
        buttons.clear();

        for(int i = 0; i < 20; i++)
            buttons.add(new MapEditorButton(handler, 80-64, 16+32*i, 32, 32, 91+i, false));
        for(int i = 0; i < 20; i++)
            buttons.add(new MapEditorButton(handler, 112-64, 16+32*i, 32, 32, 91+20+i, false));
        for(int i = 0; i < 2; i++)
            buttons.add(new MapEditorButton(handler, 80, 16+32*i, 32, 32, 91+40+i, false));
        buttonSets[1] = new MapEditorButton[buttons.size()];
        for(int i = 0; i < buttons.size(); i++){
            MapEditorButton b = buttons.get(i);
            buttonSets[1][i] = b;
        }
        buttons.clear();

        buttons.add(new MapEditorButton(handler,16,16,32,32,169, true));
        for(int i = 1; i < 8; i++)
            buttons.add(new MapEditorButton(handler, 80-64, 16+32*i, 32, 32, i+161, false));
        for(int i = 8; i < 20; i++)
            buttons.add(new MapEditorButton(handler, 80-64, 16+32*i, 32, 32, 125+i, false));
        for(int i = 0; i < 17; i++)
            buttons.add(new MapEditorButton(handler, 112-64, 16+32*i, 32, 32, 145+i, false));

        buttonSets[2] = new MapEditorButton[buttons.size()];
        for(int i = 0; i < buttons.size(); i++){
            MapEditorButton b = buttons.get(i);
            b.setActive();
            buttonSets[2][i] = b;
        }
        uiManager.addUIObject(buttonSets[2]);
        buttons.clear();

        uiManager.addUIObject(buttonSets[1]);

        uiManager.addUIObject(new UIImageButton(112, 16, 32, 32, Assets.button_SDK, world::toggleAbstract));
        uiManager.addUIObject(new UIImageButton(112, 48, 32, 32, Assets.button_new, world::toggleIslandShape));
        uiManager.addUIObject(new UIImageButton(112, 80, 32, 32, Assets.button_SDK, this::toggleTiles));
//        uiManager.addUIObject(new MapEditorButton(handler, 48, 16+32*14, 32, 32, Tile.verticalPath.getId(), false));
//        uiManager.addUIObject(new MapEditorButton(handler, 48, 16+32*15, 32, 32, Tile.pathCross.getId(), false));
//        uiManager.addUIObject(new MapEditorButton(handler,16,80,32,32,50,false));
//        uiManager.addUIObject(new MapEditorButton(handler,16,112,32,32,51,false));
//        uiManager.addUIObject(new MapEditorButton(handler,16,144,32,32,52,false));
//        uiManager.addUIObject(new MapEditorButton(handler,16,176,32,32,53,false));
//        uiManager.addUIObject(new MapEditorButton(handler,16,208,32,32,54,false));
//        uiManager.addUIObject(new MapEditorButton(handler,16,240,32,32,55,false));
//        uiManager.addUIObject(new MapEditorButton(handler,16,272,32,32,56,false));
//        uiManager.addUIObject(new MapEditorButton(handler,16,304,32,32,57,false));
        uiManager.addUIObject(new EntityEditorButton(handler,handler.getWidth()-48, 16, 32, 32, Entity.tree.getId(), Assets.tree));
        uiManager.addUIObject(new EntityEditorButton(handler, handler.getWidth()-48, 48, 32, 32, Entity.staticCrab.getId(), Assets.npcCrab[0]));
        uiManager.addUIObject(new EntityEditorButton(handler, handler.getWidth()-48, 80, 32, 32, Entity.slimeSpawner.getId(), Assets.npcSlime[0]));
        uiManager.addUIObject(new EntityEditorButton(handler, handler.getWidth()-48, 112, 32, 32, Entity.slime.getId(), Assets.slimeMovementLeft[0]));

        uiManager.addUIObject(entityOffsetXSlider);
        uiManager.addUIObject(entityOffsetYSlider);
        uiManager.addUIObject(isBlockOriented);
        uiManager.addUIObject(new SliderAdjuster(handler.getWidth()-256-16, 32, 16, 16, 1, Assets.button_up, entityOffsetXSlider));
        uiManager.addUIObject(new SliderAdjuster(handler.getWidth()-256-16, 48, 16, 16, -1, Assets.button_down, entityOffsetXSlider));
        uiManager.addUIObject(new SliderAdjuster(handler.getWidth()-256-16, 96, 16, 16, 1, Assets.button_up, entityOffsetYSlider));
        uiManager.addUIObject(new SliderAdjuster(handler.getWidth()-256-16, 112, 16, 16, -1, Assets.button_down, entityOffsetYSlider));

        uiManager.addUIObject(new UIImageButton(160,8,64,32,Assets.button_new,new MapSizingClicker(handler,
                () -> widthSlider.getValue(), () -> heightSlider.getValue(), spawnXSlider::getValue, spawnYSlider::getValue)));
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

    private void toggleTiles(){
        listIndex = listIndex < 2? listIndex+1: 0;
        System.out.println(listIndex);
        for(int y = 0; y < buttonSets.length; y++){
            if(buttonSets[y] == null)
                break;
            for(int x = 0; x < buttonSets[y].length; x++){
                buttonSets[y][x].setActive(false);
            }
        }
        for(int x = 0; x < buttonSets[listIndex].length; x++){
            buttonSets[listIndex][x].setActive(true);
        }
    }

    //getter and setters
    public Slider getHeightSlider() {
        return heightSlider;
    }

    public Slider getWidthSlider() {
        return widthSlider;
    }
}
