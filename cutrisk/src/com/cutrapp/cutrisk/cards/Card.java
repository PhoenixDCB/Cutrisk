package com.cutrapp.cutrisk.cards;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cutrapp.cutrisk.sprites.SpriteLabel;
import com.cutrapp.cutrisk.sprites.SpriteTexture;

public class Card {
	//------------------------------
	//-----     ATTRIBUTES     -----
	//------------------------------
	protected SpriteTexture sprite;
	protected SpriteLabel mission;
	
	
	
	//---------------------------
	//-----     BUILDER     -----
	//---------------------------
	public Card(String pathTexture, int regionWidth, int regionHeight, String text)
	{
		sprite = new SpriteTexture(pathTexture, regionWidth, regionHeight);
		mission = new SpriteLabel(text);
	}
	
	
	
	//---------------------------------
	//-----     METHODS BUCLE     -----
	//---------------------------------
	public void dispose()
	{
		sprite.dispose();
		mission.dispose();
	}
	
	public void render(SpriteBatch batch, int x, int y)
	{
		sprite.render(batch, x, y);
		mission.render(batch, -sprite.getWidth()/4, -sprite.getHeight()/4 -mission.numEndLines()*18);
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
		return sprite.getWidth();
	}
	
	public int getHeight()
	{
		return sprite.getHeight();
	}

} //Card
