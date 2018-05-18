package org.moz.beeline.actors;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.google.common.collect.Lists;

public abstract class BeelineAssetManager {

	private final String atlasPath;
	private AssetManager manager;
	private Preferences preferences;

	public BeelineAssetManager() {
		int spriteSheetSize;
		if (GL20.GL_MAX_TEXTURE_SIZE == 2048) {
			spriteSheetSize = 2048;
		} else {
			spriteSheetSize = 4096;
		}
		atlasPath = "imgout" + spriteSheetSize + "/atlas.atlas";

		manager = new AssetManager();
		manager.load(atlasPath, TextureAtlas.class);

		setupFonts();
	}

	public void loadMusic(List<BeelineAssetPath> paths) {
		loadAsset(paths, Music.class);
	}

	public void loadSound(List<BeelineAssetPath> paths) {
		loadAsset(paths, Sound.class);
	}

	private void loadAsset(List<BeelineAssetPath> paths, Class clazz) {
		for (BeelineAssetPath music : paths) {
			manager.load(music.getAssetPath(), clazz);
		}
		manager.finishLoading();
	}

	public void dispose() {
		getManager().dispose();
		manager = null;
	}

	public boolean isMusicEnabled() {
		return getPreferences().getBoolean("music", true);
	}

	public boolean isSoundEnabled() {
		return getPreferences().getBoolean("sound", true);
	}

	public void setMusicEnabled(boolean b) {
		getPreferences().putBoolean("music", b);
		getPreferences().flush();
	}

	public void setSoundEnabled(boolean b) {
		getPreferences().putBoolean("sound", b);
		getPreferences().flush();
	}

	public Preferences getPreferences() {
		if (preferences == null) {
			preferences = Gdx.app.getPreferences("game");
		}
		return preferences;
	}

	protected void setupFonts() {
	}

	private AssetManager getManager() {
		return manager;
	}

	public Sprite createSprite(BeelineAssetPath path) {
		return getManager().get(path.getAssetPath(), TextureAtlas.class).createSprite(path.getAssetPath());
	}

	public AtlasRegion getRegion(BeelineAssetPath path) {
		return getManager().get(path.getAssetPath(), TextureAtlas.class).findRegion(path.getAssetPath());
	}

	public static boolean isColorDark(Color color) {
		double darkness = 1 - (0.299 * color.r + 0.587 * color.g + 0.114 * color.b);
		if (darkness < 0.5) {
			return false; // It's a light color
		} else {
			return true; // It's a dark color
		}
	}

}
