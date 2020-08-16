package dev.java.game.ui.functionUI;

import dev.java.game.Handler;
import dev.java.game.gfx.Assets;
import dev.java.game.tiles.Tile;
import dev.java.game.ui.UIObject;
import dev.java.game.ui.clicker.ClickListener;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MapEditorButton extends UIObject {

    private BufferedImage image;
    private int tileID;
    private Handler handler;

    public MapEditorButton(Handler handler, float x, float y, int width, int height, int tileID, boolean init) {
        super(x, y, width, height);
        this.handler = handler;
        this.tileID = tileID;
        selected = init;
        image = Tile.tiles[tileID].getTexture();
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {

        if(selected){
            graphics.drawImage(Assets.blueSqr, (int)x-2, (int)y-2, width+4, height+4, null);
            graphics.drawImage(image, (int)x, (int)y, width, height, null);
        } else{
            graphics.drawImage(Assets.redSqr, (int)x-2, (int)y-2, width+4, height+4, null);
            graphics.drawImage(image, (int)x, (int)y, width, height, null);
        }

    }

    @Override
    public void onClick() {
        for(int i = 0; i < handler.getMouseManager().getUiManager().getUiObjects().size(); i++){
            handler.getMouseManager().getUiManager().getUiObjects().get(i).setSelected(false);
        }
        handler.getWorld().setSDKTile(tileID);
        handler.getWorld().setEntityEditing(false);
        selected = true;
    }
}
