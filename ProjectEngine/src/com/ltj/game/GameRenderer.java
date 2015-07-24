package com.ltj.game;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;

import com.ltj.android.engine.AndroidRenderer;
import com.ltj.android.engine.AndroidSprite;
import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.Engine;
import com.ltj.shared.engine.HudElement;
import com.ltj.shared.engine.Skybox;
import com.ltj.shared.engine.SoundManager;
import com.ltj.shared.engine.primitives.BoxCollider;
import com.ltj.shared.engine.primitives.RunTimeGlobals;
import com.ltj.shared.engine.primitives.Rectangle;

public class GameRenderer extends AndroidRenderer {


	public GameRenderer(Context c, boolean motion, boolean tilt) {
		super(c, motion,tilt);
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		super.onSurfaceCreated(gl, config);
		SoundManager.initSoundManager(true);
		
		AndroidSprite r = new AndroidSprite("img/racer_background.png", 1, 1);
		r.scale(20, 20);
		Engine.addRenderable(r);
		Engine.setCollisionZone(new Rectangle(r.getX(), r.getY(), r.getWidth(), r.getHeight()));
		AndroidSprite[] s = new AndroidSprite[30];
		for (int i = 0;i < s.length;i++){
			s[i] = new AndroidSprite("img/car.png", 1, 1);
			s[i].addCollider(new BoxCollider());
			Engine.addRenderable(s[i]);
		}
		
				
		AndroidSprite hero = new AndroidSprite( "img/car.png", 1, 1);
		Camera.addSkyBox(new Skybox( "img/skyboxtop.png","img/skyboxbottom.png","img/skyboxfront.png",
				"img/skyboxback.png","img/skyboxright.png","img/skyboxleft.png",Engine.ANDROID));
		hero.addBehaviour(new PlayerController25D());
		String className = hero.getBehaviourName();
		Behaviour b = null;
		try {
			Class<?> c = Class.forName(className);
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
		hero.scale(0.4f, 0.3f);
		hero.setModeSevenEnabled(true);
	
		
		
		addRenderable(hero);
		
		
		AndroidSprite sp3 = new AndroidSprite("img/car.png", 1, 1);
		sp3.translate(0, 5);
		sp3.scale(0.4f, 0.3f);
		sp3.addCollider(new BoxCollider());
		Behaviour<AndroidSprite> b2 = new Behaviour<AndroidSprite>(){
			
		
			@Override
			public void start() {
				
			}

			@Override
			public void update() {
				gameObject.setRotation(RunTimeGlobals.getFloat("rotation"));
					
			}

			
			
		};
		
		b2.allocateObject(sp3);
		sp3.addBehaviour(b2);
		sp3.setTag("ene");
		AndroidSprite sp4 = new AndroidSprite("img/car.png", 1, 1);
		Behaviour<AndroidSprite> b3 = new Behaviour<AndroidSprite>(){
			
			
			@Override
			public void start() {
				
			}

			@Override
			public void update() {
				gameObject.setRotation(RunTimeGlobals.getFloat("rotation"));
					
			}

			
			
		};
		b3.allocateObject(sp4);
		sp4.addBehaviour(b3);
		sp4.scale(0.4f, 0.3f);
		sp4.translate(-6, 2);
		Engine.addRenderable( sp4);
		Engine.addRenderable(sp3);
		HudElement e = new HudElement("img/ic_launcher.png",Engine.ANDROID);
		
		e.scale(0.1f, 0.1f);
		e.setPosition(0.9f, 0);
		addHudElement("gui",e);
		
		
		Engine.start();
	}

	


	public void onBackPressed() {
		super.changeMode();
	}


	
	
	


}
