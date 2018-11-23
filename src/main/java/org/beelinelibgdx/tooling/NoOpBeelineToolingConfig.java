package org.beelinelibgdx.tooling;

class NoOpBeelineToolingConfig implements BeelineToolingConfig {

    @Override
    public boolean shouldGenerateFontData() {
        return false;
    }

    @Override
    public boolean shouldGenerateSpriteSheet() {
        return false;
    }

    @Override
    public String getImgSourceDirectoryPath() {
        return null;
    }

    @Override
    public String getImgOutputDirectoryPath() {
        return null;
    }

    @Override
    public String getFontSourceFilePath() {
        return null;
    }

    @Override
    public String getFontDataOutputFilePath() {
        return null;
    }

    @Override
    public String getSaveGameDirectoryPath() {
        return null;
    }
}
