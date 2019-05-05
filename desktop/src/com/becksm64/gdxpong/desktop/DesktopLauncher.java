package com.becksm64.gdxpong.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.becksm64.gdxpong.Pong;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Pong.WIDTH;
		config.height = Pong.HEIGHT;
		config.title = Pong.TITLE;
		new LwjglApplication(new Pong(), config);
	}
}
