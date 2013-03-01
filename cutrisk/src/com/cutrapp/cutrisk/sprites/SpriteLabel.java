package com.cutrapp.cutrisk.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class SpriteLabel {
	//------------------------------
	//-----     ATTRIBUTES     -----
	//------------------------------
	protected Label label;
	protected Vector2 position;
	
	
	
	//---------------------------
	//-----     BUILDER     -----
	//---------------------------
	public SpriteLabel()
	{
		position = new Vector2();
		label = new Label("", new LabelStyle(new BitmapFont(), Color.WHITE));
		label.setPosition(position.x, position.y);
	}
	
	public SpriteLabel(String text)
	{
		label = new Label(text, new LabelStyle(new BitmapFont(), Color.WHITE));
		position = new Vector2();
	}
	
	
	
	//--------------------------------
	//-----     METHODS BUCLE    -----
	//--------------------------------
	public void dispose()
	{
	}
	
	public void render(SpriteBatch batch, int x, int y)
	{
		setPosition(x, y);
		label.draw(batch, 1f);
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
	public void setPosition(int x, int y)
	{
		position.x = x;
		position.y = y;
		label.setPosition(x, y);
	}
	
	public Vector2 getPosition()
	{
		return position;
	}
	
	public void setText(String text)
	{
		label.setText(text);
	}
	
	public int numEndLines()
	{
		int count = 0;
		for (int i = 0; i < label.getText().length(); i ++)
		{
			if (label.getText().charAt(i) == '\n')
				count ++;
		}
		
		return count;
	}

} //SpriteLabel
