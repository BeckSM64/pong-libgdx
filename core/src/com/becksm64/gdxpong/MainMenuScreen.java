package com.becksm64.gdxpong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenuScreen implements Screen {

    final Pong game;
    OrthographicCamera cam;

    public MainMenuScreen(Pong game) {

        this.game = game;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());//Set camera viewport to device screen size
    }

    @Override
    public void render(float delta) {

        //Clear screen before drawing stuff
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update camera
        cam.update();
        game.batch.setProjectionMatrix(cam.combined);

        game.batch.begin();
        game.font.draw(game.batch, "PONG", Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() / 2 + 100);
        game.font.draw(game.batch, "Tap to start", Gdx.graphics.getWidth() / 2 - 300, Gdx.graphics.getHeight() / 2 - 100);
        game.batch.end();

        //When menu is touched, start the game
        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

    }
}
