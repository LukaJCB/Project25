package com.ltj.game;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;

import com.ltj.android.engine.AndroidParticleEmitter;
import com.ltj.android.engine.AndroidRenderer;
import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.HudElement;
import com.ltj.shared.engine.ModeSevenObject;
import com.ltj.shared.engine.ParticleEmitter;
import com.ltj.shared.engine.SimpleSprite;
import com.ltj.shared.engine.SimpleSpriteModeS;
import com.ltj.shared.engine.Skybox;
import com.ltj.shared.engine.SoundManager;
import com.ltj.shared.engine.Updater;
import com.ltj.shared.engine.primitives.BoxCollider;
import com.ltj.shared.engine.primitives.Globals;
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
		
		SimpleSprite r = new SimpleSprite("img/racer_background.png");
		r.scale(20, 20);
		Updater.addRenderable(r);
		Updater.setCollisionZone(new Rectangle(r.getX(), r.getY(), r.getWidth(), r.getHeight()));
		SimpleSprite[] s = new SimpleSprite[30];
		for (int i = 0;i < s.length;i++){
			s[i] = new SimpleSprite("img/car.png");
			s[i].addCollider(new BoxCollider());
			Updater.addRenderable(s[i]);
		}
		
				
		SimpleSpriteModeS hero = new SimpleSpriteModeS( "img/car.png");
		Camera.addSkyBox(new Skybox( "img/skyboxtop.png","img/skyboxbottom.png","img/skyboxfront.png",
				"img/skyboxback.png","img/skyboxright.png","img/skyboxleft.png"));
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
		hero.scale(0.4f, 0.3f);
	
		
		
		addMSRenderable(hero);
		
		
		SimpleSpriteModeS sp3 = new SimpleSpriteModeS("img/car.png");
		sp3.translate(0, 5);
		sp3.scale(0.4f, 0.3f);
		sp3.addCollider(new BoxCollider());
		Behaviour<SimpleSprite> b2 = new Behaviour<SimpleSprite>(){
			
		
			@Override
			public void start() {
				
			}

			@Override
			public void update() {
				gameObject.setRotation(Globals.getFloat("rotation"));
					
			}

			
			
		};
		
		b2.allocateObject(sp3);
		sp3.addBehaviour(b2);
		sp3.setTag("ene");
		SimpleSpriteModeS sp4 = new SimpleSpriteModeS("img/car.png");
		Behaviour<SimpleSprite> b3 = new Behaviour<SimpleSprite>(){
			
			
			@Override
			public void start() {
				
			}

			@Override
			public void update() {
				gameObject.setRotation(Globals.getFloat("rotation"));
					
			}

			
			
		};
		b3.allocateObject(sp4);
		sp4.addBehaviour(b3);
		sp4.scale(0.4f, 0.3f);
		sp4.translate(-6, 2);
		Updater.addMSRenderable((ModeSevenObject) sp4);
		Updater.addMSRenderable(sp3);
		HudElement e = new HudElement("img/ic_launcher.png");
		
		e.scale(0.1f, 0.1f);
		e.setPosition(0.9f, 0);
		addHudElement("gui",e);
		changeMode();
		
		
		start();
	}

	


	public void onBackPressed() {
		super.changeMode();
	}


	
	
	


}
