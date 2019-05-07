package com.becksm64.gdxpong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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
    Label gameTitle;

    //Table stuff
    Table table;

    public MainMenuScreen(Pong game) {

        this.game = game;//Game object

        //Setup camera
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());//Set camera viewport to device screen size

        //True type fonts
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/cour.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 72;
        BitmapFont font72 = generator.generateFont(parameter);

        parameter.size = 150;
        BitmapFont font150 = generator.generateFont(parameter);
        generator.dispose();

        //Table and stage stuff
        stage = new Stage();
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        //table.debug();

        //Create menu buttons and game title
        Gdx.input.setInputProcessor(stage);
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font72;
        startButton = new TextButton("Start", textButtonStyle);
        quitButton = new TextButton("Quit", textButtonStyle);
        Skin testSkin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        gameTitle = new Label("PONG", testSkin);
        gameTitle.setStyle(new Label.LabelStyle(font150, Color.WHITE));

        //Add actors to the table
        table.add(gameTitle);
        table.row();//Next row, essentially puts following table element on a new line
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
