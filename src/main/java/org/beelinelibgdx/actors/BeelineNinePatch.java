package org.beelinelibgdx.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class BeelineNinePatch extends TextButton implements BeelineRefreshable {

    public BeelineNinePatch(TextButtonStyle style, float width, float height) {
        this(style, width, height, "");
    }

    public BeelineNinePatch(TextButtonStyle style, float width, float height, String label) {
        super(label, style);

        addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(this.getClass().getName(),
                        getText().toString() + " touchUp()" + (isChecked() ? " checked" : ""));
                onTouchUp();
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(this.getClass().getName(),
                        getText().toString() + " touchDown()" + (isChecked() ? " checked" : ""));
                onTouchDown();
                return true;
            }
        });

        setWidth(width);
        setHeight(height);
    }

    public void onTouchUp() {

    }

    public void onTouchDown() {

    }

    @Override
    public void refresh() {

    }
}

