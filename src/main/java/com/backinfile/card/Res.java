package com.backinfile.card;

import java.util.HashMap;
import java.util.Map;

import com.backinfile.support.FontCharacterCollection;
import com.backinfile.support.Timing;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Res {

	public static final String PATH_IMAGE_BACKGROUND = "image/background1.jpg";
	public static final String PATH_IMAGE_CARD1 = "image/background1.jpg";

	public static final String ACTION_DispatchActionString = "调度手牌";
	public static final String ACTION_SaveThreatenAction = "选择一张手牌加入威慑";

	public static final String CARD_ATTACK = "强攻";

	public static final String SKILL_D2D2 = "过牌2";
	public static final String SKILL_PLAY = "打出";
	public static final String CARD_AID_OCCUPY = "侵占";
	public static final String CARD_AID_STORE = "储备";
	public static final String CARD_AID_CHARGE = "充能";
	public static final String TARGET_SELECT_TO_OCCUPY = "选择一处位置侵占";

	public static BitmapFont DefaultFontSmallSamll;
	public static BitmapFont DefaultFontSmall;
	public static BitmapFont DefaultFontLarge;
	public static BitmapFont DefaultFont;
	private static FontCharacterCollection fontCharacterCollection = new FontCharacterCollection();
	private static Map<String, TextureRegionDrawable> textureMap = new HashMap<>();

	public static void init() {
		initImage();
		initText();
		initFont();
	}

	public static TextureRegionDrawable getTexture(String path) {
		return textureMap.getOrDefault(path, new TextureRegionDrawable());
	}

	private static void initImage() {
		textureMap.put(PATH_IMAGE_BACKGROUND, new TextureRegionDrawable(new Texture(PATH_IMAGE_BACKGROUND)));
	}

	public static void dispose() {
		for (var texture : textureMap.values()) {
			texture.getRegion().getTexture().dispose();
		}
	}

	@Timing
	private static void initText() {
		fontCharacterCollection.put(ACTION_DispatchActionString);
		fontCharacterCollection.put(ACTION_SaveThreatenAction);
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
