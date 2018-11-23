package org.beelinelibgdx.integration.simplegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import org.beelinelibgdx.BeelineGame;
import org.beelinelibgdx.actors.BeelineActor;
import org.beelinelibgdx.actors.BeelineAssetPath;
import org.beelinelibgdx.exception.BeelineMissingAssetRuntimeException;
import org.beelinelibgdx.integration.GameTest;
import org.beelinelibgdx.tooling.BeelineToolingConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.fail;

@RunWith(PowerMockRunner.class)
@PrepareForTest( { Stage.class })
public class TestSimpleGame extends GameTest {

    @Test
    public void testSimpleGame() throws Exception {

        /**Setup**/
        BeelineGame game = new SimpleGame(200, 200);
        game.create();

        /**Test sprites can be created from the generated spritesheet**/
        BeelineAssetPath squarePath = new BeelineAssetPath() {
            @Override
            public String getAssetPath() {
                return "square";
            }
        };
        Sprite sprite = game.getAssetManager().createSprite(squarePath);
        Assert.assertEquals("Test sprite dimensions loaded correctly", 8, sprite.getWidth(), 0.1);
        Assert.assertEquals("Test sprite dimensions loaded correctly", 8, sprite.getHeight(), 0.1);

        GameModel gameModel = new GameModel();
        Assert.assertEquals("Variable not set", 0, gameModel.variable);
        gameModel.variable = 2;

        /**Test game can be saved then loaded**/
        String gameName = "a save game";
        game.saveGame(gameModel, gameName);
        GameModel loadedGame = (GameModel) game.loadGame(gameName);
        Assert.assertEquals("Variable was saved, then loaded successfully", 2, loadedGame.variable);

        game.render();
        game.resize(400, 400);

        /**Test actor can be created and added with text**/
        BeelineActor actor = new BeelineActor(game.getAssetManager().getActorStyle(squarePath), 100, 100 ,"Hello world!");
        Assert.assertEquals("Button was created", "Hello world!", actor.getText().toString());

        /**Add actor to a test screen**/
        SpriteBatch spriteBatch = Mockito.mock(SpriteBatch.class);
        PowerMockito.whenNew(SpriteBatch.class).withAnyArguments().thenReturn(spriteBatch);

        SimpleScreen screen = new SimpleScreen(game.getViewport(), actor);
        game.setScreen(screen);

    }

    @Test
    public void testSimpleMissingAsset() throws Exception {

        /**Setup**/
        BeelineGame game = new SimpleGame(200, 200);
        game.create();

        /**Test sprites can be created from the generated spritesheet**/
        BeelineAssetPath squarePath = new BeelineAssetPath() {
            @Override
            public String getAssetPath() {
                return "square2"; //does not exist
            }
        };
        try {
            Sprite sprite = game.getAssetManager().createSprite(squarePath);
            fail();
        } catch (BeelineMissingAssetRuntimeException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setup() {
        Gdx.files.local("resources/test/simplegame/tmp/").deleteDirectory();
    }

    private class SimpleGame extends BeelineGame<GameModel> {
        public SimpleGame(int x, int y) {
            super(x, y);

            BeelineToolingConfig beelineToolingConfig = new BeelineToolingConfig() {
                @Override
                public boolean shouldGenerateFontData() {
                    return true;
                }

                @Override
                public boolean shouldGenerateSpriteSheet() {
                    return true;
                }

                @Override
                public String getImgSourceDirectoryPath() {
                    return "resources/test/simplegame/img-in/";
                }

                @Override
                public String getImgOutputDirectoryPath() {
                    return "resources/test/simplegame/tmp/img/spritesheets/";
                }

                @Override
                public String getFontSourceFilePath() {
                    return "resources/test/simplegame/fonts/font.ttf";
                }

                @Override
                public String getFontDataOutputFilePath() {
                    return "resources/test/simplegame/tmp/fonts/";
                }

                @Override
                public String getSaveGameDirectoryPath() {
                    return "resources/test/simplegame/tmp/savegames";
                }
            };
            setBeelineToolingConfig(beelineToolingConfig);
        }

//        public GameModel loadGame(String name) throws IOException, ClassNotFoundException {
//            return null;
//        }
    }
}
