package com.ltj.game;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;

import com.ltj.android.engine.AndroidRenderer;
import com.ltj.android.engine.SheetSpriteModeSAndroid;
import com.ltj.android.engine.SimpleSpriteAndroid;
import com.ltj.android.engine.SimpleSpriteModeSAndroid;
import com.ltj.projectengine.R;
import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Updater;
import com.ltj.shared.engine.primitives.BoxCollider;

public class GameRenderer extends AndroidRenderer {


	public GameRenderer(Context c, boolean motion, boolean tilt) {
		super(c, motion,tilt);
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		super.onSurfaceCreated(gl, config);
		SimpleSpriteAndroid map = new SimpleSpriteAndroid(R.drawable.background);
		map.scale(10, 24);
		
		addRenderable(map);
		
		SheetSpriteModeSAndroid hero = new SheetSpriteModeSAndroid(R.drawable.spritesheet_hero, 3,4);
		hero.addBehaviourName("PlayerController");
		String className = hero.getBehaviourName();
		Behaviour b = null;
		try {
			Class<?> c = Class.forName("com.ltj.game." + className);
			Constructor<?> constructor = c.getConstructors()[0];
			b = (Behaviour) constructor.newInstance((Object[]) null);
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
		hero.getCollider(0).setScaling(0.5f, 0.5f);
		
		
		addMSRenderable(hero);
		
		
		SimpleSpriteModeSAndroid enemy = new SimpleSpriteModeSAndroid(R.drawable.enemy);
		enemy.translate(0, 1.5f);
		enemy.addCollider(new BoxCollider());
		Updater.addId("Enemy", enemy);

		addMSRenderable(enemy);

		start();
	}

	


	public void onBackPressed() {
		super.changeMode();
	}


	
	
	


}
