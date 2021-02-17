package com.pong.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.pong.game.PongGame;

public class AndroidLauncher extends AndroidApplication {
	// Singleton
	private static final PongGame pong = PongGame.getInstance();
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(pong, config);
	}
}
