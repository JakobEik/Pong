package com.pong.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.pong.game.PongGame;
import com.pong.game.bodies.Ball;
import com.pong.game.bodies.Paddle;
import com.pong.game.bodies.Wall;
import com.pong.game.handlers.GameStateManager;
import com.pong.game.handlers.WorldContactListener;


public class PlayState extends State {

    // Singleton
    private static final PongGame pong = PongGame.getInstance();

    private final SpriteBatch batch = new SpriteBatch();
    private final World world = new World(new Vector2(0, 0), false);
    private final BitmapFont scoreLeft = new BitmapFont();
    private final BitmapFont scoreRight = new BitmapFont();
    private final BitmapFont test = new BitmapFont();

    // Objects
    private final Paddle playerLeft;
    private final Paddle playerRight;
    private final Ball ball;
    private final Wall wallTop;
    private final Wall wallBottom;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        camera.position.set(new Vector3(pong.getHeight() / 2f, pong.getWidth() / 2f, 0));


        playerLeft = new Paddle(pong.getWidth() / 10f,
                pong.getWidth() / 2f - Paddle.height / 2f, this);
        playerRight = new Paddle(pong.getWidth() - pong.getWidth() / 10f,
                pong.getHeight() / 2f - Paddle.height / 2f, this);

        ball = new Ball(this);

        wallTop = new Wall(pong.getHeight() - 15, this);
        wallBottom = new Wall(15, this);

        WorldContactListener contactListener = new WorldContactListener(this);
        world.setContactListener(contactListener);


    }

    public World getWorld() {
        return world;
    }

    public Ball getBall() {
        return ball;
    }

    public Paddle getPlayerLeft() {
        return playerLeft;
    }

    public Paddle getPlayerRight() {
        return playerRight;
    }

    private void handlePlayerLeftInput(){
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            playerLeft.setVelocityY(1);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S)){
            playerLeft.setVelocityY(-1);
        }
        else {
            playerLeft.setVelocityY(0);
        }
    }

    private void handlePlayerRightInput(){
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            playerRight.setVelocityY(1);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            playerRight.setVelocityY(-1);
        }
        else {
            playerRight.setVelocityY(0);
        }
    }

    private void handleGoal(){
        // Goal to right
        if (ball.getX() < -Ball.width){
            playerRight.increaseScore();
            ball.reset();
        }
        // Goal to left
        if (ball.getX() > pong.getWidth()){
            playerLeft.increaseScore();
            ball.reset();
        }
    }

    public void update(){
        world.step(1f/ pong.getFPS(), 6, 2);
        batch.setProjectionMatrix(camera.combined);

        handlePlayerLeftInput();
        handlePlayerRightInput();

        playerRight.update();
        playerLeft.update();
        ball.update();

        handleGoal();

    }

    @Override
    public void render(SpriteBatch batch){

        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        playerLeft.render(batch);
        playerRight.render(batch);
        ball.render(batch);
        wallTop.render(batch);
        wallBottom.render(batch);
        scoreLeft.draw(batch, String.valueOf(playerLeft.getScore()), pong.getWidth() / 5f, pong.getHeight() * 9/10f);
        scoreRight.draw(batch, String.valueOf(playerRight.getScore()), pong.getWidth() * 4/5f, pong.getHeight() * 9/10f);
        test.draw(batch, String.valueOf(ball.getY()), 20, 80);
        batch.end();

    }

    @Override
    public void dispose(){
        batch.dispose();
        world.dispose();
        playerRight.dispose();
        playerLeft.dispose();
        ball.dispose();
        wallTop.dispose();
        wallBottom.dispose();
        scoreRight.dispose();
        scoreLeft.dispose();
    }
}
