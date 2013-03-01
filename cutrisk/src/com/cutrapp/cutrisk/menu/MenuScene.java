package com.cutrapp.cutrisk.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.cutrapp.cutrisk.Cutrisk;
import com.cutrapp.cutrisk.cards.Card;
import com.cutrapp.cutrisk.sceneManager.SceneMng;
import com.cutrapp.cutrisk.sprites.SpriteTexture;

public class MenuScene {
	public static enum TYPE_MENUSCENE 
	{
	    SELECT_PLAYER,
	    LOOK_MISSION
	};	
	protected static enum SELECTED
	{
		SELECT,
		NOT_SELECT
	};
	protected static final int SPACE_WIDTH = 20,
							   SPACE_HEIGHT = 30;
	
	private SceneMng sceneMng;
	
	//------------------------------
	//-----     ATTRIBUTES     -----
	//------------------------------
	protected TYPE_MENUSCENE currentState;
	protected SpriteTexture selectPlayer,
							selectColor,
							quit,
							next,
							selectOption,
							back;
	protected int width, 
				  height;
	protected Array<SpriteTexture> colors;
	protected Array<Rectangle> colorsRect;
	protected Array<SELECTED> colorSelected;
	protected Array<Card> cardPlayer;
	protected boolean quitSelected, 
					  nextSelected,
					  pushed,
					  backSelected;
	protected Rectangle quitRect,
						nextRect,
						backRect;
	
	
	
	
	//---------------------------
	//-----     BUILDER     -----
	//---------------------------
	public MenuScene(SceneMng sceneMng, Array<SpriteTexture> colors, Array<Card> cardPlayer)
	{
		this.sceneMng = sceneMng;
		
		this.colors = colors;
		this.cardPlayer = cardPlayer;
		
		width = (int) Cutrisk.VIRTUAL_WIDTH;
		height = (int) Cutrisk.VIRTUAL_HEIGHT;
		
		currentState = TYPE_MENUSCENE.SELECT_PLAYER;
		
		selectPlayer = new SpriteTexture("graphics/menu/3D_selectMission.png", 386, 33);
		selectColor = new SpriteTexture("graphics/menu/rectangle.png", 150, 30);
		selectOption = new SpriteTexture("graphics/menu/rectangle.png", 150, 30);
		quit = new SpriteTexture("graphics/menu/quit.png", 150, 30);
		next = new SpriteTexture("graphics/menu/next.png", 150, 30);
		back = new SpriteTexture("graphics/menu/back.png", 150, 30);
		
		colorsRect = new Array<Rectangle>(colors.size);
		for (int i = 0; i < colors.size; i++)
		{
			colorsRect.add(new Rectangle());
		}
		quitRect = new Rectangle();
		nextRect = new Rectangle();
		backRect = new Rectangle();
		
		colorSelected = new Array<SELECTED>(colors.size);
		for (int i = 0; i < colors.size; i++)
		{
			colorSelected.add(SELECTED.NOT_SELECT);
		}		
		quitSelected = nextSelected = pushed = backSelected = false;
	}
	
	
	
	//-----------------------------
	//-----     FUNCTIONS     -----
	//-----------------------------
	public void dispose()
	{
		selectPlayer.dispose();
		selectColor.dispose();
		selectOption.dispose();
		quit.dispose();
		next.dispose();
		back.dispose();
	}
	
	public void render(SpriteBatch batch)
	{	
		logic();
		
		if (currentState == TYPE_MENUSCENE.SELECT_PLAYER)
		{
			int limitHeight = -selectPlayer.getHeight()/2 + ((colors.size) * selectColor.getHeight()) / 2 + (colors.size * SPACE_HEIGHT) / 2;
			selectPlayer.render(batch, -selectPlayer.getWidth()/2, limitHeight);
			for (int i = 0; i < colors.size; i++)
			{
				if (colorSelected.get(i) == SELECTED.SELECT)
				{
					selectColor.render(batch, - selectColor.getWidth() / 2, limitHeight - (i+1) * (selectColor.getHeight() + SPACE_HEIGHT));
				}
				colors.get(i).render(batch, - colors.get(i).getWidth() / 2, limitHeight - (i+1) * (selectColor.getHeight() + SPACE_HEIGHT));
			}
			
			int quitWidth = -width/2,
				quitHeight = height/2 - quit.getHeight(),
				nextWidth = width/2 - next.getWidth(),
				nextHeight = quitHeight;
			if (quitSelected)
				selectOption.render(batch, quitWidth, quitHeight);
			if (nextSelected)
				selectOption.render(batch, nextWidth, nextHeight);
			quit.render(batch, quitWidth, quitHeight);
			next.render(batch, nextWidth, nextHeight);
		}
		
		if (currentState == TYPE_MENUSCENE.LOOK_MISSION)
		{
			Card card = null;
			for (int i = 0; i < colors.size; i++)
			{
				if (colorSelected.get(i) == SELECTED.SELECT)
				{
					card = cardPlayer.get(i);
				}
			}
			if (card != null)
			{
				card.render(batch, -card.getWidth()/2, -card.getHeight()/2);
			}
			
			int backtWidth = width/2 - back.getWidth(),
				backHeight = height/2 - back.getHeight();
			if (backSelected)
			{
				selectOption.render(batch, backtWidth, backHeight);
			}
			back.render(batch, backtWidth, backHeight);
		}
	}
	
	public void resize(int width, int height) 
	{
		this.width = width;
		this.height = height;
		
		int limitHeight = - selectPlayer.getHeight()/2 + ((colors.size) * selectColor.getHeight()) / 2 + (colors.size * SPACE_HEIGHT) / 2;
		for (int i = 0; i < colors.size; i ++)
		{
			colorsRect.get(i).set(0 - selectColor.getWidth() / 2, 
								  limitHeight - (i+1) * (selectColor.getHeight() + SPACE_HEIGHT), 
								  selectColor.getWidth(), 
								  selectColor.getHeight());
		}
		
		int quitWidth = -width/2,
			quitHeight = height/2 - quit.getHeight(),
			nextWidth = width/2 - next.getWidth(),
			nextHeight = quitHeight;
		quitRect.set(quitWidth, quitHeight, quit.getWidth(), quit.getHeight());
		nextRect.set(nextWidth, nextHeight, next.getWidth(), next.getHeight());

		int backtWidth = width/2 - back.getWidth(),
			backHeight = height/2 - back.getHeight();		
		backRect.set(backtWidth, backHeight, back.getWidth(), back.getHeight());
	}

	public void pause() {
	}

	public void resume() {
	}	
	
	
	
	//-------------------------------------
	//-----     PRIVATE FUNCTIONS     -----
	//-------------------------------------
	private void logic()
	{
		//events
		int x = Gdx.input.getX(),
			y = Gdx.input.getY();
		
		Vector3 posicion = new Vector3(x,y,0f);
		this.sceneMng.camera.unproject(posicion);
		x = (int) posicion.x;
		y = (int) (posicion.y);
		
		if (!pushed && !Gdx.input.isTouched())
		{
			x = -100;
			y = -100;
		}
		
		if (Gdx.input.isTouched())
			pushed = true;
		
		//logic
		if (currentState == TYPE_MENUSCENE.SELECT_PLAYER)
		{
			//select quit
			if (quitRect.contains(x, y))
			{
				quitSelected = true;
				if (!Gdx.input.isTouched() && pushed)
				{
					Gdx.app.exit();
				}
			}
			else
			{
				quitSelected = false;
			}
			
			//select next
			if (nextRect.contains(x, y))
			{
				nextSelected = true;
				boolean anyColor = false;
				for (int i = 0; i < colors.size; i++)
				{
					if (colorSelected.get(i) == SELECTED.SELECT)
					{
						anyColor = true;
					}
				}
				if (!Gdx.input.isTouched() && pushed)
				{
					if (anyColor)
					{
						currentState = TYPE_MENUSCENE.LOOK_MISSION;
					}
				}
			}
			else
				nextSelected = false;
			
			//select a color
			int indexColor = -1;
			for (int i = 0; i < colors.size; i++)
			{
				if (colorsRect.get(i).contains(x, y))
				{
					indexColor = i;
					colorSelected.set(i, SELECTED.SELECT);
				}
			}			
			if (indexColor != -1)
			{
				for (int i = 0; i < colors.size; i++)
				{
					if (i != indexColor)
					{
						colorSelected.set(i, SELECTED.NOT_SELECT);
					}
				}
			}
		}
		else if (currentState == TYPE_MENUSCENE.LOOK_MISSION)
		{
			//select back
			if (backRect.contains(x, y))
			{
				backSelected = true;
				if (!Gdx.input.isTouched() && pushed)
				{
					currentState = TYPE_MENUSCENE.SELECT_PLAYER;
				}
			}
			else
			{
				backSelected = false;
			}
		}
		
		if (!Gdx.input.isTouched())
			pushed = false;
	} //logic	
	
} //MenuScene
