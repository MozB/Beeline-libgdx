package org.beelinelibgdx.integration.simplegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import org.beelinelibgdx.BeelineGame;
import org.beelinelibgdx.actors.BeelineNinePatch;
import org.beelinelibgdx.actors.BeelineAssetManager;
import org.beelinelibgdx.actors.BeelineAssetPath;
import org.beelinelibgdx.actors.PreGameLaunchConfig;
import org.beelinelibgdx.integration.GameTest;
import org.beelinelibgdx.util.BeelineLogger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.beelinelibgdx.utils.Utils.createWorkingTestConfigWithOverrides;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@PowerMockIgnore({"javax.xml.*", "org.xml.sax.*", "org.w3c.dom.*",  "org.springframework.context.*", "org.apache.log4j.*"})
@RunWith(PowerMockRunner.class)
@PrepareForTest( { Stage.class })
public class TestSimpleGame extends GameTest {

    /**
     * Tests that simple game assets can be loaded from local file configuration
     */
    @Test
    public void testSimpleGameWithOverrides() throws Exception {

        /**Setup**/
        BeelineGame game = new SimpleGame(200, 200, new BeelineAssetManager(createWorkingTestConfigWithOverrides()));
        game.create();

        assertGameFunctional(game);
    }


    private void assertGameFunctional(BeelineGame game) throws Exception {
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
        // update variable and ensure it wasn't saved
        gameModel.variable = 3;
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
        PreGameLaunchConfig workingTestConfig;

        /**Works**/
        game = new SimpleGame(200, 200, new BeelineAssetManager(createWorkingTestConfigWithOverrides()));
        game.create();

        /**No source font**/
        workingTestConfig = createWorkingTestConfigWithOverrides();
        workingTestConfig.fontSourceLocalFilePath = "dfg45dfg";
        game = new SimpleGame(200, 200, new BeelineAssetManager(workingTestConfig));
        try {
            game.create();
            fail();
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("font"));
        }

        /**No source sprite directory**/
        workingTestConfig = createWorkingTestConfigWithOverrides();
        workingTestConfig.spriteSheetSourceLocalDirectoryPath = "dfg45dfg";
        game = new SimpleGame(200, 200, new BeelineAssetManager(workingTestConfig));
        try {
            game.create();
            fail();
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("sprite source directory"));
        }

        /**No sprites in source directory**/
        workingTestConfig = createWorkingTestConfigWithOverrides();
        workingTestConfig.spriteSheetSourceLocalDirectoryPath = "src/test/resources/simplegame/empty-img-in/";
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
        PreGameLaunchConfig preLaunchConfig = new PreGameLaunchConfig();
        preLaunchConfig.spriteSheetSourceLocalDirectoryPath = "dfsfdsfrg4534dfg4";

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
    public void before() {
        deleteTmpDirectory();
    }

    @After
    public void after() {
        deleteTmpDirectory();
    }

    private void deleteTmpDirectory() {
        boolean deleted = Gdx.files.local("src/test/resources/simplegame/tmp/").deleteDirectory();
        BeelineLogger.log("Deleting temp directory", deleted + "");
    }

    private class SimpleGame extends BeelineGame<GameModel> {

        public SimpleGame(int x, int y, BeelineAssetManager assets) {
            super(x, y, assets);
        }

        @Override
        public void create() {
            getAssetManager().getPreferences().clear();
            super.create();
        }
    }
}
