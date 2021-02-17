package com.pong.game.handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.pong.game.bodies.Ball;
import com.pong.game.bodies.Paddle;
import com.pong.game.states.PlayState;
import com.pong.game.utilities.ContactType;

public class WorldContactListener implements ContactListener {

    private final PlayState playState;


    public WorldContactListener(PlayState playState) {
        this.playState = playState;

    }

    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        boolean oneIsBall = a.getUserData() == ContactType.BALL || b.getUserData() == ContactType.BALL;
        boolean oneIsPlayer = a.getUserData() == ContactType.PLAYER || b.getUserData() == ContactType.PLAYER;
        boolean oneIsWall = a.getUserData() == ContactType.WALL || b.getUserData() == ContactType.WALL;

        float pLeftX = playState.getPlayerLeft().getX();
        float pRightX = playState.getPlayerRight().getX();

        Ball ball = playState.getBall();
        float ballX = ball.getX();

        if (oneIsBall) {
            if (oneIsPlayer) {
                // To make sure ball doesnt flip if it hits behind the paddle
                if (ballX > pLeftX && ballX < pRightX){
                    ball.hitPaddle();
                }
                if (ballX < pLeftX + Paddle.width / 2f || ballX > pRightX - Paddle.width / 2f){
                    ball.reverseVelocityY();
                    ball.hitPaddleEdge();
                }
            }
            if (oneIsWall){
                ball.reverseVelocityY();
            }
        }



    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
