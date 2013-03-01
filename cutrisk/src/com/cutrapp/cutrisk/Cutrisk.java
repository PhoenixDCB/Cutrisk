package com.cutrapp.cutrisk;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.cutrapp.cutrisk.sceneManager.SceneMng;

public class Cutrisk implements ApplicationListener {
	//Samsung Galaxy S3
	public static final float VIRTUAL_WIDTH = 720, 
							  VIRTUAL_HEIGHT = 1280;
	
	//Samsung Galaxy S
	//public static final float VIRTUAL_WIDTH = 480, 
	//		  				  VIRTUAL_HEIGHT = 800;

    private static final float ASPECT_RATIO = (float)VIRTUAL_WIDTH/(float)VIRTUAL_HEIGHT;
 
 	public static final boolean DEBUG = true;
 	//public static final boolean DEBUG = false;
	
	//------------------------------
	//-----     ATTRIBUTES     -----
	//------------------------------
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private SceneMng sceneMng;
    private Rectangle viewport;
	
	
	
	//-----------------------------
	//-----     FUNCTIONS     -----
	//-----------------------------
	@Override
	public void create() {	
		//spriteBatch & camera
		batch = new SpriteBatch();
		camera = new OrthographicCamera(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		
		sceneMng = new SceneMng(camera);
	}

	@Override
	public void dispose() {
		batch.dispose();
		
		sceneMng.dispose();
	}

	@Override
	public void render() {
		// update camera
        //camera.update();
        //camera.apply(Gdx.gl10);
 
        // set viewport
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);
 
        // clear previous frame
        Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); 
		
		//draw
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		sceneMng.render(batch);
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		//camera = new OrthographicCamera(width, height);
		//sceneMng.resize(width, height);
		
		// calculate new viewport
        float aspectRatio = (float)width/(float)height;
        float scale = 1f;
        Vector2 crop = new Vector2(0f, 0f);
        
        if(aspectRatio > ASPECT_RATIO)
        {
            scale = (float)height/(float)VIRTUAL_HEIGHT;
            crop.x = (width - VIRTUAL_WIDTH*scale)/2f;
        }
        else if(aspectRatio < ASPECT_RATIO)
        {
            scale = (float)width/(float)VIRTUAL_WIDTH;
            crop.y = (height - VIRTUAL_HEIGHT*scale)/2f;
        }
        else
        {
            scale = (float)width/(float)VIRTUAL_WIDTH;
        }

        float w = (float)VIRTUAL_WIDTH*scale;
        float h = (float)VIRTUAL_HEIGHT*scale;
        viewport = new Rectangle(crop.x, crop.y, w, h);
    }

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
} //Cutrisk
