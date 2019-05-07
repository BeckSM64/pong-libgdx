package com.becksm64.gdxpong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MainMenuScreen implements Screen {

    final Pong game;
    OrthographicCamera cam;

    //Testing menu button stuff
    Stage stage;
    TextButton startButton;
    TextButton quitButton;
    TextButton.TextButtonStyle textButtonStyle;
    //Label gameTitle;

    //Table stuff
    Table table;

    public MainMenuScreen(Pong game) {

        this.game = game;//Game object

        //Setup camera
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());//Set camera viewport to device screen size

        //Table and stage stuff
        stage = new Stage();
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.debug();

        //Create menu buttons and game title
        Gdx.input.setInputProcessor(stage);
        game.font = new BitmapFont();
        game.font.getData().setScale(5,5);
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = game.font;
        startButton = new TextButton("Start", textButtonStyle);
        quitButton = new TextButton("Quit", textButtonStyle);
        //gameTitle = new Label("PONG", new Skin());

        //Add actors to the table
        //table.add(gameTitle);
        //table.row();//Next row, essentially puts following table element on a new line
        table.add(startButton);
        table.row();
        table.add(quitButton);
    }

    @Override
    public void render(float delta) {

        //Clear screen before drawing stuff
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        game.batch.setProjectionMatrix(cam.combined);
        stage.draw();
    }

    @Override
    public void show() {

        //Listener for start button that handles starting the game
        startButton.addListener(new ChangeListener() {

            @Override
            public void changed (ChangeEvent event, Actor actor) {

                game.setScreen(new GameScreen(game));
                dispose();
            }
        });

        //Listener for the quit button that handles quitting the app
        quitButton.addListener(new ChangeListener() {

            @Override
            public void changed (ChangeEvent event, Actor actor) {

                dispose();
                game.dispose();
            }
        });
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
        stage.dispose();
    }
}
