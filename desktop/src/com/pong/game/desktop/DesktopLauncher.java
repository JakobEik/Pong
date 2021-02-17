package com.pong.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pong.game.PongGame;

public class DesktopLauncher {
	// Singleton
	private static final PongGame pong = PongGame.getInstance();
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = pong.getTitle();
		config.width = pong.getWidth();
		config.height = pong.getHeight();
		config.backgroundFPS = pong.getFPS();
		config.foregroundFPS = pong.getFPS();

		new LwjglApplication(pong, config);
	}
}
