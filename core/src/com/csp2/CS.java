package com.csp2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP;


public class CS extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img, the;
	TextureRegion[] frames;
	Animation a;
	private float time=0;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		the = new Texture("running.png");

		TextureRegion[][] tmp=TextureRegion.split(the,420,504);

		frames= new TextureRegion[9];

		int i=0;

		for(int x=0; x<9; x++){
			frames[i]=tmp[0][x];
			i++;
		}

		a=new Animation(1f/9f,frames);
	}

	@Override
	public void render () {
		time+= Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor((float)1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw((TextureRegion)a.getKeyFrame(time,true), (float)0, (float)0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
