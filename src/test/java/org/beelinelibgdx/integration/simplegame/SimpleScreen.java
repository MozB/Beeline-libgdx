package org.beelinelibgdx.integration.simplegame;

import com.badlogic.gdx.utils.viewport.Viewport;

import org.beelinelibgdx.actors.BeelineNinePatch;
import org.beelinelibgdx.actors.BeelineRemovable;
import org.beelinelibgdx.screens.BeelineScreen;

class SimpleScreen extends BeelineScreen {

    private final BeelineNinePatch actor;

    public SimpleScreen(Viewport v, BeelineNinePatch actor) {
        super(v);
        this.actor = actor;
    }

    @Override
    public BeelineRemovable getScreenContentAsRemoveable() {
        return (BeelineRemovable) actor;
    }

}
