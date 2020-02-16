package dev.java.game.ui.clicker;

import dev.java.game.Handler;
import dev.java.game.gfx.Assets;
import dev.java.game.ui.Slider;
import dev.java.game.ui.UIImageButton;

public class MapSizingClicker implements ClickListener {

    private Handler handler;
    private UIImageButton mapSave;
    private int width, height, spawnX, spawnY;
    private Slider widthSlider, heightSlider, spawnXSlider, spawnYSlider;

    public MapSizingClicker(Handler handler, Slider widthSlider, Slider heightSlider, Slider spawnXSlider, Slider spawnYSlider){
        mapSave = new UIImageButton(80,128,64,32, Assets.button_save, new MapSaveClicker());
        mapSave.setActive();
        handler.getMouseManager().getUiManager().addUIObject(mapSave);
        this.handler = handler;
        this.widthSlider = widthSlider;
        this.heightSlider = heightSlider;
        this.spawnXSlider = spawnXSlider;
        this.spawnYSlider = spawnYSlider;
        width = 20;
        height = 12;
        spawnX = 2;
        spawnY = 2;

    }

    @Override
    public void onClick() {
        handler.getMouseManager().getUiManager().getUiObjects().get(handler.getMouseManager().getUiManager().getUiObjects().size()-1).setActive();
        handler.getMouseManager().getUiManager().getUiObjects().get(handler.getMouseManager().getUiManager().getUiObjects().size()-2).setActive();
        handler.getMouseManager().getUiManager().getUiObjects().get(handler.getMouseManager().getUiManager().getUiObjects().size()-3).setActive();
        handler.getMouseManager().getUiManager().getUiObjects().get(handler.getMouseManager().getUiManager().getUiObjects().size()-4).setActive();
        handler.getMouseManager().getUiManager().getUiObjects().get(handler.getMouseManager().getUiManager().getUiObjects().size()-5).setActive();
        handler.getMouseManager().getUiManager().getUiObjects().get(handler.getMouseManager().getUiManager().getUiObjects().size()-6).setActive();
        handler.getMouseManager().getUiManager().getUiObjects().get(handler.getMouseManager().getUiManager().getUiObjects().size()-7).setActive();
        handler.getMouseManager().getUiManager().getUiObjects().get(handler.getMouseManager().getUiManager().getUiObjects().size()-8).setActive();
        handler.getMouseManager().getUiManager().getUiObjects().get(handler.getMouseManager().getUiManager().getUiObjects().size()-9).setActive();
        handler.getMouseManager().getUiManager().getUiObjects().get(handler.getMouseManager().getUiManager().getUiObjects().size()-10).setActive();
        handler.getMouseManager().getUiManager().getUiObjects().get(handler.getMouseManager().getUiManager().getUiObjects().size()-11).setActive();
        handler.getMouseManager().getUiManager().getUiObjects().get(handler.getMouseManager().getUiManager().getUiObjects().size()-12).setActive();
        handler.getMouseManager().getUiManager().getUiObjects().get(handler.getMouseManager().getUiManager().getUiObjects().size()-14).setActive();
    }

    private class MapSaveClicker implements ClickListener{
        @Override
        public void onClick() {
            if(spawnXSlider != null && spawnYSlider != null) {
                spawnX = spawnXSlider.getValue();
                spawnY = spawnYSlider.getValue();
            }
            if(widthSlider != null && heightSlider != null){
                width = widthSlider.getValue();
                height = heightSlider.getValue();
            }
            handler.getWorld().generateNewMap(width,height,spawnX,spawnY);
        }
    }

}
