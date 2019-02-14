package org.beelinelibgdx.tooling;

@Deprecated
public interface BeelineToolingConfig {

    boolean shouldGenerateFont();

    String getFontSourceFilePath();

    String getFontDataOutputFilePath();

    boolean shouldGenerateSpriteSheet();

    String getSpriteSheetSourceDirectoryPath();

    String getSpriteSheetOutputDirectoryPath();

    String getSaveGameDirectoryPath();

}
