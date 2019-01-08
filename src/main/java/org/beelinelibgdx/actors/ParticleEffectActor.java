package org.beelinelibgdx.actors;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.scenes.scene2d.Actor;

import org.beelinelibgdx.util.BeelineLogger;

public class ParticleEffectActor extends Actor {

    private final ParticleEffect particleEffect;
    private float xOffset;
    private float xOffsetDiff;
    private float lastSetX;
    private float lastSetY;

    public ParticleEffectActor(ParticleEffect particleEffect) {
        super();
        this.particleEffect = particleEffect;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        particleEffect.setEmittersCleanUpBlendFunction(false);
        particleEffect.draw(batch);
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        particleEffect.update(delta);
    }

    public void setXOffset(float xOffset) {
        this.xOffsetDiff = xOffset - this.xOffset;
        this.xOffset = xOffset;
//        BeelineLogger.log(this, "new XOffset: " + xOffset + ", diff: " + this.xOffsetDiff);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        particleEffect.setPosition(x, y);
        lastSetX = x;
        lastSetY = y;
        if (xOffsetDiff != 0) {
            for (ParticleEmitter emitter : particleEffect.getEmitters()) {
                emitter.setAttached(true);
                emitter.setPosition(lastSetX + xOffsetDiff, lastSetY);
//                BeelineLogger.log(this, "lastSetX: " + lastSetX + ", xOffsetDiff: " + xOffsetDiff);
                emitter.setAttached(false);
            }
        }
    }

    public void start() {
        particleEffect.start();
    }

    public ParticleEffect getParticleEffect() {
        return particleEffect;
    }
}
