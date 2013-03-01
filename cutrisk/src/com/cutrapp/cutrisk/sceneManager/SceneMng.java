package com.cutrapp.cutrisk.sceneManager;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cutrapp.cutrisk.Cutrisk;
import com.cutrapp.cutrisk.menu.Menu;
import com.cutrapp.cutrisk.menu.MenuScene;
import com.cutrapp.cutrisk.splashScreen.Splash;
import com.cutrapp.cutrisk.sprites.SpriteTexture;

public class SceneMng {
	public static enum STATE {
		SPLASH_CUTRAPP,
	    MENU,
	    MENU_SCENE
	};
	
	public Camera camera;
	
	//------------------------------
	//-----     ATTRIBUTES     -----
	//------------------------------
	protected STATE currentState;
	protected Menu menu;
	protected MenuScene menuScene;
	protected Splash splashScreen;
	protected int width,
				  height;
	protected SpriteTexture background;
	
	
	
	//---------------------------
	//-----     BUILDER     -----
	//---------------------------
	public SceneMng(Camera camera)
	{
		width = (int) Cutrisk.VIRTUAL_WIDTH;
		height = (int) Cutrisk.VIRTUAL_HEIGHT;
		
		currentState = STATE.SPLASH_CUTRAPP;
		
		splashScreen = new Splash();
		background = new SpriteTexture("graphics/backgrounds/backgroundTitle.png", width, height);
		
		this.camera = camera;  
	}
	
	
	
	//-----------------------------
	//-----     FUNCTIONS     -----
	//-----------------------------
	public void dispose()
	{
		splashScreen.dispose();
		if (menu != null)
			menu.dispose();
		if (menuScene != null)
			menuScene.dispose();
		background.dispose();
	}
	
	public void render(SpriteBatch batch)
	{
		logic();
		
		if (currentState == STATE.SPLASH_CUTRAPP)
			splashScreen.render(batch);
		else if (currentState == STATE.MENU)
		{
			background.render(batch, -width / 2, -height / 2, width, height);
			menu.render(batch);
		}
		else if (currentState == STATE.MENU_SCENE)
		{
			background.render(batch, -width / 2, -height / 2, width, height);
			menuScene.render(batch);
		}
	}
	
	public void resize(int width, int height) 
	{
		this.width = width;
		this.height = height;
		
		if (currentState == STATE.SPLASH_CUTRAPP)
			splashScreen.resize(width, height);
		else if (currentState == STATE.MENU)
			menu.resize(width, height);
		
		else if (currentState == STATE.MENU_SCENE)
			menuScene.resize(width, height);
	}

	public void pause() 
	{
	}

	public void resume() 
	{
	}	
	
	
	
	//---------------------------
	//-----     PRIVATE     -----
	//---------------------------
	private void logic()
	{
		if (currentState == STATE.SPLASH_CUTRAPP && splashScreen.isFinished())
		{
			currentState = STATE.MENU;
			menu = new Menu(this);
			menu.resize(width, height);
		}
		else if (currentState == STATE.MENU && menu.isFinished())
		{
			menuScene = new MenuScene(this, menu.getPlayers(), menu.getCards());
			menuScene.resize(width, height);
			
			currentState = STATE.MENU_SCENE;
		}
	}

} //SceneMng
