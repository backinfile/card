package com.backinfile.card;

import java.util.HashMap;
import java.util.Map;

import com.backinfile.card.support.FontCharacterCollection;
import com.backinfile.card.support.Timing;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Res {

	public static final String ACTION_DispatchActionString = "调度手牌";
	public static final String ACTION_SaveThreatenAction = "选择一张手牌加入威慑";

	public static final String CARD_ATTACK = "强攻";

	public static final String SKILL_D2D2 = "过牌2";
	public static final String SKILL_PLAY = "打出";

	public static BitmapFont DefaultFontSmallSamll;
	public static BitmapFont DefaultFontSmall;
	public static BitmapFont DefaultFontLarge;
	public static BitmapFont DefaultFont;
	private static FontCharacterCollection fontCharacterCollection = new FontCharacterCollection();
	private static Map<String, TextureRegionDrawable> textureMap = new HashMap<>();

	public static void init() {
		initText();
		initFont();
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
