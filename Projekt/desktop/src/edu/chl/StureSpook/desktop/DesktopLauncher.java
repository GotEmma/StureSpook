package edu.chl.StureSpook.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import edu.chl.StureSpook.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
            LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
            config.title = "StureSpook";
            config.width = 800;
            config.height = 480;
            new LwjglApplication(new Main(), config);
	}
}
