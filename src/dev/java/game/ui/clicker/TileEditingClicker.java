package dev.java.game.ui.clicker;

import dev.java.game.Handler;

public class TileEditingClicker implements ClickListener {

    private Handler handler;
    private int mapObjectID;

    public TileEditingClicker(Handler handler, int mapObjectID){
        this.handler = handler;
        this.mapObjectID = mapObjectID;
    }

    @Override
    public void onClick() {
        for(int i = 0; i < handler.getMouseManager().getUiManager().getUiObjects().size(); i++){
            handler.getMouseManager().getUiManager().getUiObjects().get(i).setSelected(false);
        }
        handler.getWorld().setSDKTile(mapObjectID);
        handler.getWorld().setEntityEditing(false);
    }
}
