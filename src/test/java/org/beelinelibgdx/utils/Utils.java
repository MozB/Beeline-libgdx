package org.beelinelibgdx.utils;

import org.beelinelibgdx.actors.BeelineAssetManager;

public class Utils {

    public static BeelineAssetManager.PreGameLaunchConfig createWorkingTestConfig() {
        BeelineAssetManager.PreGameLaunchConfig config = new BeelineAssetManager.PreGameLaunchConfig();
        config.shouldGenerateFont = true;
        config.shouldGenerateSpriteSheet = true;
        config.fontSourceFilePath = "resources/test/simplegame/fonts/font.ttf";
        config.fontDataOutputFilePath = "resources/test/simplegame/tmp/fonts/";
        config.spriteSheetSourceDirectoryPath = "resources/test/simplegame/img-in/";
        config.spriteSheetOutputDirectoryPath = "resources/test/simplegame/tmp/img/spritesheets/";
        config.saveGameDirectoryPath = "resources/test/simplegame/tmp/savegames";
        return config;
    }
}
