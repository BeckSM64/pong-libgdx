package com.becksm64.gdxpong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Ball {

    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;

    public Vector3 position;
    private Vector3 velocity;
    public ShapeRenderer shape;
    private Rectangle ballBounds;

    public Ball(int x, int y) {

        position = new Vector3(x, y, 0);
        velocity = new Vector3(25,12,0);
        shape = new ShapeRenderer();
        ballBounds = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    /*
     * Updates stuff that needs updating such as position, bounds, and also checks for collision with walls
     */
    public void update() {

        position.add(velocity.x, velocity.y, 0);//Changes ball position based on velocity
        setBounds(position.x, position.y);//Set the bounds to the new location of the ball
        constrainBall();//Collision detection with walls
    }

    /*
     * Keeps ball from leaving the top and bottom of screen
     */
    private void constrainBall() {

        //Check for collision with ceiling and floor
        if(position.y >= Gdx.graphics.getHeight() || position.y <= 0) {
            velocity.y *= -1;
        }
    }

    /*
     * Returns true if the ball is still on the screen
     */
    public boolean isOnScreen() {

        if(position.x > Gdx.graphics.getWidth() || position.x < 0) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * Sets the position of the bounds of the ball
     */
    private void setBounds(float x, float y) {
        this.ballBounds.setPosition(x, y);
    }

    /*
     * Returns the bounds of the ball
     */
    public Rectangle getBounds() {
        return this.ballBounds;
    }

    /*
     * Returns the velocity of the ball as a Vector3
     */
    public Vector3 getVelocity() {
        return this.velocity;
    }

    /*
     * Returns the position of the ball as a Vector3
     */
    public Vector3 getPosition() {
        return this.position;
    }
}
