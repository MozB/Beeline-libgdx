package org.beelinelibgdx.tooling;

public interface BeelineToolingConfig {

    boolean shouldGenerateFontData();

    boolean shouldGenerateSpritesheet();

    String getImgSourceDirectoryPath();

    String getImgOutputDirectoryPath();

    String getFontSourceFilePath();

    String getFontDataOutputFilePath();

    String getSaveGameDirectoryPath();

}
