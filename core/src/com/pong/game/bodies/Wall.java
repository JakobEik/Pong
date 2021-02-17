package com.pong.game.bodies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.pong.game.PongGame;
import com.pong.game.states.PlayState;
import com.pong.game.utilities.BodyBuilder;
import com.pong.game.utilities.ContactType;

public class Wall {

    // Singleton
    private static final PongGame pong = PongGame.getInstance();

    private Body body;
    private final float x;
    private float y;
    private final int width;
    private final int height;
    private final Texture texture = new Texture("white.png");

    public Wall(float y, PlayState playState) {
        this.y = y;
        this.x = pong.getWidth() / 2f;
        this.width = pong.getWidth();
        this.height = 30;
        this.body = BodyBuilder.createBody(x, y, width, height, playState.getWorld(), true, ContactType.WALL, 1f);
    }



    public void render(SpriteBatch batch){
        batch.draw(texture, x - width / 2f, y - height / 2f, width, height);
    }


    public void dispose(){
        texture.dispose();
    }
}
