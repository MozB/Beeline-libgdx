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
import org.beelinelibgdx.actors.PreGameLaunchConfig;
import org.beelinelibgdx.actors.PreGameLaunchConfigBuilder;
import org.beelinelibgdx.exception.BeelineDeleteFailureException;
import org.beelinelibgdx.exception.BeelineLoadFailureException;
import org.beelinelibgdx.exception.BeelineLoadFileNotFoundException;
import org.beelinelibgdx.util.BeelineLogger;

public abstract class BeelineGame<G extends Serializable> extends Game {

	private BeelineAssetManager assets;
	private StretchViewport viewport;
	private static int width;
	private static int height;
	@SuppressWarnings("unused")
	private FPSLogger fpsLogger = new FPSLogger();
	private OrthographicCamera camera;

	@SuppressWarnings("static-access")
	public BeelineGame(int width, int height) {
		this(width, height,
				new BeelineAssetManager(new PreGameLaunchConfigBuilder().build()));
	}

	public BeelineGame(int width, int height, BeelineAssetManager assets) {
		this.width = width;
		this.height = height;
		this.assets = assets;
	}

	@Override
	public void create() {
		BeelineLogger.log(this.getClass().getSimpleName(), "Creating game");
		assets.load();

		camera = createCamera();
		viewport = new StretchViewport(getWidth(), getHeight(), camera);
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	protected OrthographicCamera createCamera() {
		return new OrthographicCamera();
	}

	public G loadGame(String name) throws IOException, ClassNotFoundException, BeelineLoadFailureException, BeelineLoadFileNotFoundException {
		return (G)loadObject(name);
	}

	public Object loadObject(String name) throws IOException, ClassNotFoundException, BeelineLoadFailureException, BeelineLoadFileNotFoundException {
		// unit testing
		if (Gdx.files != null) {
			FileHandle file = Gdx.files.local( assets.getPreGameLaunchConfig().saveGameDirectoryPath + "/" + name);
			if (file.exists()) {
				byte[] bytes = file.readBytes();
				ByteArrayInputStream in = new ByteArrayInputStream(bytes);
				BufferedInputStream bos = new BufferedInputStream(in);
				ObjectInputStream is;

				is = new ObjectInputStream(bos);
				return is.readObject();
			} else {
				throw new BeelineLoadFileNotFoundException(name);
			}
		}
		throw new BeelineLoadFailureException(name);
	}

	public boolean deleteObject(String name) throws BeelineLoadFileNotFoundException, BeelineDeleteFailureException {
		// unit testing
		if (Gdx.files != null) {
			FileHandle file = Gdx.files.local( assets.getPreGameLaunchConfig().saveGameDirectoryPath + "/" + name);
			if (file.exists()) {
				return file.delete();
			} else {
				throw new BeelineLoadFileNotFoundException(name);
			}
		}
		throw new BeelineDeleteFailureException(name);
	}

	public void saveGame(G saveObject, String name) {
		saveObject(saveObject, name);
	}

	public void saveObject(Object saveObject, String name) {
		try {
			BeelineLogger.log("SAVE", "Start write object");

			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			BufferedOutputStream bufferedStream = new BufferedOutputStream(byteStream);
			ObjectOutputStream os = new ObjectOutputStream(bufferedStream);

			os.writeObject(saveObject);
			os.flush();

			BeelineLogger.log("SAVE", "End write object");

			BeelineLogger.log("SAVE", "Start to byte array");
			byte[] array = byteStream.toByteArray();
			BeelineLogger.log("SAVE", "End to byte array");

			BeelineLogger.log("SAVE", "Start write bytes");
			FileHandle file = Gdx.files.local(assets.getPreGameLaunchConfig().saveGameDirectoryPath + "/" + name);
			file.writeBytes(array, false);
			BeelineLogger.log("SAVE", "End write bytes");

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
