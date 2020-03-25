package org.beelinelibgdx.utils;

import org.beelinelibgdx.actors.PreGameLaunchConfigBuilder;

public class Utils {

    public static PreGameLaunchConfigBuilder createWorkingTestConfigWithOverridesBuilder() {
        return new PreGameLaunchConfigBuilder()
                .shouldAttemptToGenerateFont(true)
                .shouldAttemptToGenerateSpriteSheet(true)
                .withFontSourceLocalFilePath("src/test/resources/simplegame/fonts/font.ttf")
                .withFontDataOutputFilePath("src/test/resources/simplegame/tmp/fonts/")
                .withSpriteSheetSourceLocalDirectoryPath("src/test/resources/simplegame/img-in/")
                .withSpriteSheetOutputLocalDirectoryPath("src/test/resources/simplegame/tmp/img/spritesheets/")
                .withSaveGameDirectoryPath("src/test/resources/simplegame/tmp/savegames");
    }
}
