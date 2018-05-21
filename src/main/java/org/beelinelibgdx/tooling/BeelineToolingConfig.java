package org.beelinelibgdx.tooling;

public interface BeelineToolingConfig {

    boolean shouldGenerateFontData();

    boolean shouldGenerateSpriteMap();

    String getAssetImgSourcePath();

    String getAssetImgOutputPath();

    String getFontFileSourcePath();

    String getFontDataFileOutputPath();

}
