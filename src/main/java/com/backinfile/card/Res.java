package com.backinfile.card;

import java.util.HashMap;
import java.util.Map;

import com.backinfile.card.model.LocalString;
import com.backinfile.card.model.LocalString.LocalImagePathString;
import com.backinfile.support.FontCharacterCollection;
import com.backinfile.support.Timing;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Res {
	public static final TextureRegionDrawable EMPTY_DRAWABLE = new TextureRegionDrawable();

	public static float CARD_WIDTH;
	public static float CARD_HEIGHT;
	public static float CARD_WIDTH_L;
	public static float CARD_HEIGHT_L;
	public static float CARD_WIDTH_LL;
	public static float CARD_HEIGHT_LL;
	public static float CARD_WIDTH_S;
	public static float CARD_HEIGHT_S;

	public static TextureRegionDrawable TEX_BLACK;
	public static TextureRegionDrawable TEX_WHITE;
	public static TextureRegionDrawable TEX_GRAY;
	public static TextureRegionDrawable TEX_DARK_GRAY;
	public static TextureRegionDrawable TEX_LIGHT_GRAY;
	public static TextureRegionDrawable TEX_HALF_BLACK;
	public static final String PATH_IMAGE_BACKGROUND = "image/background1.jpg";

	public static BitmapFont DefaultFontSmallSamll;
	public static BitmapFont DefaultFontSmall;
	public static BitmapFont DefaultFontLarge;
	public static BitmapFont DefaultFont;
	private static FontCharacterCollection fontCharacterCollection = new FontCharacterCollection();
	private static Map<String, Texture> textureMap = new HashMap<>(); // path->image
	private static Map<String, TextureRegionDrawable> textureDrawableMap = new HashMap<>();
	private static Map<LocalImagePathString, TextureRegionDrawable> cardImageMap = new HashMap<>();

	@Timing("res init")
	public static void init() {

		CARD_HEIGHT = Gdx.graphics.getHeight() / 3f;
		CARD_WIDTH = CARD_HEIGHT * 0.715f;
		CARD_HEIGHT_L = CARD_HEIGHT * 4 / 3f;
		CARD_WIDTH_L = CARD_WIDTH * 4 / 3f;
		CARD_HEIGHT_LL = CARD_HEIGHT * 2f;
		CARD_WIDTH_LL = CARD_WIDTH * 2f;
		CARD_HEIGHT_S = CARD_HEIGHT * 3f / 4f;
		CARD_WIDTH_S = CARD_WIDTH * 3f / 4f;

		String localStringConf = Gdx.files.internal("local/zh.json").readString("utf8");
		fontCharacterCollection.put(localStringConf);
		LocalString.init(localStringConf);

		initImage();
		initFont(); // 一定要在所有string加载后执行
	}

	public static TextureRegionDrawable getTexture(String path) {
		var texture = textureMap.getOrDefault(path, null);
		if (texture != null) {
			return new TextureRegionDrawable(texture);
		}
		return EMPTY_DRAWABLE;
	}

	public static TextureRegionDrawable getTexture(LocalImagePathString imagePathString) {
		return cardImageMap.getOrDefault(imagePathString, EMPTY_DRAWABLE);
	}

	@Timing
	private static void initImage() {
		TEX_WHITE = getDrawable(newColorPixmap(8, 8, Color.WHITE));
		TEX_BLACK = getDrawable(newColorPixmap(8, 8, Color.BLACK));
		TEX_GRAY = getDrawable(newColorPixmap(8, 8, Color.GRAY));
		TEX_DARK_GRAY = getDrawable(newColorPixmap(8, 8, Color.DARK_GRAY));
		TEX_LIGHT_GRAY = getDrawable(newColorPixmap(8, 8, Color.LIGHT_GRAY));
		TEX_HALF_BLACK = getDrawable(newColorPixmap(8, 8, new Color(0, 0, 0, 0.5f)));

		textureMap.put(PATH_IMAGE_BACKGROUND, new Texture(PATH_IMAGE_BACKGROUND));

		// 加载所有texture
		for (var imageString : LocalString.getAllImagePathStrings()) {
			String path = imageString.path;
			if (!textureMap.containsKey(path)) {
				textureMap.put(path, new Texture(path));
			}
		}
		// 获取drawable
		for (var path : textureMap.keySet()) {
			textureDrawableMap.put(path, new TextureRegionDrawable(textureMap.get(path)));
		}
		for (var imageString : LocalString.getAllImagePathStrings()) {
			if (cardImageMap.containsKey(imageString)) {
				continue;
			}
			var texture = textureMap.get(imageString.path);
			int[] locate = imageString.locate;
			if (locate != null && locate.length == 4) {
				int dw = texture.getWidth() / locate[0];
				int dh = texture.getHeight() / locate[1];
				var textureRegion = new TextureRegion(texture, dw * locate[2], dh * locate[3], dw, dh);
				cardImageMap.put(imageString, new TextureRegionDrawable(textureRegion));
			} else {
				cardImageMap.put(imageString, new TextureRegionDrawable(texture));
			}
		}
	}

	private static Pixmap newColorPixmap(int width, int height, Color color) {
		Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
		pixmap.setColor(color);
		pixmap.fill();
		return pixmap;
	}

	private static TextureRegionDrawable getDrawable(Pixmap pixmap) {
		Texture texture = new Texture(pixmap, true);
		TextureRegion region = new TextureRegion(texture);
		pixmap.dispose();
		return new TextureRegionDrawable(region);
	}

	public static void dispose() {
		for (var texture : textureMap.values()) {
			texture.dispose();
		}
	}

	@Timing
	private static void initFont() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/msyh.ttc"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		fontCharacterCollection.put(FreeTypeFontGenerator.DEFAULT_CHARS);
		parameter.characters = fontCharacterCollection.getAll();

//		parameter.size = 12;
//		DefaultFontSmallSamll = generator.generateFont(parameter);
//		parameter.size = 16;
//		DefaultFontSmall = generator.generateFont(parameter);
		parameter.size = 20;
		DefaultFont = generator.generateFont(parameter);
//		parameter.size = 24;
//		DefaultFontLarge = generator.generateFont(parameter);
		generator.dispose();
	}
}
