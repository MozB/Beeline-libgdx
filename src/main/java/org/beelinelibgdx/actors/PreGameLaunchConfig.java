package org.beelinelibgdx.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class PreGameLaunchConfig {
    private static final String DEFAULT_FONT_SOURCE_FILE_PATH = "fonts/font.ttf";

    public boolean shouldAttemptToGenerateFont = true;
    private String fontSourceLocalFilePath;
    public String fontDataOutputFilePath = "beeline-out/fonts/font";
    public boolean shouldAttemptToGenerateSpriteSheet = true;
    private String spriteSheetSourceLocalDirectoryPath = "beeline-in/img/";
    public String spriteSheetOutputLocalDirectoryPath = "beeline-out/img/";
    public String saveGameDirectoryPath = "beeline-out/savegames/";

    public FileHandle getFontSourceFileHandle() {
        return fontSourceLocalFilePath == null ?
                Gdx.files.classpath(DEFAULT_FONT_SOURCE_FILE_PATH) : Gdx.files.local(fontSourceLocalFilePath);
    }

    public void setFontSourceLocalFilePath(String fontSourceLocalFilePath) {
        this.fontSourceLocalFilePath = fontSourceLocalFilePath;
    }

    public FileHandle getSpriteSheetSourceDirectoryFileHandle() {
        return Gdx.files.local(spriteSheetSourceLocalDirectoryPath);
    }

    public void setSpriteSheetSourceLocalDirectoryPath(String spriteSheetSourceLocalDirectoryPath) {
        this.spriteSheetSourceLocalDirectoryPath = spriteSheetSourceLocalDirectoryPath;
    }
}
