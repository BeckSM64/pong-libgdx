package com.becksm64.gdxpong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

    Stage stage;

    //Menu buttons
    TextButton quitButton;
    TextButton easyButton;
    TextButton mediumButton;
    TextButton hardButton;
    TextButton.TextButtonStyle textButtonStyle;

    Label gameTitle;

    //Table to organize menu screen
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
        //table.debug();//Draws lines to show layout of table

        //Create menu buttons and game title
        Gdx.input.setInputProcessor(stage);
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font72;
        quitButton = new TextButton("Quit", textButtonStyle);
        easyButton = new TextButton("Easy", textButtonStyle);
        mediumButton = new TextButton("Medium", textButtonStyle);
        hardButton = new TextButton("Hard", textButtonStyle);
        Skin testSkin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        gameTitle = new Label("PONG", testSkin);
        gameTitle.setStyle(new Label.LabelStyle(font150, Color.WHITE));

        //Add buttons to the table
        table.add(gameTitle).colspan(3);
        table.row();//Next row, essentially puts following table element on a new line
        table.row();
        table.add(easyButton);//Take up full width of screen
        table.add(mediumButton).pad(100);
        table.add(hardButton);
        table.row();
        table.add(quitButton).colspan(3);
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

        //Listener for the quit button that handles quitting the app
        quitButton.addListener(new ChangeListener() {

            @Override
            public void changed (ChangeEvent event, Actor actor) {

                dispose();
                game.dispose();
            }
        });

        //Listener for easy button, starts game with low ball speed
        easyButton.addListener(new ChangeListener() {

            @Override
            public void changed (ChangeEvent event, Actor actor) {

                game.setScreen(new GameScreen(game, 10, 5, 11));
                dispose();
            }
        });

        //Listener for medium button, starts game with medium ball speed
        mediumButton.addListener(new ChangeListener() {

            @Override
            public void changed (ChangeEvent event, Actor actor) {

                game.setScreen(new GameScreen(game, 30, 10, 11));
                dispose();
            }
        });

        //Listener for hard button, starts game with fast ball speed
        hardButton.addListener(new ChangeListener() {

            @Override
            public void changed (ChangeEvent event, Actor actor) {

                game.setScreen(new GameScreen(game, 40, 12, 30));
                dispose();
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
