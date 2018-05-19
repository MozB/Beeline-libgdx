package org.beelinelibgdx.core.examples.simplegame;

import java.io.Serializable;
import org.moz.beelinelibgdx.core.BeelineGame;
import org.moz.beelinelibgdx.core.assets.BeelineAssetManager;

public class SimpleGameExample extends BeelineGame<SimpleGameExample.ExampleGameModel, SimpleGameExample.ExampleAssetManager> {

    public SimpleGameExample() {
        super(800, 600);
    }

    @Override
    protected ExampleAssetManager createBeelineAssetManager() {
        return new ExampleAssetManager();
    }

    class ExampleGameModel implements Serializable {

    }

    class ExampleAssetManager extends BeelineAssetManager {

    }

}
