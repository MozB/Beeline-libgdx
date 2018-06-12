package org.beelinelibgdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import org.beelinelibgdx.actors.BeelineRefreshable;
import org.beelinelibgdx.actors.BeelineRemovable;

public abstract class BeelineScreen extends Stage implements Screen, InputProcessor, BeelineRefreshable {

    private FPSLogger log;
    private boolean firstRender = true;
    private int lastFrameRenderCalls;

    public BeelineScreen(Viewport v) {
        super(v);
        log = new FPSLogger();
        GLProfiler.enable();
    }

    public abstract BeelineRemovable getScreenContent();

    @Override
    public void show() {
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta) {
        if (firstRender) {
            firstRender = false;
        } else {
            Gdx.input.setInputProcessor(this);
            SpriteBatch batch = (SpriteBatch) getBatch();
            batch.totalRenderCalls = 0;
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

            // Calling to Stage methods
            super.act(delta);
            super.draw();
            lastFrameRenderCalls = batch.totalRenderCalls;
        }
    }

    @Override
    public boolean keyUp(int keyCode) {
        if (keyCode == Input.Keys.BACK || keyCode == Input.Keys.BACKSPACE) {
            onBack();
            return true;
        }
        return false;
    }

    public void onBack() {

    }

    @Override
    public void resize(int width, int height) {
        getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        super.dispose();
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void refresh() {
    }

    /**
     * Can be used as a good measure of performance.  The lower this number, the less times
     * SpriteBatch has needed to reload texture data into memory.  The benefit of having a
     * single sprite sheet (or as less as possible) is that all of images can be in memory at once.
     * Therefore reducing this number.
     *
     * If you load images into memory one by one and do not have a sprite sheet, this number starts
     * climbing into double figures you will see a significant impact on performance in even the
     * simplest of stages.
     *
     * @return the number of times {@link SpriteBatch} had to re-render in the last frame.  Try to
     * keep this as low as possible, 1 is realistic if you can fit all of your game images into
     * one sprite sheet.
     */
    public int getLastFrameRenderCalls() {
        return lastFrameRenderCalls;
    }
}
