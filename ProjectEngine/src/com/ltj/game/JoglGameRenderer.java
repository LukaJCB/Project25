package com.ltj.game;

import java.awt.event.KeyEvent;

import com.jogamp.opengl.GLAutoDrawable;
import com.ltj.java.engine.JoglRenderer;
import com.ltj.java.engine.KeyInput;
import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.EmptyObject;
import com.ltj.shared.engine.GameObject;
import com.ltj.shared.engine.SheetSpriteModeS;
import com.ltj.shared.engine.SimpleSprite;
import com.ltj.shared.engine.SimpleSpriteModeS;
import com.ltj.shared.engine.Skybox;
import com.ltj.shared.engine.SoundManager;
import com.ltj.shared.engine.Updater;
import com.ltj.shared.engine.primitives.BoxCollider;

public class JoglGameRenderer extends JoglRenderer {

	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		SoundManager.initSoundManager(false);

		Camera.addSkyBox(new Skybox(gl, "assets/img/sky.png","assets/img/sky.png","assets/img/sky.png","assets/img/sky.png","assets/img/sky.png","assets/img/sky.png"));
		
		SimpleSprite sp = new SimpleSprite(gl, "assets/img/background.png");
		sp.scale(10, 24);
		Updater.addRenderable(sp);

		SheetSpriteModeS hero = new SheetSpriteModeS(gl, "assets/img/spritesheet_hero.png",3,4);
		Behaviour<SheetSpriteModeS> b = new Behaviour<SheetSpriteModeS>(){

			@Override
			public void start() {
				gameObject.setTexture(2, 1);
				gameObject.translate(0, 0.01f);
				SoundManager.addShortClip("assets/test.wav");
			}

			@Override
			public void update() {
				if (KeyInput.getEvent() != null && KeyInput.getEvent().getKeyCode() == KeyEvent.VK_UP){
					gameObject.translate(0, 0.04f);
				} else if (KeyInput.getEvent() != null && KeyInput.getEvent().getKeyCode() == KeyEvent.VK_DOWN){
					gameObject.translate(0, -0.04f);
				}  else if (KeyInput.getEvent() != null && KeyInput.getEvent().getKeyCode() == KeyEvent.VK_RIGHT){
					gameObject.rotate(-1f);
				}  else if (KeyInput.getEvent() != null && KeyInput.getEvent().getKeyCode() == KeyEvent.VK_LEFT){
					gameObject.rotate(1f);
				}
				
				Camera.setRotateAround(gameObject.getX(), gameObject.getY(),gameObject.getRotation());
			}

			@Override
			public void onChildCollisionEnter(GameObject collider) {
				System.out.println("child");
			}

			@Override
			public void onCollisionEnter(GameObject collider) {
				System.out.println("me");
			}
			
		
			
			
			
		};
		hero.addCollider(new BoxCollider());
		hero.addBehaviour(b);
		b.allocateObject(hero);
		hero.scale(0.5f, 0.5f);
		hero.setTag("hero");
		
		EmptyObject zone = new EmptyObject();
		zone.addCollider(new BoxCollider());
		zone.setParent(hero);
		zone.setTag("zon");
		Updater.addRenderable(zone);
		Updater.addMSRenderable(hero);
		
		SimpleSpriteModeS sp3 = new SimpleSpriteModeS(gl, "assets/img/enemy.png");
		sp3.translate(0, 5);
		sp3.addCollider(new BoxCollider());
		Behaviour<SimpleSprite> b2 = new Behaviour<SimpleSprite>(){
			
		
			@Override
			public void start() {
				
			}

			@Override
			public void update() {
				
						
			}

			
			
		};
		b2.allocateObject(sp3);
		sp3.addBehaviour(b2);
		sp3.setTag("ene");
		Updater.addMSRenderable(sp3);
		changeMode();
		Updater.start();
	}
	
}
