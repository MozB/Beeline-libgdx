package org.beelinelibgdx.exception;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import static com.badlogic.gdx.scenes.scene2d.ui.Table.Debug.all;

public class BeelineMissingAssetRuntimeException extends BeelineRuntimeException {

    public BeelineMissingAssetRuntimeException(String missingPath, AssetManager manager) {
        super("Asset could not be found in atlas\nAsset invalid: " + missingPath + "\n" + assetManagerToString(manager));
    }

    private static String assetManagerToString(AssetManager manager) {
        String s = "Valid assets in atlas: ";
        Array<TextureAtlas> atlases = manager.getAll(TextureAtlas.class, Array.of(TextureAtlas.class));
        for (TextureAtlas atlas : atlases) {
            for (TextureRegion region : atlas.getRegions()) {
                s += region.toString() + ", ";
            }
        }
        s = s.substring(0, s.length()-2);
        return s;
    }

}
