package dev.java.game.ui.clicker;

import dev.java.game.Handler;
import dev.java.game.states.State;

public class StateSwitchingClicker implements ClickListener {

    private Handler handler;
    private State state;
    private int objects;

    public StateSwitchingClicker(Handler handler, State state, int objects){
        this.handler = handler;
        this.state = state;
        this.objects = objects;
    }

    @Override
    public void onClick() {
        State.setState(state);
        state.init();
        for(int i = 0; i < objects; i++){
            handler.getMouseManager().getUiManager().removeUIObject(handler.getMouseManager().getUiManager().getUiObjects().get(0));
        }
    }
}
