package com.becksm64.gdxpong;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Paddle {

    public static final int WIDTH = 50;
    public static final int HEIGHT = 150;

    public Vector3 position;
    private Vector3 velocity;
    public ShapeRenderer shape;
    private Rectangle paddleBounds;
    public int score;

    public Paddle(int x, int y) {

        position = new Vector3(x, y, 0);
        velocity = new Vector3(15,15,0);
        shape = new ShapeRenderer();
        paddleBounds = new Rectangle(x, y, WIDTH, HEIGHT);
        score = 0;
    }

    /*
     * Updates stuff that needs updating like bounds
     */
    public void update() {
        setBounds(position.x, position.y);//Update paddle bounds to match current position
    }

    /*
     * Gets the player score
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
