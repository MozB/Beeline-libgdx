package org.moz.beelinelibgdx.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import org.moz.beelinelibgdx.core.assets.BeelineAssetManager;
import org.moz.beelinelibgdx.core.util.BeelineLogger;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class BeelineGame<G extends Serializable, A extends BeelineAssetManager> extends Game {

	private A assets;
	private StretchViewport viewport;
	private static int width;
	private static int height;
	@SuppressWarnings("unused")
	private FPSLogger fpsLogger = new FPSLogger();

	@SuppressWarnings("static-access")
	public BeelineGame(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public void create() {
		BeelineLogger.log(this.getClass().getSimpleName(), "Creating game");

		assets = createBeelineAssetManager();

		OrthographicCamera camera = new OrthographicCamera();
		viewport = new StretchViewport(getWidth(), getHeight(), camera);
	}

	public G loadGame(String name) throws IOException, ClassNotFoundException {
		return (G)loadObject(name);
	}

	public Object loadObject(String name) throws IOException, ClassNotFoundException {
		// unit testing
		if (Gdx.files != null) {
			FileHandle file = Gdx.files.local("savegames/" + name + ".txt");
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
			FileHandle file = Gdx.files.local("savegames/" + name + ".txt");
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

	protected Viewport getViewport() {
		return viewport;
	}

	protected abstract A createBeelineAssetManager();

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public A getAssets() {
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
