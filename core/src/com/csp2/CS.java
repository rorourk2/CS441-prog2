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
	Texture img, ground;
	Texture the;
	TextureRegion[] frames,back;
	Animation a;
	private float time=0;
	private float g=(float).7;
	private float b=1;
	private float move=0;
	private boolean up=false;
	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture("back.png");
		the = new Texture("running.png");
		ground = new Texture("ground.png");
		TextureRegion[][] tmp=TextureRegion.split(the,420,504);
		//TextureRegion[][] tmp2=TextureRegion.split(img,2000,1250);

		frames= new TextureRegion[9];

		int i=0;

		for(int x=0; x<9; x++){
			frames[i]=tmp[0][x];
			i++;
		}

		a=new Animation(1f/18f,frames);

		/*back= new TextureRegion[44];
		i=0;

		for(int x=0; x<3; x++){
			for(int y=0;y<10;y++) {
				back[i] = tmp2[x][y];
				i++;
			}
		}

		b=new Animation(1f/1f,back);*/

	}

	@Override
	public void render () {
		time+= Gdx.graphics.getDeltaTime();
		if(up==true && b<1){
			b+=.001;
		}else if(up==true){
			g+=.001;
		}else if(up==false && g>0){
			g-=.001;
		}else if(up==false){
			b-=.001;
		}
		if(b>=1 && g>=.7){
			up=false;
		}
		if(b<=0 && g<=0){
			up=true;
		}
		Gdx.gl.glClearColor((float)0, (float)g, b, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (Gdx.input.isTouched()) {
			if (Gdx.input.getX() < Gdx.graphics.getWidth() / 2){
				move-=1.5;
			} else {
				move+=1.5;
			}
		}
		batch.begin();
		batch.draw(ground,0,0);
        //batch.draw((TextureRegion)b.getKeyFrame(time,true), (float)0, (float)0);
		batch.draw((TextureRegion)a.getKeyFrame(time,true), (float)0+move, (float)0);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		ground.dispose();
	}
}
