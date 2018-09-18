package org.beelinelibgdx.integration.simplegame;

import com.badlogic.gdx.utils.viewport.Viewport;

import org.beelinelibgdx.actors.BeelineActor;
import org.beelinelibgdx.actors.BeelineRefreshable;
import org.beelinelibgdx.screens.BeelineScreen;

class SimpleScreen extends BeelineScreen {

    private final BeelineActor actor;

    public SimpleScreen(Viewport v, BeelineActor actor) {
        super(v);
        this.actor = actor;
    }

    @Override
    public BeelineRefreshable getScreenContentAsRemoveable() {
        return actor;
    }

}
