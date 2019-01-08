package org.beelinelibgdx.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.tools.bmfont.BitmapFontWriter;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.google.common.collect.Lists;

import org.beelinelibgdx.exception.BeelineMissingAssetRuntimeException;
import org.beelinelibgdx.tooling.BeelineToolingConfig;

import java.util.List;

public class BeelineAssetManager {

    private static final String FONT_FILE_PATH = "fonts/font";
    private String atlasPath;
	private Skin skin;
	private AssetManager manager;
	private Preferences preferences;
    private BitmapFont font;
    private BeelineToolingConfig config;

    public BeelineAssetManager(BeelineToolingConfig config) {
        this.config = config;
	}

	public final void load() {
		int size;
		if (GL20.GL_MAX_TEXTURE_SIZE == 2048) {
			size = 2048;
		} else {
			size = 4096;
		}

		if (config.shouldGenerateFontData()) {
			createFontPng(config);
		}

		if (config.shouldGenerateSpriteSheet()) {
			try {
				createSpriteSheet(config, 2048);
				createSpriteSheet(config, 4096);
			} catch (RuntimeException e) {
				throw new IllegalArgumentException("Exception packing images into sprite sheet, ensure the following:\n\n1) You have an img\\ folder in the root" +
						" of your android project.\n\n2) You are running the desktop app with a working directory set to projectRoot\\android\\assets\\.", e);
			}
		}

		atlasPath = config.getImgOutputDirectoryPath() + "/" + size + "/atlas.atlas";

		manager = new AssetManager();
		manager.load(atlasPath, TextureAtlas.class);
		loadMusic(getMusics());
		loadSound(getSounds());
		try {
			manager.finishLoading();
		} catch (GdxRuntimeException e) {
			throw new IllegalStateException("Could not load assets, is the working directory for the desktop application set to android/assets?", e);
		}

		skin = new Skin();
		skin.add("default", getFont(), BitmapFont.class);

		Label.LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = getFont();
		skin.add("default", labelStyle, Label.LabelStyle.class);

		afterLoad();
	}

	protected void afterLoad() {

	}

	protected List<BeelineAssetPath> getMusics() {
		return Lists.newArrayList();
	}

	protected List<BeelineAssetPath> getSounds() {
		return Lists.newArrayList();
	}

	public void loadMusic(List<BeelineAssetPath> paths) {
    	loadAsset(paths, Music.class);
	}

	public void loadSound(List<BeelineAssetPath> paths) {
    	loadAsset(paths, Sound.class);
	}

	private void loadAsset(List<BeelineAssetPath> paths, Class clazz) {
		for (BeelineAssetPath path : paths) {
			manager.load(path.getAssetPath(), clazz);
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

	protected AssetManager getManager() {
		return manager;
	}

	public Sprite createSprite(BeelineAssetPath path) {
		return createSprite(path, 1);
	}

	protected Sprite createSprite(BeelineAssetPath path, int scale) {
        Sprite sprite = getTextureAtlas().createSprite(path.getAssetPath());
        if (sprite == null) {
            throw new BeelineMissingAssetRuntimeException(path.getAssetPath(), getManager());
        }
        sprite.scale(scale);
        return sprite;
	}

	public AtlasRegion getRegion(BeelineAssetPath path) {
		return getTextureAtlas().findRegion(path.getAssetPath());
	}

	public TextureAtlas getTextureAtlas() {
		return getManager().get(getAtlasPath(), TextureAtlas.class);
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
				config.getImgSourceDirectoryPath(),
				config.getImgOutputDirectoryPath() + "/" + size + "/", "atlas");
	}

    private void createFontPng(BeelineToolingConfig config) {
		BitmapFontWriter.FontInfo info = new BitmapFontWriter.FontInfo();
		info.padding = new BitmapFontWriter.Padding(1, 1, 1, 1);

		FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
		param.size = 60;
		param.gamma = 1f;
		param.renderCount = 199;
//		param.characters = Hiero.EXTENDED_CHARS;
		param.mono = false;
		param.packer = new PixmapPacker(1024, 1024, Pixmap.Format.RGBA8888, 2, false,
		new PixmapPacker.SkylineStrategy());

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.local(config.getFontSourceFilePath()));
		FreeTypeFontGenerator.FreeTypeBitmapFontData data = generator.generateData(param);

		BitmapFontWriter.writePixmaps(
        		param.packer.getPages(),
				Gdx.files.local(config.getImgSourceDirectoryPath()),
				FONT_FILE_PATH);
        BitmapFontWriter.writeFont(
		 		data,
				new String[] { config.getImgSourceDirectoryPath() + FONT_FILE_PATH + ".png" },
                Gdx.files.local(getFontSpriteFilePath()),
                info,
                512, 512);
	}

    private String getFontSpriteFilePath() {
        return config.getFontDataOutputFilePath() + "/font";
    }

    public BitmapFont getFont() {
        if (font == null) {
            Sprite fontSprite = createSprite(() -> FONT_FILE_PATH);
            font = new BitmapFont(Gdx.files.internal(getFontSpriteFilePath()), fontSprite);
            // font.getData().setScale(1);
            font.getData().setLineHeight(60);
            font.getData().setScale(1f);
            for (BitmapFont.Glyph[] glyphArray : font.getData().glyphs) {
                if (glyphArray != null) {
                    for (BitmapFont.Glyph glyph : glyphArray) {
                        if (glyph != null) {
                            glyph.xadvance -= 4;
                        }
                    }
                }
            }
        }
        return font;
    }

	private String getAtlasPath() {
		return atlasPath;
	}
	
    public TextButton.TextButtonStyle getActorStyle(BeelineAssetPath path) {
        return getActorStyle(path, path, path, path, null, null, null, null, Color.WHITE, Color.WHITE,
                Color.WHITE, 0, 0, 0, 0);
    }

    public TextButton.TextButtonStyle getActorStyle(BeelineAssetPath path, int border) {
        return getActorStyle(path, path, path, path, null, null, null, null, Color.WHITE, Color.WHITE,
                Color.WHITE, border, border, border, border);
    }

    public TextButton.TextButtonStyle getActorStyle(BeelineAssetPath path, int left, int right, int top, int bottom) {
        return getActorStyle(path, path, path, path, null, null, null, null, Color.WHITE, Color.WHITE,
                Color.WHITE, left, right, top, bottom);
    }

    public TextButton.TextButtonStyle getActorStyle(BeelineAssetPath upTexture, BeelineAssetPath downTexture, BeelineAssetPath disabledTexture,
													 BeelineAssetPath checkedTexture, Color upColor, Color downColor, Color disabledColor, Color checkedColor,
													 Color fontColor, Color checkedFontColor, Color disabledFontColor, int left, int right, int top,
													 int bottom) {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = getFont();
        {
            NinePatch ninePatch = getNinePatch(upTexture, left, right, top, bottom);
            if (upColor != null) {
                ninePatch.setColor(upColor);
            }
            textButtonStyle.up = getNinePatchDrawable(ninePatch);
        }
        {
            NinePatch ninePatch = getNinePatch(downTexture, left, right, top, bottom);
            if (downColor != null) {
                ninePatch.setColor(downColor);
            }
            textButtonStyle.down = getNinePatchDrawable(ninePatch);
        }
        {
            NinePatch ninePatch = getNinePatch(disabledTexture, left, right, top, bottom);
            if (disabledColor != null) {
                ninePatch.setColor(disabledColor);
            }
            textButtonStyle.disabled = getNinePatchDrawable(ninePatch);
        }
        {
            NinePatch ninePatch = getNinePatch(checkedTexture, left, right, top, bottom);
            if (checkedColor != null) {
                ninePatch.setColor(checkedColor);
            }
            textButtonStyle.checked = getNinePatchDrawable(ninePatch);
            textButtonStyle.checkedFontColor = checkedFontColor;
        }
        textButtonStyle.fontColor = fontColor;
        textButtonStyle.disabledFontColor = disabledFontColor;
        return textButtonStyle;
    }

    protected NinePatch getNinePatch(BeelineAssetPath te, int left, int right, int top, int bottom) {
        TextureRegion tex = getTextureAtlas().findRegion(te.getAssetPath());
        return new NinePatch(tex, left, right, top, bottom);
    }

    protected NinePatchDrawable getNinePatchDrawable(NinePatch t) {
		NinePatchDrawable d = new NinePatchDrawable(t);
        return d;
    }


	public Skin getSkin() {
		return skin;
	}

	public BeelineToolingConfig getConfig() {
		return config;
	}
}
