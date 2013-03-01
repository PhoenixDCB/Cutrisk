package com.cutrapp.cutrisk;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "cutrisk";
		cfg.useGL20 = true;
		
		//Samsung Galaxy S3
		cfg.width = 720;
		cfg.height = 1280;
		
		//Samsung Galaxy S
		//cfg.width = 480;
		//cfg.height = 800;
		
		new LwjglApplication(new Cutrisk(), cfg);
	}
}
