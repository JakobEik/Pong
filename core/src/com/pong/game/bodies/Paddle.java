package com.pong.game.bodies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.pong.game.states.PlayState;
import com.pong.game.utilities.BodyBuilder;
import com.pong.game.utilities.Constants;
import com.pong.game.utilities.ContactType;

public class Paddle {

    // Fields
    private float x, y;
    private final Body body;
    private int velocityY;

    // Paddle vars
    public static final int width = 45;
    public static final int height = 80;
    private static final Texture texture = new Texture("paddle.png");
    private static final int speed = 15;
    private int score = 0;



    public Paddle(float x, float y, PlayState playState) {
        this.x = x;
        this.y = y;
        this.body = BodyBuilder.createBody(x, y, width, height, playState.getWorld(),
                false, ContactType.PLAYER, 10000);

        velocityY = 0;


    }

    public float getX() {
        return x;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        this.score++;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public void update(){
        x = body.getPosition().x * Constants.PPM - width / 2f;
        y = body.getPosition().y * Constants.PPM - height / 2f;

        body.setLinearVelocity(0, velocityY * speed);

    }

    public void render(SpriteBatch batch){
        batch.draw(texture, x, y, width, height);
    }

    public void dispose(){
        texture.dispose();
    }
}
