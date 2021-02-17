package com.pong.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pong.game.handlers.GameStateManager;


public abstract class State {
    protected OrthographicCamera camera;
    protected GameStateManager gsm;

    protected State(GameStateManager gsm) {
        this.gsm = gsm;
        camera = new OrthographicCamera();


    }


    public abstract void update();
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();


}