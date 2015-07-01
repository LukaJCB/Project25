package com.ltj.game;

import java.awt.event.KeyEvent;

import com.jogamp.opengl.GLAutoDrawable;
import com.ltj.java.engine.JoglParticleEmitter;
import com.ltj.java.engine.JoglRenderer;
import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.EmptyObject;
import com.ltj.shared.engine.GameObject;
import com.ltj.shared.engine.HudElement;
import com.ltj.shared.engine.RenderObject;
import com.ltj.shared.engine.SheetSpriteModeS;
import com.ltj.shared.engine.SimpleSprite;
import com.ltj.shared.engine.SimpleSpriteModeS;
import com.ltj.shared.engine.Skybox;
import com.ltj.shared.engine.SoundManager;
import com.ltj.shared.engine.Updater;
import com.ltj.shared.engine.primitives.BoxCollider;
import com.ltj.shared.engine.primitives.Globals;
import com.ltj.shared.engine.primitives.Rectangle;

public class JoglGameRenderer extends JoglRenderer {

	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		SoundManager.initSoundManager(false);

		Camera.addSkyBox(new Skybox(gl, "assets/img/skyboxtop.png","assets/img/skyboxbottom.png","assets/img/skyboxfront.png",
				"assets/img/skyboxback.png","assets/img/skyboxright.png","assets/img/skyboxleft.png"));
		

		RenderObject r = new EmptyObject(); 
		r.scale(30, 30);
		Updater.addRenderable(r);
		Updater.setCollisionZone(new Rectangle(r.getX(), r.getY(), r.getWidth(), r.getHeight()));

		final JoglParticleEmitter pe = new JoglParticleEmitter(gl, 1000, 1.0f,0f,0);
		Updater.addParticleEmitter(pe);

		

//		SimpleSpriteModeS[] s = new SimpleSpriteModeS[10];
//		for (int i = 0;i < s.length;i++){
//			s[i] = new SimpleSpriteModeS(gl,"assets/img/car.png");
//			s[i].translate(-15 +i *2.5f,2);
//			s[i].addCollider(new BoxCollider());
//			Updater.addRenderable(s[i]);
//		}
		
		
		SheetSpriteModeS hero = new SheetSpriteModeS(gl, "assets/img/ship.png",1,1);
		hero.scale(0.4f, 0.9f);
		hero.setTexture(0, 0);
		Behaviour<SheetSpriteModeS> b = new Behaviour<SheetSpriteModeS>(){

			private float speed, rotation;
			private boolean moving;

			@Override
			public void start() {
				moving = true;
			}

			
			public void onCollision(GameObject c){
				
				pe.addParticleExplosion(60, 0.0001f);
				setMovement(-2 *speed *(float)-Math.sin(Math.toRadians(gameObject.getRotation())), 
						-2 *speed *(float)Math.cos(Math.toRadians(gameObject.getRotation())));
				
			}
			


			@Override
			public void update() {
				if (moving){
					gameObject.rotate(rotation);
					Globals.add("rotation", gameObject.getRotation());
					setMovement(speed *(float)-Math.sin(Math.toRadians(gameObject.getRotation())), 
							speed *(float)Math.cos(Math.toRadians(gameObject.getRotation())));

					Camera.setLookAt(gameObject.getX(), gameObject.getY());
				}
			}

			private void setMovement(float f, float cos) {
				gameObject.translate(f, cos);
				pe.translate(f, cos, 0);
			}

			@Override
			public void onKeyInput(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP){
					speed = 0.08f;
				} else if (e.getKeyCode() ==KeyEvent.VK_DOWN){
					speed = -0.04f;
				}  else if (e.getKeyCode() ==KeyEvent.VK_RIGHT){
					rotation = -1;
				}  else if (e.getKeyCode() ==KeyEvent.VK_LEFT){
					rotation = 1;
				}
			}

			@Override
			public void onKeyRelease(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP){
					speed = 0;
				} else if (e.getKeyCode() ==KeyEvent.VK_DOWN){
					speed = 0;
				}  else if (e.getKeyCode() ==KeyEvent.VK_RIGHT){
					rotation = 0;
				}  else if (e.getKeyCode() ==KeyEvent.VK_LEFT){
					rotation = 0;
				}
			}
			

		
			
			
			
		};
		hero.addCollider(new BoxCollider());
		hero.addBehaviour(b);
		b.allocateObject(hero);
		hero.scale(0.5f, 0.5f);
		hero.setTag("hero");
		
		EmptyObject zone = new EmptyObject();
		//zone.addCollider(new BoxCollider());
		zone.setParent(hero);
		zone.setTag("zon");
		//Updater.addRenderable(zone);
		Updater.addMSRenderable(hero);
		
		SimpleSpriteModeS sp3 = new SimpleSpriteModeS(gl, "assets/img/blue.png");
		sp3.translate(0, 5);
		//sp3.scale(4f, 3f);
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
		SimpleSpriteModeS sp4 = new SimpleSpriteModeS(gl, "assets/img/redship.png");
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
		sp4.addCollider(new BoxCollider());
		Updater.addMSRenderable( sp4);
		Updater.addMSRenderable(sp3);
		HudElement e = new HudElement(gl, "assets/img/ic_launcher.png");
		
		e.scale(0.1f, 0.1f);
		e.setPosition(0.9f, 0);
		addHudElement("gui",e);
		changeMode();
		
		
		Updater.start();
	}
	
	
	
}
