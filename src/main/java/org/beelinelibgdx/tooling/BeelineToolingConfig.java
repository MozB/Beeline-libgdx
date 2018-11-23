package org.beelinelibgdx.tooling;

public interface BeelineToolingConfig {

    boolean shouldGenerateFontData();

    boolean shouldGenerateSpriteSheet();

    String getImgSourceDirectoryPath();

    String getImgOutputDirectoryPath();

    String getFontSourceFilePath();

    String getFontDataOutputFilePath();

    String getSaveGameDirectoryPath();

}
