package com.pong.game.bodies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.pong.game.PongGame;
import com.pong.game.states.PlayState;
import com.pong.game.utilities.BodyBuilder;
import com.pong.game.utilities.Constants;
import com.pong.game.utilities.ContactType;

public class Ball {

    // Singleton
    private static final PongGame pong = PongGame.getInstance();

    // Fields
    private float x, y, lastY;
    private final Body body;
    private int velocityY, velocityX;
    private boolean isLeft;

    // Ball vars
    public static final int width = 65;
    public static final int height = 65;
    private static final Texture ballLeft = new Texture("ballLeft1.png");
    private static final Texture ballRight = new Texture("ballRight1.png");
    private Texture texture;
    private float speed = 6f;

    public Ball(PlayState playState){
        this.x = pong.getWidth() / 2f - width / 2f;
        this.y = pong.getHeight() / 2f - height / 2f;
        this.lastY = this.y;
        this.body = BodyBuilder.createBody(x, y, width, height, playState.getWorld(), false, ContactType.BALL, 0.1f);

        boolean isLeft = isDirectionLeft();
        texture = isLeft ? ballLeft : ballRight;
        this.isLeft = isLeft;

        velocityX = isLeft ? -1 : 1;
        velocityY = isDirectionUp() ? 1 : -1;

    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    private void setIsLeft(boolean isLeft) {
        this.isLeft = isLeft;
    }

    private void setSpeed(int speed) {
        this.speed = speed;
    }

    private boolean isDirectionLeft(){
        return Math.random() < 0.5;
    }

    private boolean isDirectionUp(){
        return Math.random() < 0.5;
    }

    /*
    Doubles the speed of the ball if it hits the edge of a paddle
     */
    public void hitPaddleEdge(){
        speed *= 2f;
    }

    public void hitPaddle(){
        velocityX *= -1;
        setIsLeft(velocityX < 0);
        speed += 1;
    }

    public void reverseVelocityY(){
        velocityY *= -1;
    }



    public void update(){
        lastY = y;
        x = body.getPosition().x * Constants.PPM - width / 2f;
        y = body.getPosition().y * Constants.PPM - height / 2f;

        // So the ball doesnt get stuck at the same y
        velocityY = lastY == y ? velocityY *= -1 : velocityY;

        setIsLeft(velocityX < 0);
        texture = isLeft ? ballLeft : ballRight;

        body.setLinearVelocity(velocityX * speed, velocityY * speed);

    }

    public void reset(){
        setSpeed(6);
        setIsLeft(isDirectionLeft());
        texture = isLeft ? ballLeft : ballRight;
        velocityX = isLeft ? -1 : 1;
        velocityY = isDirectionUp() ? 1 : -1;
        body.setTransform(pong.getWidth() / 2f / Constants.PPM, pong.getHeight() / 2f / Constants.PPM, 0);

    }

    public void render(SpriteBatch batch){
        batch.draw(texture, x, y, width, height);
    }

    public void dispose(){
        texture.dispose();
    }
}
