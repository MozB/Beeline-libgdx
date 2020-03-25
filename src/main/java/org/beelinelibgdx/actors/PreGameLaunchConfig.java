package org.beelinelibgdx.actors;

public class PreGameLaunchConfig {

    public final boolean shouldAttemptToGenerateFont;
    public final String fontSourceLocalFilePath;
    public final String fontDataOutputFilePath;
    public final boolean shouldAttemptToGenerateSpriteSheet;
    public final String spriteSheetSourceLocalDirectoryPath;
    public final String spriteSheetOutputLocalDirectoryPath;
    public final String saveGameDirectoryPath;

    PreGameLaunchConfig(
            boolean shouldAttemptToGenerateFont,
            String fontSourceLocalFilePath,
            String fontDataOutputFilePath,
            boolean shouldAttemptToGenerateSpriteSheet,
            String spriteSheetSourceLocalDirectoryPath,
            String spriteSheetOutputLocalDirectoryPath,
            String saveGameDirectoryPath) {
        this.shouldAttemptToGenerateFont = shouldAttemptToGenerateFont;
        this.fontSourceLocalFilePath = fontSourceLocalFilePath;
        this.fontDataOutputFilePath = fontDataOutputFilePath;
        this.shouldAttemptToGenerateSpriteSheet = shouldAttemptToGenerateSpriteSheet;
        this.spriteSheetSourceLocalDirectoryPath = spriteSheetSourceLocalDirectoryPath;
        this.spriteSheetOutputLocalDirectoryPath = spriteSheetOutputLocalDirectoryPath;
        this.saveGameDirectoryPath = saveGameDirectoryPath;
    }

}
