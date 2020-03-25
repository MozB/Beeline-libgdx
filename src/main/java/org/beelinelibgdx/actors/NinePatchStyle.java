package org.beelinelibgdx.actors;

import com.badlogic.gdx.graphics.Color;

public class NinePatchStyle {

    NinePatchStyle(BeelineAssetPath texture, BeelineAssetPath pressedDownTexture, BeelineAssetPath disabledTexture, BeelineAssetPath checkedTexture, Color color, Color pressedDownColor, Color disabledColor, Color checkedColor,
                   Color fontColor, Color pressedDownFontColor, Color checkedFontColor, Color disabledFontColor,
                   int leftBorder, int rightBorder, int topBorder, int bottomBorder) {
        this.texture = texture;
        this.pressedDownTexture = pressedDownTexture;
        this.disabledTexture = disabledTexture;
        this.checkedTexture = checkedTexture;
        this.color = color;
        this.pressedDownColor = pressedDownColor;
        this.disabledColor = disabledColor;
        this.checkedColor = checkedColor;
        this.fontColor = fontColor;
        this.pressedDownFontColor = pressedDownFontColor;
        this.checkedFontColor = checkedFontColor;
        this.disabledFontColor = disabledFontColor;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        this.topBorder = topBorder;
        this.bottomBorder = bottomBorder;
    }

    BeelineAssetPath texture;
    BeelineAssetPath pressedDownTexture;
    BeelineAssetPath disabledTexture;
    BeelineAssetPath checkedTexture;
    Color color;
    Color pressedDownColor;
    Color disabledColor;
    Color checkedColor;
    Color fontColor;
    Color pressedDownFontColor;
    Color checkedFontColor;
    Color disabledFontColor;
    int leftBorder;
    int rightBorder;
    int topBorder;
    int bottomBorder;
    
}
