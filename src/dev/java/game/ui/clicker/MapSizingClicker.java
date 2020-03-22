package dev.java.game.ui.clicker;

import dev.java.game.Handler;
import dev.java.game.gfx.Assets;
import dev.java.game.ui.Slider;
import dev.java.game.ui.UIImageButton;

import java.util.function.IntSupplier;

public class MapSizingClicker implements ClickListener {

    private Handler handler;
    private UIImageButton mapSave;
    private IntSupplier widthSupplier, heightSupplier, spawnXSupplier, spawnYSupplier;

    public MapSizingClicker(Handler handler, IntSupplier widthSupplier, IntSupplier heightSupplier, IntSupplier spawnXSupplier, IntSupplier spawnYSupplier){
        mapSave = new UIImageButton(80,128,64,32, Assets.button_save, new MapSaveClicker());
        mapSave.setActive();
        handler.getMouseManager().getUiManager().addUIObject(mapSave);
        this.handler = handler;
        this.widthSupplier = widthSupplier;
        this.heightSupplier = heightSupplier;
        this.spawnXSupplier = spawnXSupplier;
        this.spawnYSupplier = spawnYSupplier;

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
            int width = 20, height = 12, spawnX = 2, spawnY = 2;
            spawnX = spawnXSupplier.getAsInt();
            spawnY = spawnYSupplier.getAsInt();
            width = widthSupplier.getAsInt();
            height = heightSupplier.getAsInt();
            handler.getWorld().generateNewMap(width,height,spawnX,spawnY);
        }
    }

}
