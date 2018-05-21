package org.beelinelibgdx.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.tools.bmfont.BitmapFontWriter;
import com.badlogic.gdx.tools.hiero.Hiero;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.google.common.collect.Lists;

import org.beelinelibgdx.tooling.BeelineToolingConfig;

import java.util.List;

public class BeelineAssetManager {

	private final String atlasPath;
	private AssetManager manager;
	private Preferences preferences;

	public BeelineAssetManager(BeelineToolingConfig config) {
		int size;
		if (GL20.GL_MAX_TEXTURE_SIZE == 2048) {
			size = 2048;
		} else {
			size = 4096;
		}

		if (config.shouldGenerateFontData()) {
			createFontPng(config);
		}

		if (config.shouldGenerateSpriteMap()) {
			try {
				createSpriteSheet(config, 2048);
				createSpriteSheet(config, 4096);
			} catch (RuntimeException e) {
				throw new IllegalArgumentException("Exception packing images into spritesheet, ensure the following:\n\n1) You have an img\\ folder in the root" +
						" of your android project.\n\n2) You are running the desktop app with a working directory set to projectRoot\\android\\assets\\.", e);
			}
		}

        atlasPath = config.getAssetImgOutputPath() + "/" + size + "/atlas.atlas";

        manager = new AssetManager();
        manager.load(atlasPath, TextureAtlas.class);
        loadMusic(Lists.newArrayList());
        loadSound(Lists.newArrayList());
        manager.finishLoading();
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

	private AssetManager getManager() {
		return manager;
	}

	public Sprite createSprite(BeelineAssetPath path) {
		return getManager().get(getAtlasPath(), TextureAtlas.class).createSprite(path.getAssetPath());
	}

	public AtlasRegion getRegion(BeelineAssetPath path) {
		return getManager().get(getAtlasPath(), TextureAtlas.class).findRegion(path.getAssetPath());
	}

	public static boolean isColorDark(Color color) {
		double darkness = 1 - (0.299 * color.r + 0.587 * color.g + 0.114 * color.b);
		if (darkness < 0.5) {
			return false; // It's a light color
		} else {
			return true; // It's a dark color
		}
	}

	private void createSpriteSheet(BeelineToolingConfig config, int size) {
		TexturePacker.Settings settings = new TexturePacker.Settings();
		settings.maxWidth = size;
		settings.maxHeight = size;
		settings.fast = false;
		settings.useIndexes = false;
		settings.combineSubdirectories = true;
		TexturePacker.process(
				settings,
				config.getAssetImgSourcePath(),
				config.getAssetImgOutputPath() + "/" + size + "/", "atlas");
	}

    private void createFontPng(BeelineToolingConfig config) {
		BitmapFontWriter.FontInfo info = new BitmapFontWriter.FontInfo();
		info.padding = new BitmapFontWriter.Padding(1, 1, 1, 1);

		FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
		param.size = 49;
		param.gamma = 1f;
		param.renderCount = 199;
		param.characters = Hiero.EXTENDED_CHARS;
		param.mono = true;
		param.packer = new PixmapPacker(512, 512, Pixmap.Format.RGBA8888, 2, false,
		new PixmapPacker.SkylineStrategy());

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.local(config.getFontFileSourcePath()));
		FreeTypeFontGenerator.FreeTypeBitmapFontData data = generator.generateData(param);

		String fontFilePath = "/fonts/font";
		BitmapFontWriter.writePixmaps(
        		param.packer.getPages(),
				Gdx.files.local(config.getAssetImgSourcePath()),
				fontFilePath);
        BitmapFontWriter.writeFont(
		 		data,
				new String[] { config.getAssetImgSourcePath() + fontFilePath + ".png" },
                Gdx.files.local(config.getFontDataFileOutputPath() + "/font"),
                info,
                512, 512);
	}

	private String getAtlasPath() {
		return atlasPath;
	}

}
