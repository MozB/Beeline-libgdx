package org.beelinelibgdx.integration.simplegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import org.beelinelibgdx.BeelineGame;
import org.beelinelibgdx.actors.BeelineNinePatch;
import org.beelinelibgdx.actors.BeelineAssetManager;
import org.beelinelibgdx.actors.BeelineAssetPath;
import org.beelinelibgdx.integration.GameTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.beelinelibgdx.utils.Utils.createWorkingTestConfig;
import static org.junit.Assert.assertTrue;
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
        BeelineAssetPath squarePath = () -> "square";
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
        BeelineNinePatch actor = new BeelineNinePatch(game.getAssetManager().createNinePatchStyle(squarePath), 100, 100 ,"Hello world!");
        Assert.assertEquals("Button was created", "Hello world!", actor.getText().toString());

        /**Add actor to a test screen**/
        SpriteBatch spriteBatch = Mockito.mock(SpriteBatch.class);
        PowerMockito.whenNew(SpriteBatch.class).withAnyArguments().thenReturn(spriteBatch);

        SimpleScreen screen = new SimpleScreen(game.getViewport(), actor);
        game.setScreen(screen);

    }

    @Test
    public void testSimpleGameIllegalConfigurations() {
        BeelineGame game;
        BeelineAssetManager.PreGameLaunchConfig workingTestConfig;

        /**Works**/
        game = new SimpleGame(200, 200, new BeelineAssetManager(createWorkingTestConfig()));
        game.create();

        /**No source font**/
        workingTestConfig = createWorkingTestConfig();
        workingTestConfig.fontSourceFilePath = "dfg45dfg";
        game = new SimpleGame(200, 200, new BeelineAssetManager(workingTestConfig));
        try {
            game.create();
            fail();
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("font"));
        }

        /**No source sprite directory**/
        workingTestConfig = createWorkingTestConfig();
        workingTestConfig.spriteSheetSourceDirectoryPath = "dfg45dfg";
        game = new SimpleGame(200, 200, new BeelineAssetManager(workingTestConfig));
        try {
            game.create();
            fail();
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("sprite source directory"));
        }

        /**No sprites in source directory**/
        workingTestConfig = createWorkingTestConfig();
        workingTestConfig.spriteSheetSourceDirectoryPath = "resources/test/simplegame/empty-img-in/";
        game = new SimpleGame(200, 200, new BeelineAssetManager(workingTestConfig));
        try {
            game.create();
            fail();
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("any sprites"));
        }
    }

    @Test
    public void testSimpleGameNoAssets() {
        /**Setup**/
        BeelineAssetManager.PreGameLaunchConfig preLaunchConfig = new BeelineAssetManager.PreGameLaunchConfig();
        preLaunchConfig.spriteSheetSourceDirectoryPath = "dfsfdsfrg4534dfg4";

        BeelineAssetManager assets = new BeelineAssetManager(preLaunchConfig);
        BeelineGame game = new SimpleGame(200, 200, assets);
        try {
            game.create();
            fail();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setup() {
        Gdx.files.local("resources/test/simplegame/tmp/").deleteDirectory();
    }

    private class SimpleGame extends BeelineGame<GameModel> {

        public SimpleGame(int x, int y, BeelineAssetManager assets) {
            super(x, y, assets);
        }

        public SimpleGame(int x, int y) {
            super(x, y, new BeelineAssetManager(createWorkingTestConfig()));
        }
    }
}
