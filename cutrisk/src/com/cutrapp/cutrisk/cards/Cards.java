package com.cutrapp.cutrisk.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.cutrapp.cutrisk.Cutrisk;


public class Cards {
	protected static final int WIDTH = (int) Cutrisk.VIRTUAL_WIDTH,
							   HEIGHT = (int) Cutrisk.VIRTUAL_HEIGHT;
	
	//------------------------------
	//-----     ATTRIBUTES     -----
	//------------------------------
	protected Array<Card> cards,
						  cardsSelected;
	protected int numCard = 0;
	protected float time = 0;
	
	
	//---------------------------
	//-----     BUILDER     -----
	//---------------------------
	public Cards()
	{
		cards = new Array<Card>();		
		cards.add(new Card("graphics/3D_card.png", WIDTH, HEIGHT, "Ocupa 24 territorios."));
		cards.add(new Card("graphics/3D_card.png", WIDTH, HEIGHT, "Ocupa 18 territorios con, mínimo, 2\nejércitos"));
		cards.add(new Card("graphics/3D_card.png", WIDTH, HEIGHT, "Destruye el ejército rojo. Si no existe\neste ejército o es tu ejército, tu objetivo\nes ocupar 24 territorios"));
		cards.add(new Card("graphics/3D_card.png", WIDTH, HEIGHT, "Destruye el ejército amarillo. Si no existe\neste ejército o es tu ejército, tu objetivo\nes ocupar 24 territorios"));
		cards.add(new Card("graphics/3D_card.png", WIDTH, HEIGHT, "Destruye el ejército azul. Si no existe\neste ejército o es tu ejército, tu objetivo\nes ocupar 24 territorios"));
		cards.add(new Card("graphics/3D_card.png", WIDTH, HEIGHT, "Destruye el ejército verde. Si no existe\neste ejército o es tu ejército, tu objetivo\nes ocupar 24 territorios"));
		cards.add(new Card("graphics/3D_card.png", WIDTH, HEIGHT, "Destruye el ejército negro. Si no existe\neste ejército o es tu ejército, tu objetivo\nes ocupar 24 territorios"));
		cards.add(new Card("graphics/3D_card.png", WIDTH, HEIGHT, "Conquista Asia y América del sur."));
		cards.add(new Card("graphics/3D_card.png", WIDTH, HEIGHT, "Conquista Asia y África."));
		cards.add(new Card("graphics/3D_card.png", WIDTH, HEIGHT, "Conquista América del norte y África."));
		cards.add(new Card("graphics/3D_card.png", WIDTH, HEIGHT, "Conquista América del norte y Australia."));
		cards.add(new Card("graphics/3D_card.png", WIDTH, HEIGHT, "Conquista Europa, América del sur y\notro continente."));
		cards.add(new Card("graphics/3D_card.png", WIDTH, HEIGHT, "Conquista Europa, África y otro\ncontinente"));
		cards.add(new Card("graphics/3D_card.png", WIDTH, HEIGHT, "Mantén Asia conquistado durante 1 turno."));
		cards.add(new Card("graphics/3D_card.png", WIDTH, HEIGHT, "Consigue 60 soldados."));
		
		cardsSelected = new Array<Card>();
	}
	
		
	
	//---------------------------------
	//-----     METHODS BUCLE     -----
	//---------------------------------	
	public void dispose()
	{
		for (int i = 0; i < cards.size; i ++)
		{
			cards.get(i).dispose();
		}
	}
	
	public void render(SpriteBatch batch)
	{
		time += Gdx.graphics.getDeltaTime();
		if (time > 2)
		{
			if (numCard + 1 == cards.size)
				numCard = 0;
			else
				numCard ++;
			
			time = 0;
		}
		
		cards.get(numCard).render(batch, -WIDTH/2, -HEIGHT/2);
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
	public Card drawCard()
	{
		int numCard = MathUtils.random(0, cards.size - 1);
		while (cardsSelected.contains(cards.get(numCard), true))
			numCard = MathUtils.random(0, cards.size - 1);
		cardsSelected.add(cards.get(numCard));
		return cards.get(numCard);
	}
	
} //Cards
