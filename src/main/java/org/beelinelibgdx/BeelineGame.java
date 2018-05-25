package org.beelinelibgdx;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.beelinelibgdx.actors.BeelineAssetManager;
import org.beelinelibgdx.exception.BeelineRuntimeException;
import org.beelinelibgdx.tooling.BeelineToolingConfig;
import org.beelinelibgdx.util.BeelineLogger;

public abstract class BeelineGame<G extends Serializable> extends Game {

	private BeelineAssetManager assets;
	private StretchViewport viewport;
	private static int width;
	private static int height;
	private BeelineToolingConfig config;
	@SuppressWarnings("unused")
	private FPSLogger fpsLogger = new FPSLogger();

	@SuppressWarnings("static-access")
	public BeelineGame(int width, int height) {
		this.width = width;
		this.height = height;
	}

	protected void setBeelineToolingConfig(BeelineToolingConfig config) {
		this.config = config;
	}

	@Override
	public void create() {
		BeelineLogger.log(this.getClass().getSimpleName(), "Creating game");

		if (config != null) {
			assets = new BeelineAssetManager(config);
		} else {
			throw new BeelineRuntimeException("You must set up BeelineToolingConfig before creating your game.");
		}

		OrthographicCamera camera = new OrthographicCamera();
		viewport = new StretchViewport(getWidth(), getHeight(), camera);
	}

	public G loadGame(String name) throws IOException, ClassNotFoundException {
		return (G)loadObject(name);
	}

	public Object loadObject(String name) throws IOException, ClassNotFoundException {
		// unit testing
		if (Gdx.files != null) {
			FileHandle file = Gdx.files.local(config.getSaveGameDirectoryPath() + "/" + name);
			if (file.exists()) {
				byte[] bytes = file.readBytes();
				ByteArrayInputStream in = new ByteArrayInputStream(bytes);
				BufferedInputStream bos = new BufferedInputStream(in);
				ObjectInputStream is;

				is = new ObjectInputStream(bos);
				return is.readObject();
			}
		}
		return null;
	}

	public void saveGame(G saveObject, String name) {
		try {
			Gdx.app.log("SAVE", "Start write object");

			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			BufferedOutputStream bufferedStream = new BufferedOutputStream(byteStream);
			ObjectOutputStream os = new ObjectOutputStream(bufferedStream);

			os.writeObject(saveObject);
			os.flush();

			Gdx.app.log("SAVE", "End write object");

			Gdx.app.log("SAVE", "Start to byte array");
			byte[] array = byteStream.toByteArray();
			Gdx.app.log("SAVE", "End to byte array");

			Gdx.app.log("SAVE", "Start write bytes");
			FileHandle file = Gdx.files.local(config.getSaveGameDirectoryPath() + "/" + name);
			file.writeBytes(array, false);
			Gdx.app.log("SAVE", "End write bytes");

			os.reset();
			bufferedStream.close();
			byteStream.close();

			System.gc();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Viewport getViewport() {
		return viewport;
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public BeelineAssetManager getAssetManager() {
		return assets;
	}

	@Override
	public void resize(int w, int h) {
		getViewport().update(w, h);
	}

	@Override
	public void render() {
		super.render();
	}

	protected void removeScreenIfNotNull() {
		if (getScreen() != null) {
			getScreen().dispose();
		}
	}

}
