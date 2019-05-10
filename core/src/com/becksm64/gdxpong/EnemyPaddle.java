package com.becksm64.gdxpong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class EnemyPaddle {

    public static final int WIDTH = 50;
    public static final int HEIGHT = 150;

    public Vector3 position;
    private Vector3 velocity;
    public ShapeRenderer shape;
    private Rectangle paddleBounds;
    public int score;
    private int paddleSpeed;

    public EnemyPaddle(int x, int y, int paddleSpeed) {

        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        shape = new ShapeRenderer();
        paddleBounds = new Rectangle(x, y, WIDTH, HEIGHT);
        score = 0;
        this.paddleSpeed = paddleSpeed;
    }

    /*
     * Updates stuff that needs updating like bounds and position
     */
    public void update() {

        constrainPaddle();
        setBounds(position.x, position.y);//Update paddle bounds to match current position
        position.add(0, velocity.y, 0);//Changes ball position based on velocity, only y position will ever change
        move();
    }

    /*
     * Move the enemy paddle towards the ball to attempt to hit it
     */
    private void move() {

        //Only move if ball is coming toward enemy (Enemy is always located on right side of screen)
        if(GameScreen.ball.getVelocity().x > 0) {

            //Check position of paddle in relation to ball and move accordingly
            if(position.y < GameScreen.ball.getPosition().y) {
                velocity.y = paddleSpeed;
            } else {
                velocity.y = -paddleSpeed;
            }
        } else {
            velocity.y = 0;//Stop moving when the ball isn't coming towards enemy
        }
    }

    /*
     * Keeps ball from leaving the top and bottom of screen
     */
    private void constrainPaddle() {

        //Check for collision with ceiling and floor
        if(position.y + HEIGHT >= Gdx.graphics.getHeight()) {
            position.y = Gdx.graphics.getHeight() - (HEIGHT + 10);
        }

        if(position.y + HEIGHT <= 0) {
            position.y = (HEIGHT + 10);
        }
    }

    /*
     * Gets the enemy score
     */
    public int getScore() {
        return this.score;
    }

    /*
     * Sets the position of the bounds of the paddle
     */
    private void setBounds(float x, float y) {
        this.paddleBounds.setPosition(x, y);
    }

    /*
     * Returns the bounds of the player paddle
     */
    public Rectangle getBounds() {
        return this.paddleBounds;
    }
}
