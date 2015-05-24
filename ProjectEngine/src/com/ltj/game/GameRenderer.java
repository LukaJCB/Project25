package com.ltj.game;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;

import com.ltj.android.engine.AndroidRenderer;
import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.SheetSpriteModeS;
import com.ltj.shared.engine.SimpleSprite;
import com.ltj.shared.engine.SimpleSpriteModeS;
import com.ltj.shared.engine.Skybox;
import com.ltj.shared.engine.SoundManager;
import com.ltj.shared.engine.primitives.BoxCollider;

public class GameRenderer extends AndroidRenderer {


	public GameRenderer(Context c, boolean motion, boolean tilt) {
		super(c, motion,tilt);
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		super.onSurfaceCreated(gl, config);
		SoundManager.initSoundManager(true);
		
		SimpleSprite map = new SimpleSprite("img/background.png");
		map.scale(10, 24);
		
		addRenderable(map);
		Camera.addSkyBox(new Skybox("img/sky.png","img/sky.png","img/sky.png",
				"img/sky.png","img/sky.png","img/sky.png"));
		SheetSpriteModeS hero = new SheetSpriteModeS("img/spritesheet_hero.png", 3,4);
		hero.addBehaviourName("PlayerController25D");
		String className = hero.getBehaviourName();
		Behaviour b = null;
		try {
			Class<?> c = Class.forName("com.ltj.game." + className);
			Constructor<?> constructor = c.getConstructors()[0];
			b = (Behaviour) constructor.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		b.allocateObject(hero);
		hero.addBehaviour(b);
		hero.addCollider(new BoxCollider());
	
		
		
		addMSRenderable(hero);
		
		
		SimpleSpriteModeS enemy = new SimpleSpriteModeS("img/enemy.png");
		enemy.translate(0, 1.5f);
		enemy.addCollider(new BoxCollider());
		enemy.setTag("enemy");

		addMSRenderable(enemy);

		start();
	}

	


	public void onBackPressed() {
		super.changeMode();
	}


	
	
	


}
