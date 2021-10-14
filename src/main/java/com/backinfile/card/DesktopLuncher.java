package com.backinfile.card;

import com.backinfile.card.view.stage.MainGame;
import com.backinfile.support.reflection.Reflections;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLuncher {
	public static void main(String[] args) {

		if (Settings.DEV) {
			Reflections.classRewriteInit(Settings.PACKAGE_NAME);
		}

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setResizable(false);
		if (Settings.FULL_SCREEN) {
			config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
		} else {
			config.setWindowedMode(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
		}
		new Lwjgl3Application(new MainGame(), config);
	}
}