package org.beelinelibgdx.examples.setupexample;

import com.badlogic.gdx.graphics.Color;

import org.beelinelibgdx.BeelineGame;
import org.beelinelibgdx.actors.BeelineActor;
import org.beelinelibgdx.actors.BeelineAssetManager;
import org.beelinelibgdx.actors.BeelineAssetPath;
import org.beelinelibgdx.actors.BeelineLabel;
import org.beelinelibgdx.actors.BeelineRemovable;
import org.beelinelibgdx.screens.BeelineScreen;

import static org.beelinelibgdx.utils.Utils.createWorkingTestConfig;

public class MyGdxGame extends BeelineGame {

    public MyGdxGame() {
        super(800, 800, new BeelineAssetManager(createWorkingTestConfig()));
    }

    @Override
    public void create() {
        super.create();
        BeelineScreen screen = new BeelineScreen(getViewport());

        BeelineActor redSquare = new BeelineActor(getAssetManager().createSprite(() -> "square"), 600, 200);
        redSquare.setColor(Color.RED);
        screen.addActor(redSquare);

        BeelineLabel helloWorld = new BeelineLabel("Hello world!", getAssetManager().getSkin());
        screen.addActor(helloWorld);

        setScreen(screen);
    }
}
