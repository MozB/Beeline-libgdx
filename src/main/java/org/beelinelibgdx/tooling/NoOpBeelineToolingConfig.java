package org.beelinelibgdx.tooling;

class NoOpBeelineToolingConfig implements BeelineToolingConfig {

    @Override
    public boolean shouldGenerateFont() {
        return false;
    }

    @Override
    public boolean shouldGenerateSpriteSheet() {
        return false;
    }

    @Override
    public String getSpriteSheetSourceDirectoryPath() {
        return null;
    }

    @Override
    public String getSpriteSheetOutputDirectoryPath() {
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
