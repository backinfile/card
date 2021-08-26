package com.backinfile.card;

import com.backinfile.card.view.MainGame;
import com.backinfile.support.ReflectionUtils;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLuncher {
	public static void main(String[] args) {

		if (Settings.DEV) {
			ReflectionUtils.initTimingMethod(Settings.PackageName);
		}

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
		config.setResizable(false);
		new Lwjgl3Application(new MainGame(), config);
	}
}