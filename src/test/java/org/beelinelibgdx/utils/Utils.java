package org.beelinelibgdx.utils;

import org.beelinelibgdx.actors.PreGameLaunchConfig;

public class Utils {

    public static PreGameLaunchConfig createWorkingTestConfigWithOverrides() {
        PreGameLaunchConfig config = new PreGameLaunchConfig();
        config.shouldAttemptToGenerateFont = true;
        config.shouldAttemptToGenerateSpriteSheet = true;
        config.setFontSourceLocalFilePath("simplegame/fonts/font.ttf");
        config.fontDataOutputFilePath = "simplegame/tmp/fonts/";
        config.setSpriteSheetSourceLocalDirectoryPath("simplegame/img-in/");
        config.spriteSheetOutputLocalDirectoryPath = "simplegame/tmp/img/spritesheets/";
        config.saveGameDirectoryPath = "simplegame/tmp/savegames";
        return config;
    }

    public static PreGameLaunchConfig createWorkingTestConfigWithClasspathDefaults() {
        PreGameLaunchConfig config = new PreGameLaunchConfig();
        config.shouldAttemptToGenerateFont = true;
        config.shouldAttemptToGenerateSpriteSheet = true;
        config.fontDataOutputFilePath = "simplegame/tmp/fonts/";
        config.setSpriteSheetSourceLocalDirectoryPath("simplegame/img-in/");
        config.spriteSheetOutputLocalDirectoryPath = "simplegame/tmp/img/spritesheets/";
        config.saveGameDirectoryPath = "simplegame/tmp/savegames";
        return config;
    }
}
