package org.beelinelibgdx.actors;

public class PreGameLaunchConfigBuilder {
    private boolean shouldAttemptToGenerateFont = true;
    private String fontSourceLocalFilePath = "../../beeline-in/fonts/font.ttf";
    private String fontDataOutputFilePath = "beeline-out/fonts/font";
    private boolean shouldAttemptToGenerateSpriteSheet = true;
    private String spriteSheetSourceLocalDirectoryPath = "../../beeline-in/img/";
    private String spriteSheetOutputLocalDirectoryPath = "beeline-out/img/";
    private String saveGameDirectoryPath = "beeline-out/savegames/";

    public PreGameLaunchConfigBuilder shouldAttemptToGenerateFont(boolean shouldAttemptToGenerateFont) {
        this.shouldAttemptToGenerateFont = shouldAttemptToGenerateFont;
        return this;
    }

    public PreGameLaunchConfigBuilder withFontSourceLocalFilePath(String fontSourceLocalFilePath) {
        this.fontSourceLocalFilePath = fontSourceLocalFilePath;
        return this;
    }

    public PreGameLaunchConfigBuilder withFontDataOutputFilePath(String fontDataOutputFilePath) {
        this.fontDataOutputFilePath = fontDataOutputFilePath;
        return this;
    }

    public PreGameLaunchConfigBuilder shouldAttemptToGenerateSpriteSheet(boolean shouldAttemptToGenerateSpriteSheet) {
        this.shouldAttemptToGenerateSpriteSheet = shouldAttemptToGenerateSpriteSheet;
        return this;
    }

    public PreGameLaunchConfigBuilder withSpriteSheetSourceLocalDirectoryPath(String spriteSheetSourceLocalDirectoryPath) {
        this.spriteSheetSourceLocalDirectoryPath = spriteSheetSourceLocalDirectoryPath;
        return this;
    }

    public PreGameLaunchConfigBuilder withSpriteSheetOutputLocalDirectoryPath(String spriteSheetOutputLocalDirectoryPath) {
        this.spriteSheetOutputLocalDirectoryPath = spriteSheetOutputLocalDirectoryPath;
        return this;
    }

    public PreGameLaunchConfigBuilder withSaveGameDirectoryPath(String saveGameDirectoryPath) {
        this.saveGameDirectoryPath = saveGameDirectoryPath;
        return this;
    }

    public PreGameLaunchConfig build() {
        return new PreGameLaunchConfig(shouldAttemptToGenerateFont, fontSourceLocalFilePath, fontDataOutputFilePath, shouldAttemptToGenerateSpriteSheet, spriteSheetSourceLocalDirectoryPath, spriteSheetOutputLocalDirectoryPath, saveGameDirectoryPath);
    }
}