package com.becksm64.gdxpong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Pong extends Game {

	public static final int WIDTH = 1000;
	public static final int HEIGHT = 640;

	public static final String TITLE = "Pong";

	SpriteBatch batch;
	BitmapFont font;

	public void create () {

		batch = new SpriteBatch();
		font = new BitmapFont();//Default arial font for now
		font.getData().setScale(10, 10);
		this.setScreen(new MainMenuScreen(this));//Start with menu screen
	}

	public void render () {
		super.render();
	}

	public void dispose () {

		font.dispose();
		batch.dispose();
		System.exit(0);
	}
}
