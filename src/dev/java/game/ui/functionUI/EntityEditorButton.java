package dev.java.game.ui.functionUI;

import dev.java.game.Handler;
import dev.java.game.entities.Entity;
import dev.java.game.gfx.Assets;
import dev.java.game.tiles.Tile;
import dev.java.game.ui.UIObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EntityEditorButton extends UIObject {

    private BufferedImage image;
    private int entityID;
    private Handler handler;

    public EntityEditorButton(Handler handler, float x, float y, int width, int height, int entityID, BufferedImage image) {
        super(x, y, width, height);
        this.handler = handler;
        this.entityID = entityID;
        this.image = image;
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
        handler.getWorld().setSDKEntity(entityID);
        handler.getWorld().setEntityEditing(true);
        selected = true;
    }
}
