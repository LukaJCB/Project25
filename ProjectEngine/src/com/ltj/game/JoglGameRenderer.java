package com.ltj.game;

import java.awt.event.KeyEvent;

import com.jogamp.opengl.GLAutoDrawable;
import com.ltj.java.engine.JoglRenderer;
import com.ltj.java.engine.KeyInput;
import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.EmptyObject;
import com.ltj.shared.engine.RenderObject;
import com.ltj.shared.engine.SimpleSprite;
import com.ltj.shared.engine.SimpleSpriteModeS;
import com.ltj.shared.engine.Skybox;
import com.ltj.shared.engine.SoundManager;
import com.ltj.shared.engine.Updater;
import com.ltj.shared.engine.primitives.BoxCollider;
import com.ltj.shared.engine.primitives.Globals;

public class JoglGameRenderer extends JoglRenderer {

	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		SoundManager.initSoundManager(false);

		Camera.addSkyBox(new Skybox(gl, "assets/img/sky.png","assets/img/sky.png","assets/img/sky.png","assets/img/sky.png","assets/img/sky.png","assets/img/sky.png"));
		
//		ArrayList<RenderObject> ar = new ArrayList<RenderObject>(25);
//		for (int i = 0; i < 25;i++){
//			if (i == 17 || i == 7){
//				ar.add(new SimpleSprite(gl,"assets/img/18_texture.png"));
//				ar.get(i).rotate(90);
//			} else if (i == 11 || i == 13){
//				ar.add(new SimpleSprite(gl,"assets/img/5_texture.png"));
//			} else if (i == 6){
//				ar.add(new SimpleSprite(gl,"assets/img/4_texture.png"));
//				ar.get(i).rotate(90);
//			} else if (i == 8){
//				ar.add(new SimpleSprite(gl,"assets/img/3_texture.png"));
//				ar.get(i).rotate(180);
//			} else if (i == 16){
//				ar.add(new SimpleSprite(gl,"assets/img/3_texture.png"));
//				ar.get(i).rotate(90);
//			} else if (i == 18){
//				ar.add(new SimpleSprite(gl,"assets/img/3_texture.png"));
//				ar.get(i).rotate(0);
//			} else {
//				ar.add(new SimpleSprite(gl,"assets/img/1_texture.png"));
//			}
//			ar.get(i).scale(5, 5);
//			ar.get(i).translate((i % 5)*5, i );
//		}
//		
//		Updater.addRenderableList(ar);
		RenderObject r = new SimpleSprite(gl,"assets/img/racer_background.png");
		r.scale(20, 20);
		Updater.addRenderable(r);
	

		SimpleSpriteModeS hero = new SimpleSpriteModeS(gl, "assets/img/car.png");
		Behaviour<SimpleSpriteModeS> b = new Behaviour<SimpleSpriteModeS>(){

			private float speed;

			@Override
			public void start() {
				
			}

			@Override
			public void update() {
				if (KeyInput.getEvent() != null && KeyInput.getEvent().getKeyCode() == KeyEvent.VK_UP){
					speed = 0.08f;
				} else if (KeyInput.getEvent() != null && KeyInput.getEvent().getKeyCode() == KeyEvent.VK_DOWN){
					speed = 0;
				}  else if (KeyInput.getEvent() != null && KeyInput.getEvent().getKeyCode() == KeyEvent.VK_RIGHT){
					gameObject.rotate(-1f);
				}  else if (KeyInput.getEvent() != null && KeyInput.getEvent().getKeyCode() == KeyEvent.VK_LEFT){
					gameObject.rotate(1f);
				}
				Globals.add("rotation", gameObject.getRotation());
				setMovement(speed *(float)-Math.sin(Math.toRadians(gameObject.getRotation())), 
						speed *(float)Math.cos(Math.toRadians(gameObject.getRotation())));
				
				Camera.setRotateAround(gameObject.getX(), gameObject.getY(),gameObject.getRotation());
			}

			private void setMovement(float f, float cos) {
				gameObject.translate(f, cos);
				
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
				
						gameObject.setRotation(Globals.getFloat("rotation"));
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
