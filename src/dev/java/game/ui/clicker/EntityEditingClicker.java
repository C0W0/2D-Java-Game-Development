package dev.java.game.ui.clicker;

import dev.java.game.Handler;

public class EntityEditingClicker implements ClickListener  {

    private Handler handler;
    private int mapEntityID;

    public EntityEditingClicker(Handler handler, int mapEntityID){
        this.handler = handler;
        this.mapEntityID = mapEntityID;
    }
    @Override
    public void onClick() {
        for(int i = 0; i < handler.getMouseManager().getUiManager().getUiObjects().size(); i++){
            handler.getMouseManager().getUiManager().getUiObjects().get(i).setSelected(false);
        }
        handler.getWorld().setSDKEntity(mapEntityID);
        handler.getWorld().setEntityEditing(true);
    }
}
