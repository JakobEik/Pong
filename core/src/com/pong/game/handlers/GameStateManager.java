package com.pong.game.handlers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pong.game.states.State;

import java.util.Stack;

public class GameStateManager {

    private final Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }
    public void push(State state) {
        states.push(state);
    }

    public void set(State state) {
        states.pop();
        states.push(state);
    }
    public void update() {
        states.peek().update();
    }
    public void render(SpriteBatch sb) {
        states.peek().render(sb);
    }
}
