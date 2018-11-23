package org.beelinelibgdx.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ParticleEffectActor extends Actor {

    private final ParticleEffect particleEffect;

    public ParticleEffectActor(ParticleEffect particleEffect) {
        super();
        this.particleEffect = particleEffect;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        particleEffect.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        particleEffect.update(delta);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        particleEffect.setPosition(x, y);
    }

    public void start() {
        particleEffect.start();
    }

    public ParticleEffect getParticleEffect() {
        return particleEffect;
    }
}
