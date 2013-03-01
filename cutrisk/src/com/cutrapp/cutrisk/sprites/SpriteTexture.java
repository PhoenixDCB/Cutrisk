package com.cutrapp.cutrisk.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class SpriteTexture {
	//-----------------------------
	//-----     ATRIBUTOS     -----
	//-----------------------------	
	protected Texture texture;
	protected Sprite sprite;
	protected int regionWidth, regionHeight;
	protected Vector2 position;
	
		
	
	//---------------------------
	//-----     BUILDER     -----
	//---------------------------
	public SpriteTexture(String pathTexture, int regionWidth, int regionHeight)
	{
		this.regionWidth = regionWidth;
		this.regionHeight = regionHeight;
		
		position = new Vector2();
		
		texture = new Texture(Gdx.files.internal(pathTexture));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);		
		TextureRegion region = new TextureRegion(texture, 0, 0, regionWidth, regionHeight);
		
		sprite = new Sprite(region);
		sprite.setSize(regionWidth, regionHeight);
		sprite.setOrigin(regionWidth/2, regionHeight/2);
		sprite.setPosition(-region.getRegionWidth()/2, -region.getRegionHeight()/2);		
	}
	
	
	
	//--------------------------------
	//-----     METHODS BUCLE    -----
	//--------------------------------
	public void dispose()
	{
		texture.dispose();
	}
	
	public void render(SpriteBatch batch, int x, int y)
	{
		sprite.setPosition(x, y);
		sprite.draw(batch);
	}
	
	public void render(SpriteBatch batch, int x, int y, int width, int height)
	{
		sprite.setBounds(x, y, width, height);
		sprite.draw(batch);
	}
	
	public void render(SpriteBatch batch, int x, int y, float r, float g, float b, float a)
	{
		sprite.setPosition(x, y);
		sprite.setColor(r, g, b, a);
		sprite.draw(batch);
	}
	
	public void render(SpriteBatch batch, int x, int y, int width, int height, float r, float g, float b, float a)
	{
		sprite.setBounds(x, y, width, height);
		sprite.setColor(r, g, b, a);
		sprite.draw(batch);
	}
	
	public void resize(int width, int height) {
	}

	public void pause() {
	}

	public void resume() {
	}
	
	
	
	//---------------------------
	//-----     METHODS     -----
	//---------------------------
	
	public int getWidth()
	{
		return regionWidth;
	}
	
	public int getHeight()
	{
		return regionHeight;
	}

} //SpriteTexture
