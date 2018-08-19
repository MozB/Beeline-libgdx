package org.beelinelibgdx.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BeelineRotatableActor extends Actor {

    private Sprite sprite;

    public BeelineRotatableActor(Sprite sprite, float width, float height) {
        this(sprite);
        setWidth(width);
        setHeight(height);
    }

    public BeelineRotatableActor(Sprite sprite) {
        this.sprite = sprite;
        setWidth(sprite.getWidth());
        setHeight(sprite.getHeight());
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

    // @Override
    // public Color getColor() {
    // return sprite.getColor();
    // }
}
