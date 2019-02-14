package org.beelinelibgdx.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class BeelineActor extends Actor {

    private Sprite sprite;

    public BeelineActor(Sprite sprite, float width, float height) {
        this(sprite);
        setWidth(width);
        setHeight(height);
    }

    public BeelineActor(Sprite sprite) {
        this.sprite = sprite;
        setWidth(sprite.getWidth());
        setHeight(sprite.getHeight());

        addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                onTouchUp();
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                onTouchDown();
                return true;
            }
        });
    }

    public void onTouchUp() {

    }

    public void onTouchDown() {

    }

    @Override
    public void draw(Batch batch, float alpha) {
        Color color = super.getColor();
        batch.setColor(color.r, color.g, color.b, color.a * alpha);
        batch.draw(sprite, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(),
                getScaleY(), getRotation());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void size(int scale) {
        setWidth(getWidth() * scale);
        setHeight(getHeight() * scale);
    }

    // @Override
    // public Color getColor() {
    // return sprite.getColor();
    // }
}
