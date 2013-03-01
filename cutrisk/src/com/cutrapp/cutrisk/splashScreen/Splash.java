package com.cutrapp.cutrisk.splashScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cutrapp.cutrisk.Cutrisk;
import com.cutrapp.cutrisk.sprites.SpriteTexture;

public class Splash {
	//------------------------------
	//-----     ATTRIBUTES     -----
	//------------------------------
	protected SpriteTexture cutrappScreen;
	protected int width,
				  height;
	protected float transparence,
					time;
	
	
	
	//---------------------------
	//-----     BUILDER     -----
	//---------------------------
	public Splash()
	{
		width = (int) Cutrisk.VIRTUAL_WIDTH;
		height = (int) Cutrisk.VIRTUAL_HEIGHT;
		
		cutrappScreen = new SpriteTexture("graphics/splash/cutrapp.png", width, height);
		
		transparence = time = 0.0f;
	}
	
	
	
	//-----------------------------
	//-----     FUNCTIONS     -----
	//-----------------------------
	public void dispose()
	{
		cutrappScreen.dispose();
	}
	
	public void render(SpriteBatch batch)
	{
		logic();
		
		cutrappScreen.render(batch, -width / 2, -height / 2, width, height, 1, 1, 1, this.transparence);
	}
	
	public void resize(int width, int height) 
	{
		this.width = width;
		this.height = height;
	}

	public void pause() 
	{
	}

	public void resume() 
	{
	}
	
	public boolean isFinished()
	{
		return time >= 4f;
	}
	
	
	
	//---------------------------
	//-----     PRIVATE     -----
	//---------------------------
	protected void logic()
	{
		time += Gdx.graphics.getDeltaTime();
		transparence = Math.min(time / 4, 1f);
	}
	
} //Splash
