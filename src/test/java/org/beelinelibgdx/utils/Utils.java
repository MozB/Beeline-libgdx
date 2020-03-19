package org.beelinelibgdx.utils;

import org.beelinelibgdx.actors.PreGameLaunchConfig;

public class Utils {

    public static PreGameLaunchConfig createWorkingTestConfigWithOverrides() {
        PreGameLaunchConfig config = new PreGameLaunchConfig();
        config.shouldAttemptToGenerateFont = true;
        config.shouldAttemptToGenerateSpriteSheet = true;
        config.fontSourceLocalFilePath = "src/test/resources/simplegame/fonts/font.ttf";
        config.fontDataOutputFilePath = "src/test/resources/simplegame/tmp/fonts/";
        config.spriteSheetSourceLocalDirectoryPath = "src/test/resources/simplegame/img-in/";
        config.spriteSheetOutputLocalDirectoryPath = "src/test/resources/simplegame/tmp/img/spritesheets/";
        config.saveGameDirectoryPath = "src/test/resources/simplegame/tmp/savegames";
        return config;
    }
}
