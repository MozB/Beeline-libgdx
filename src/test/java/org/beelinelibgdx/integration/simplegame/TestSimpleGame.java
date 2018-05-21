package org.beelinelibgdx.integration.simplegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.Sprite;

import org.beelinelibgdx.BeelineGame;
import org.beelinelibgdx.actors.BeelineAssetPath;
import org.beelinelibgdx.integration.GameTest;
import org.beelinelibgdx.tooling.BeelineToolingConfig;
import org.beelinelibgdx.util.BeelineLogger;
import org.junit.Test;

import java.io.Serializable;

//@RunWith(GdxTestRunner.class)
public class TestSimpleGame extends GameTest {

    @Test
    public void testSimpleGame() {

        BeelineToolingConfig beelineConfig = new BeelineToolingConfig() {
            @Override
            public boolean shouldGenerateFontData() {
                return true;
            }

            @Override
            public boolean shouldGenerateSpriteMap() {
                return true;
            }

            @Override
            public String getAssetImgSourcePath() {
                return "resources/test/simplegame/img-in/";
            }

            @Override
            public String getAssetImgOutputPath() {
                return "resources/test/simplegame/gen/img/spritesheets/";
            }

            @Override
            public String getFontFileSourcePath() {
                return "resources/test/simplegame/fonts/font.ttf";
            }

            @Override
            public String getFontDataFileOutputPath() {
                return "resources/test/simplegame/gen/fonts/";
            }
        };

        Gdx.files.local("resources/test/simplegame/gen/").deleteDirectory();

        BeelineGame game = new BeelineGame<GameModel>(200, 200, beelineConfig) {
        };
        game.create();

        Sprite sprite = game.getAssetManager().createSprite(new BeelineAssetPath() {
            @Override
            public String getAssetPath() {
                return "square";
            }
        });
        BeelineLogger.log(this, "" + sprite.getHeight());
    }

//    public static void main (String args[]) {
//        new TestSimpleGame().testSimpleGame();
//    }

    private class GameModel implements Serializable {

    }

}
