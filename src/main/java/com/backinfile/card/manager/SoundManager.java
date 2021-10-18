package com.backinfile.card.manager;

import com.backinfile.support.reflection.Timing;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
	public static Sound cardMoveSound;

	@Timing("sound init")
	public static void init() {
		cardMoveSound = Gdx.audio.newSound(Gdx.files.internal("sound/card_move.mp3"));
	}

	public static void playCardMove() {
		cardMoveSound.play();
	}
}
