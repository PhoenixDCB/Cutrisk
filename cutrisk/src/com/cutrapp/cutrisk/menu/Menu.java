package com.cutrapp.cutrisk.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.cutrapp.cutrisk.Cutrisk;
import com.cutrapp.cutrisk.cards.Card;
import com.cutrapp.cutrisk.cards.Cards;
import com.cutrapp.cutrisk.sceneManager.SceneMng;
import com.cutrapp.cutrisk.sprites.SpriteLabel;
import com.cutrapp.cutrisk.sprites.SpriteTexture;

public class Menu {
	public static enum TYPE_MENU {
	    INITIAL,
	    NUMBER_PLAYERS,
	    SELECT_COLOR,
	    MISSION
	};
	
	protected static final int SPACE_WIDTH = 20,
			   				   SPACE_HEIGHT = 30;	
	
	protected SceneMng sceneMng;
	
	//------------------------------
	//-----     ATTRIBUTES     -----
	//------------------------------
	protected TYPE_MENU currentType;
	
	protected ShapeRenderer shapes;
	
	protected SpriteTexture next,
							back,
							newGame,
							quit,
							selColors,
							red,
							yellow,
							green,
							blue,
							black,
							selectNewGame,
							selectQuit,
							selectNext,
							selectBack,
							selectColors;
	
	protected Rectangle nextRect,
						backRect,
						newGameRect,
						quitRect,
						redRect,
						yellRect,
						blueRect,
						greenRect,
						blackRect;
	
	private SpriteLabel mousePosition;
	
	protected int width, height;
	
	protected boolean selectedMouse,
						selectedNewGame,
						selectedQuit,
						selectedBack,
						selectedNext,
						selectedRed,
						selectedYellow,
						selectedBlue,
						selectedGreen,
						selectedBlack,
						pressedRed,
						pressedYellow,
						pressedBlue,
						pressedGreen,
						pressedBlack,
						finished;
	
	protected float time;
	
	private int totalPlayers;
	
	private Array<SpriteTexture> players;
	
	protected Array<Card> cardPlayer;
	
	protected Cards cards;
	
	
	//---------------------------
	//-----     BUILDER     -----
	//---------------------------
	public Menu(SceneMng sceneMng)
	{
		this.sceneMng = sceneMng;
		
		width = (int)Cutrisk.VIRTUAL_WIDTH;
		height = (int)Cutrisk.VIRTUAL_HEIGHT;
		
		totalPlayers = 0;
		
		players = null;
		cardPlayer = null;
		
		cards = new Cards();
		
		finished = false;
		
		selectedMouse = false;
		selectedNewGame = false;
		selectedQuit = false;
		selectedBack = false;
		selectedNext = false;
		
		selectedRed = false;
		selectedYellow = false;
		selectedBlue = false;
		selectedGreen = false;
		selectedBlack = false;
		
		pressedRed = false;
		pressedYellow = false;
		pressedBlue = false;
		pressedGreen = false;
		pressedBlack = false;
		
		time = 2;
		
		currentType = TYPE_MENU.INITIAL;
		
		next = new SpriteTexture("graphics/menu/next.png", 150, 30);
		back = new SpriteTexture("graphics/menu/back.png", 150, 30);
		newGame = new SpriteTexture("graphics/menu/3D_newGame.png", 387, 57);
		quit = new SpriteTexture("graphics/menu/3D_quit.png", 186, 55);
		selColors = new SpriteTexture("graphics/menu/3D_selectPlayers.png", 386, 39);
		red = new SpriteTexture("graphics/menu/red.png", 150, 30);
		yellow = new SpriteTexture("graphics/menu/yellow.png", 150, 30);
		green = new SpriteTexture("graphics/menu/green.png", 150, 30);
		blue = new SpriteTexture("graphics/menu/blue.png", 150, 30);
		black = new SpriteTexture("graphics/menu/black.png", 150, 30);
		
		selectNewGame = new SpriteTexture("graphics/menu/rectangle.png", newGame.getWidth(), newGame.getHeight());
		selectQuit = new SpriteTexture("graphics/menu/rectangle.png", quit.getWidth(), quit.getHeight());
		selectNext = new SpriteTexture("graphics/menu/rectangle.png", next.getWidth(), next.getHeight());
		selectBack = new SpriteTexture("graphics/menu/rectangle.png", back.getWidth(), back.getHeight());
		selectColors = new SpriteTexture("graphics/menu/rectangle.png", red.getWidth(), red.getHeight());
		
		nextRect = new Rectangle();
		backRect = new Rectangle();
		newGameRect = new Rectangle();
		quitRect = new Rectangle();
		
		redRect = new Rectangle();
		yellRect = new Rectangle();
		blueRect = new Rectangle();
		greenRect = new Rectangle();
		blackRect = new Rectangle();
		
		mousePosition = new SpriteLabel();
	}
	
	
	
	//-----------------------------
	//-----     FUNCTIONS     -----
	//-----------------------------
	public void dispose()
	{
		next.dispose();
		back.dispose();
		newGame.dispose();
		quit.dispose();
		selColors.dispose();
		red.dispose();
		yellow.dispose();
		green.dispose();
		blue.dispose();
		black.dispose();
		
		selectNewGame.dispose();
		selectQuit.dispose();
		selectNext.dispose();
		selectBack.dispose();
		selectColors.dispose();
		
		mousePosition.dispose();
		
		if (players != null)
			for (int i = 0; i < totalPlayers; i++)
				players.get(i).dispose();
		cards.dispose();
	}
	
	public void render(SpriteBatch batch)
	{	
		time += Gdx.graphics.getDeltaTime();
		logic();
		
		if (currentType == TYPE_MENU.INITIAL) {
			int widthNewGame = -newGame.getWidth()/2, 
				heightNewGame = newGame.getHeight()/2 + SPACE_HEIGHT/2;
			int widthQuit = - quit.getWidth()/2,
				heightQuit = heightNewGame - SPACE_HEIGHT - quit.getHeight(); 
			
			if (selectedNewGame)
				selectNewGame.render(batch, widthNewGame, heightNewGame);
			if (selectedQuit)
				selectQuit.render(batch, widthQuit, heightQuit);
			
			newGame.render(batch, widthNewGame, heightNewGame);
			quit.render(batch, widthQuit, heightQuit);
		} 
		else if (currentType == TYPE_MENU.SELECT_COLOR) {
			//paint next and back
			int widthNext = width/2 - next.getWidth(),
				heightNext = height/2 - next.getHeight(), 
				widthBack = -width/2,
				heightBack = height/2 - back.getHeight();
			
			if (selectedBack)
				selectBack.render(batch, widthBack, heightBack);
			if (selectedNext)
				selectNext.render(batch, widthNext, heightNext);
			next.render(batch, widthNext, heightNext);
			back.render(batch, widthBack, heightBack);
			
			//paint "select colors" and colors
			int widthSelColors = -selColors.getWidth() / 2,
				heightSelColors = -selColors.getHeight() / 2 + 5 * (selColors.getHeight() + SPACE_HEIGHT) / 2,
				widthColors = - selectColors.getWidth() / 2,
				heightColorRed = heightSelColors - SPACE_HEIGHT - selectColors.getHeight(),
				heightColorYellow = heightColorRed - SPACE_HEIGHT - selectColors.getHeight(),
				heightColorBlue = heightColorYellow - SPACE_HEIGHT - selectColors.getHeight(),
				heightColorGreen = heightColorBlue - SPACE_HEIGHT - selectColors.getHeight(),
				heightColorBlack = heightColorGreen - SPACE_HEIGHT - selectColors.getHeight();
			
			selColors.render(batch, widthSelColors, heightSelColors);
			
			if  (selectedRed || pressedRed)
				selectColors.render(batch, widthColors, heightColorRed);
			if (selectedYellow || pressedYellow)
				selectColors.render(batch, widthColors, heightColorYellow);
			if (selectedBlue || pressedBlue)
				selectColors.render(batch, widthColors, heightColorBlue);
			if (selectedGreen || pressedGreen)
				selectColors.render(batch, widthColors,heightColorGreen);
			if (selectedBlack || pressedBlack)
				selectColors.render(batch, widthColors, heightColorBlack);
			
			red.render(batch, widthColors, heightColorRed);
			yellow.render(batch, widthColors, heightColorYellow);
			blue.render(batch, widthColors, heightColorBlue);
			green.render(batch, widthColors, heightColorGreen);
			black.render(batch, widthColors, heightColorBlack);
		}
		
		if (Cutrisk.DEBUG)
			mousePosition.render(batch, -width/2 + 10, height/2 - 20);
	}
	
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		
		int widthNewGame = 0 - newGame.getWidth()/2, 
			heightNewGame = 0 + newGame.getHeight()/2 + SPACE_HEIGHT/2;
		int widthQuit = 0 - quit.getWidth()/2,
			heightQuit = heightNewGame - SPACE_HEIGHT - quit.getHeight();	 		
		quitRect.set(widthQuit, heightQuit, quit.getWidth(), quit.getHeight());
		newGameRect.set(widthNewGame, heightNewGame, newGame.getWidth(), newGame.getHeight());
		
		int widthNext = width/2 - next.getWidth(),
			heightNext = height/2 - next.getHeight(), 
			widthBack = -width/2,
			heightBack = height/2 - back.getHeight();
		nextRect.set(widthNext, heightNext, next.getWidth(), next.getHeight());
		backRect.set(widthBack, heightBack, back.getWidth(), back.getHeight());
		
		
		int widthColors = 0 - selectColors.getWidth() / 2,
			heightColorRed = 0 - selColors.getHeight() / 2 + 5 * (selColors.getHeight() + SPACE_HEIGHT) / 2 - SPACE_HEIGHT - selectColors.getHeight(),
			heightColorYellow = heightColorRed - SPACE_HEIGHT - selectColors.getHeight(),
			heightColorBlue = heightColorYellow - SPACE_HEIGHT - selectColors.getHeight(),
			heightColorGreen = heightColorBlue - SPACE_HEIGHT - selectColors.getHeight(),
			heightColorBlack = heightColorGreen - SPACE_HEIGHT - selectColors.getHeight();
		redRect.set(widthColors, heightColorRed, selectColors.getWidth(), selectColors.getHeight());
		yellRect.set(widthColors, heightColorYellow, selectColors.getWidth(), selectColors.getHeight());
		blueRect.set(widthColors, heightColorBlue, selectColors.getWidth(), selectColors.getHeight());
		greenRect.set(widthColors, heightColorGreen, selectColors.getWidth(), selectColors.getHeight());
		blackRect.set(widthColors, heightColorBlack, selectColors.getWidth(), selectColors.getHeight());
	}

	public void pause() {
	}

	public void resume() {
	}	
	
	public boolean isFinished()
	{
		return finished;
	}
	
	public Array<SpriteTexture> getPlayers()
	{
		return players;
	}
	
	public Array<Card> getCards()
	{
		return cardPlayer;
	}
	
	
	
	//-------------------------------------
	//-----     PRIVATE FUNCTIONS     -----
	//-------------------------------------
	private void logic()
	{
		int x = Gdx.input.getX(),
			y = Gdx.input.getY();
		
		Vector3 posicion = new Vector3(x,y,0f);
		this.sceneMng.camera.unproject(posicion);
		x = (int) posicion.x;
		y = (int) (posicion.y);
		
		if (!selectedMouse && !Gdx.input.isTouched())
		{
			x = -100;
			y = -100;
		}
		mousePosition.setText("Position of mouse: " + x + ", " + y);
		
		if (Gdx.input.isTouched()) {
			selectedMouse = true;
		}
		
		if (currentType == TYPE_MENU.INITIAL)
		{
			if (newGameRect.contains(x, y))
			{
				selectedNewGame = true;
				selectedQuit = false;
				if (!Gdx.input.isTouched() && selectedMouse)
					currentType = TYPE_MENU.SELECT_COLOR;
			}
			else if (quitRect.contains(x, y)) {
				selectedNewGame = false;
				selectedQuit = true;
				if (!Gdx.input.isTouched() && selectedMouse)
					Gdx.app.exit();
			}
			else {
				selectedNewGame = false;
				selectedQuit = false;
			}
		}	
		else if (currentType == TYPE_MENU.SELECT_COLOR) {
			if (backRect.contains(x, y)) {
				selectedBack = true;
				selectedNext = false;
				
				selectedRed = false;
				selectedYellow = false;
				selectedBlue = false;
				selectedGreen = false;
				selectedBlack = false;
				if (!Gdx.input.isTouched() && selectedMouse)
					currentType = TYPE_MENU.INITIAL;
			}
			else if (nextRect.contains(x, y)) {
				selectedBack = false;
				selectedNext = true;
				
				selectedRed = false;
				selectedYellow = false;
				selectedBlue = false;
				selectedGreen = false;
				selectedBlack = false;
				
				//look how many colors are selected
				if (!Gdx.input.isTouched() && selectedMouse)
				{
					if (pressedRed)
						totalPlayers += 1; 
					if (pressedYellow)
						totalPlayers += 1; 
					if (pressedBlue)
						totalPlayers += 1; 
					if (pressedGreen)
						totalPlayers += 1; 
					if (pressedBlack)
						totalPlayers += 1; 
					
					if (totalPlayers >= 2)
					{
						finished = true;
						
						players = new Array<SpriteTexture>(totalPlayers);
						cardPlayer = new Array<Card>(totalPlayers);
						
						if (pressedRed)
						{
							players.add(new SpriteTexture("graphics/menu/red.png", 150, 30));
							cardPlayer.add(cards.drawCard());
						} 
						if (pressedYellow)
						{
							players.add(new SpriteTexture("graphics/menu/yellow.png", 150, 30));
							cardPlayer.add(cards.drawCard());
						}
						if (pressedBlue)
						{
							players.add(new SpriteTexture("graphics/menu/blue.png", 150, 30));
							cardPlayer.add(cards.drawCard());
						}
						if (pressedGreen)
						{
							players.add(new SpriteTexture("graphics/menu/green.png", 150, 30));
							cardPlayer.add(cards.drawCard());
						}
						if (pressedBlack)
						{
							players.add(new SpriteTexture("graphics/menu/black.png", 150, 30));
							cardPlayer.add(cards.drawCard());
						}
					}
					else
						totalPlayers = 0;
				}
			}
			else if (redRect.contains(x, y)) {
				selectedBack = false;
				selectedNext = false;
				
				selectedRed = true;
				selectedYellow = false;
				selectedBlue = false;
				selectedGreen = false;
				selectedBlack = false;
				
				if (!Gdx.input.isTouched() && selectedMouse) {
					pressedRed = !pressedRed;					
				}
			}
			else if (yellRect.contains(x, y)) {
				selectedBack = false;
				selectedNext = false;
				
				selectedRed = false;
				selectedYellow = true;
				selectedBlue = false;
				selectedGreen = false;
				selectedBlack = false;
				
				if (!Gdx.input.isTouched() && selectedMouse) {
					pressedYellow = !pressedYellow;					
				}
			}
			else if (blueRect.contains(x, y)) {
				selectedBack = false;
				selectedNext = false;
				
				selectedRed = false;
				selectedYellow = false;
				selectedBlue = true;
				selectedGreen = false;
				selectedBlack = false;
				
				if (!Gdx.input.isTouched() && selectedMouse) {
					pressedBlue = !pressedBlue;					
				}
			}
			else if (greenRect.contains(x, y)) {
				selectedBack = false;
				selectedNext = false;
				
				selectedRed = false;
				selectedYellow = false;
				selectedBlue = false;
				selectedGreen = true;
				selectedBlack = false;
				
				if (!Gdx.input.isTouched() && selectedMouse) {
					pressedGreen = !pressedGreen;					
				}
			}
			else if (blackRect.contains(x, y)) {
				selectedBack = false;
				selectedNext = false;
				
				selectedRed = false;
				selectedYellow = false;
				selectedBlue = false;
				selectedGreen = false;
				selectedBlack = true;
				
				if (!Gdx.input.isTouched() && selectedMouse) {
					pressedBlack = !pressedBlack;					
				}
			}
			else {
				selectedBack = false;
				selectedNext = false;
				
				selectedRed = false;
				selectedYellow = false;
				selectedBlue = false;
				selectedGreen = false;
				selectedBlack = false;
			}
		}
		
		if (!Gdx.input.isTouched())
			selectedMouse = false;
		
	}
	
	
} //Menu
