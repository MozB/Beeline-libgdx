package org.beelinelibgdx.actors;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class BeelineLabel extends Label {

    public BeelineLabel(CharSequence text, Skin skin) {
        super(text, skin);
    }

    public void setMaxWidth(float width) {
        if (getPrefWidth() > width) {
            float ratio = getFontScaleX() * (width / getPrefWidth());
            setFontScale(ratio);
            setSize(getPrefWidth(), getPrefHeight());
        }
    }

    public void setPositionAndResize(float x, float y, int align) {
        setSize(getPrefWidth(), getPrefHeight());
        super.setPosition(x, y, align);
    }

    public void refreshPosition() {
        setPositionAndResize(getX(), getY(), getLabelAlign());
    }

}
