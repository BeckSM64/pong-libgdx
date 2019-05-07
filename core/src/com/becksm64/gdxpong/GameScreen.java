package com.becksm64.gdxpong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;

import java.util.Random;

public class GameScreen implements Screen {

    final Pong game;

    SpriteBatch batch;

    //Pong objects
    private Paddle player;
    private EnemyPaddle enemy;
    public static Ball ball;
    private Vector3 touchPos;
    private OrthographicCamera cam;
    private ShapeRenderer courtBorder;
    private String playerScoreDisplay;
    private String enemyScoreDisplay;
    private BitmapFont bitmapFont;
    private BitmapFont font72;
    private Random rand;
    private boolean playerScored;

    public GameScreen(Pong game) {

        this.game = game;

        batch = new SpriteBatch();

        //Game Objects
        player = new Paddle(10, 10);
        enemy = new EnemyPaddle(10, 10);
        enemy.position.x = Gdx.graphics.getWidth() - (enemy.WIDTH + 10);
        ball = new Ball(100, 100);

        //Camera and touch position
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        touchPos = new Vector3(10,10,0);

        //Draw court
        courtBorder = new ShapeRenderer();

        //Scores and Bitmap fonts
        playerScoreDisplay = "0";
        enemyScoreDisplay = "0";
        bitmapFont = new BitmapFont();

        rand = new Random();
        playerScored = true;

        //Freetype fonts
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/cour.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 72;
        font72 = generator.generateFont(parameter);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        //Clears the screen before new stuff is drawn (Give it a random color just for fun)
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update ball and paddle positions
        cam.update();
        player.update();
        enemy.update();
        ball.update();

        //If screen is touched, move paddle to that location for y value only
        if(Gdx.input.isTouched()) {

            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touchPos);
            player.position.y = touchPos.y;
        }

        //Draw scores
        batch.begin();
        font72.setColor(Color.BLACK);
        //bitmapFont.getData().setScale(10, 10);
        font72.draw(batch, Integer.toString(player.getScore()), Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() - 20);
        font72.draw(batch, Integer.toString(enemy.getScore()), Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() / 4), Gdx.graphics.getHeight() - 20);
        batch.end();

        //Draw the court
        courtBorder.begin(ShapeRenderer.ShapeType.Filled);
        courtBorder.setColor(Color.BLACK);
        courtBorder.rect((Gdx.graphics.getWidth() / 2) - 5, 0, 10, Gdx.graphics.getHeight());
        courtBorder.end();

        //Draw the ball
        ball.shape.begin(ShapeRenderer.ShapeType.Filled);
        //ball.shape.setColor(new Color(rand.nextInt(5), rand.nextInt(5), rand.nextInt(5), 1));
        ball.shape.setColor(Color.BLACK);
        ball.shape.rect(ball.position.x, ball.position.y, ball.WIDTH, ball.HEIGHT);
        ball.shape.end();

        //Draw the player paddle
        player.shape.begin(ShapeRenderer.ShapeType.Filled);
        player.shape.setColor(Color.BLACK);
        player.shape.rect(10, player.position.y, player.WIDTH, player.HEIGHT);
        player.shape.end();

        //Draw the enemy paddle
        enemy.shape.begin(ShapeRenderer.ShapeType.Filled);
        enemy.shape.setColor(Color.BLACK);
        enemy.shape.rect(enemy.position.x, enemy.position.y, enemy.WIDTH, enemy.HEIGHT);
        enemy.shape.end();

        //Check for ball and paddle collision
        if(player.getBounds().overlaps(ball.getBounds()) || enemy.getBounds().overlaps(ball.getBounds())) {
            ball.getVelocity().x *= -1;//Change the direction of the ball if it hits a paddle
        }

        //Check if ball is still on screen
        if(!ball.isOnScreen()) {

            //Give point to player who scored
            if(ball.position.x > Gdx.graphics.getWidth()) {
                player.score++;
                playerScored = true;
            } else if(ball.position.x <0){
                enemy.score++;
                playerScored = false;
            }

            //Put ball back in the middle of the court
            ball.position.x = (Gdx.graphics.getWidth() / 2) - (ball.WIDTH / 2);
            ball.position.y = (Gdx.graphics.getHeight() / 2) - (ball.HEIGHT / 2);
            ball.getVelocity().x = 0;
            ball.getVelocity().y = 0;

            //Set a delay when the ball is put back into the middle of the court
            float delay = 1;//Delay in seconds
            Timer.schedule(new Timer.Task(){
                @Override
                public void run() {

                    //Give ball velocity after delay
                    ball.getVelocity().x = 25;
                    ball.getVelocity().y = 12;

                    if(!playerScored) {

                        //Make ball go towards enemy if they scored
                        ball.getVelocity().x *= -1;
                        ball.getVelocity().y *= -1;
                    }
                }
            }, delay);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        ball.shape.dispose();
        player.shape.dispose();
        bitmapFont.dispose();
        batch.dispose();
    }
}
