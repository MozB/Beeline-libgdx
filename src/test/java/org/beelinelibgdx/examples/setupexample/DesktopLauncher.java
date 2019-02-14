package org.beelinelibgdx.examples.setupexample;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import org.beelinelibgdx.actors.BeelineAssetManager;
import org.junit.Test;

public class DesktopLauncher {
    public static void main(String[] arg) {

        MyGdxGame game = new MyGdxGame();

        new LwjglApplication(game, new LwjglApplicationConfiguration());

    }

    @Test
    public void test() {

    }
}
