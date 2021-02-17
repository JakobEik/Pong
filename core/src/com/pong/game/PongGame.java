package com.pong.game;

import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pong.game.handlers.GameStateManager;
import com.pong.game.states.PlayState;


public class PongGame extends ApplicationAdapter {

	// Singleton
	private static final PongGame INSTANCE = new PongGame();

	private GameStateManager gsm;
	private SpriteBatch batch;

	// Game Vars
	private static final String TITLE = "Pong";
	private static final int WIDTH = 1200;
	private static final int HEIGHT = 720;
	private static final int FPS = 60;

	private PongGame() {}

	// Singleton
	public static PongGame getInstance() {
		return INSTANCE;
	}

	public String getTitle() {
		return TITLE;
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public int getFPS() {
		return FPS;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		gsm.push(new PlayState(gsm));
	}

	@Override
	public void render () {
		super.render();

		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
		}

		gsm.update();
		gsm.render(batch);

	}

	@Override
	public void dispose () {
		super.dispose();

	}


}
