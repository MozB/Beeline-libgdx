package org.beelinelibgdx.exception;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class BeelineLoadFailureException extends Exception {

    private static final long serialVersionUID = 1L;

    public BeelineLoadFailureException(String path) {
        super("Could not load: " + path);
    }

}
