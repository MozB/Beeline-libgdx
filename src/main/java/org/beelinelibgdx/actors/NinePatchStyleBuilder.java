package org.beelinelibgdx.actors;

import com.badlogic.gdx.graphics.Color;

public class NinePatchStyleBuilder {
    private BeelineAssetPath texture;
    private BeelineAssetPath pressedDownTexture;
    private BeelineAssetPath disabledTexture;
    private BeelineAssetPath checkedTexture;
    private Color color;
    private Color pressedDownColor;
    private Color disabledColor;
    private Color checkedColor;
    private Color fontColor;
    private Color pressedDownFontColor;
    private Color checkedFontColor;
    private Color disabledFontColor;
    private Integer border;
    private int leftBorder;
    private int rightBorder;
    private int topBorder;
    private int bottomBorder;

    public NinePatchStyleBuilder withTexture(BeelineAssetPath texture) {
        this.texture = texture;
        return this;
    }

    public NinePatchStyleBuilder withPressedDownTexture(BeelineAssetPath pressedDownTexture) {
        this.pressedDownTexture = pressedDownTexture;
        return this;
    }

    public NinePatchStyleBuilder withDisabledTexture(BeelineAssetPath disabledTexture) {
        this.disabledTexture = disabledTexture;
        return this;
    }

    public NinePatchStyleBuilder withCheckedTexture(BeelineAssetPath checkedTexture) {
        this.checkedTexture = checkedTexture;
        return this;
    }

    public NinePatchStyleBuilder withColor(Color color) {
        this.color = color;
        return this;
    }

    public NinePatchStyleBuilder withPressedDownColor(Color pressedDownColor) {
        this.pressedDownColor = pressedDownColor;
        return this;
    }

    public NinePatchStyleBuilder withDisabledColor(Color disabledColor) {
        this.disabledColor = disabledColor;
        return this;
    }

    public NinePatchStyleBuilder withCheckedColor(Color checkedColor) {
        this.checkedColor = checkedColor;
        return this;
    }

    public NinePatchStyleBuilder withFontColor(Color fontColor) {
        this.fontColor = fontColor;
        return this;
    }

    public NinePatchStyleBuilder withPressedDownFontColor(Color pressedDownFontColor) {
        this.pressedDownFontColor = pressedDownFontColor;
        return this;
    }

    public NinePatchStyleBuilder withCheckedFontColor(Color checkedFontColor) {
        this.checkedFontColor = checkedFontColor;
        return this;
    }

    public NinePatchStyleBuilder withDisabledFontColor(Color disabledFontColor) {
        this.disabledFontColor = disabledFontColor;
        return this;
    }

    public NinePatchStyleBuilder withBorder(int border) {
        this.border = border;
        return this;
    }

    public NinePatchStyleBuilder withLeftBorder(int border) {
        this.leftBorder = border;
        return this;
    }

    public NinePatchStyleBuilder withRightBorder(int border) {
        this.rightBorder = border;
        return this;
    }

    public NinePatchStyleBuilder withTopBorder(int border) {
        this.topBorder = border;
        return this;
    }

    public NinePatchStyleBuilder withBottomBorder(int border) {
        this.bottomBorder = border;
        return this;
    }

    public NinePatchStyle build() {
        if (border != null) {
            leftBorder = border;
            rightBorder = border;
            topBorder = border;
            bottomBorder = border;
        }
        return new NinePatchStyle(texture, pressedDownTexture, disabledTexture, checkedTexture, color, pressedDownColor,
                disabledColor, checkedColor, fontColor, pressedDownFontColor, checkedFontColor, disabledFontColor,
                leftBorder, rightBorder, topBorder, bottomBorder);
    }
}