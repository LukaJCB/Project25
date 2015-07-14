package com.ltj.game.projectPlatform;

import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;


import com.ltj.java.engine.JoglParticleEmitter;
import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.Engine;
import com.ltj.shared.engine.RenderObject;
import com.ltj.shared.engine.Sprite;
import com.ltj.shared.engine.SpriteSheet;
import com.ltj.shared.engine.primitives.Globals;
import com.ltj.shared.engine.primitives.ObjectPool;
import com.ltj.shared.engine.primitives.Scene;
public class CharacterController extends Behaviour<SpriteSheet> {
	
	private float speed, upSpeed;
	private boolean grounded;
	private float gravity;
	private boolean jumping;
	private ObjectPool bullets;
	private boolean doubleJumped;
	private float maxSpeed;
	private float lastColliderX;
	private boolean movingRight;
	
	@Override
	public void start() {
		gravity = Globals.getFloat("gravity");
		bullets = Globals.getObjectPool("bullets");
		Camera.setDistance(7f);
		maxSpeed = -0.31f;
	}

	@Override
	public void update() {
	
		if (jumping){

			jumping = false;
			grounded = false;
			upSpeed = 0.22f;
		}
		if (grounded){
			upSpeed = -gravity*2;
		} else {
			lastColliderX = 0;
			if (upSpeed > maxSpeed){
				upSpeed -= gravity;
			}
		}
	
		gameObject.translate(speed, upSpeed);
		Camera.setLookAt(gameObject.getX(), gameObject.getY()+0.4f);
		grounded = false;
		if (movingRight){
			try {
				Scene.load();
				movingRight = false;
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}	
	}


	@Override
	public void onKeyInput(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT){
			speed = 0.1f;
			gameObject.setMirroring(false,false);
			gameObject.startAnimation("walk");
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT){
			speed = -0.1f;
			gameObject.setMirroring(true,false);
			gameObject.startAnimation("walk");
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE){
			if (grounded){
				jumping = true;
			} else if(!doubleJumped) {
				doubleJumped = true;
				jumping = true;
			}
		}
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT){
			speed = 0;
			gameObject.stopAnimation();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT){
			speed = 0;
			gameObject.stopAnimation();
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE){
			jumping = false;
		} else if (e.getKeyCode() == KeyEvent.VK_SHIFT){
			Sprite rocket = bullets.create();
			
			rocket.setPosition(gameObject.getX(), gameObject.getY());
			rocket.setRotation(90);
			rocket.setInactive(false);
			System.out.println(Engine.parseAll());
//			movingRight = true;
//			System.out.println(((RenderObject) gameObject).toJSON());
//			System.out.println(((RenderObject) gameObject.getChildList().get(0)).toJSON());
		}
	}


	@Override
	public void onCollision(Sprite collider) {
		if (collider.compareTag("ground")){
			if ((gameObject.getY() -upSpeed - gameObject.getHeight()*0.3f) > collider.getY() + collider.getHeight()/2){
				//collision is below
				grounded = true;
				doubleJumped = false;
				gameObject.setPosition(gameObject.getX(), collider.getY()+collider.getHeight()/2 +gameObject.getHeight()/2);

				Camera.setLookAt(gameObject.getX(), gameObject.getY()+0.4f);
				
			} else if ((gameObject.getY() -upSpeed + gameObject.getHeight()*0.3) < collider.getY() - collider.getHeight()/2){
				//collision is above 
				gameObject.setPosition(gameObject.getX(), collider.getY()-collider.getHeight()/2 -gameObject.getHeight()/2);
			
				upSpeed = 0;
				Camera.setLookAt(gameObject.getX(), gameObject.getY()+0.4f);
				
			} else if ((gameObject.getX() - speed - gameObject.getWidth()*0.3f) > collider.getX() + collider.getWidth()/2){
				//collision is to the right
				gameObject.setPosition(collider.getX()+ collider.getWidth()/2 + gameObject.getWidth()/2,gameObject.getY());
				
				Camera.setLookAt(gameObject.getX(), gameObject.getY()+0.4f);
				
			} else if ((gameObject.getX() - speed + gameObject.getWidth()*0.3f) < collider.getX() - collider.getWidth()/2){
				//collision is to the left
				gameObject.setPosition(collider.getX()- collider.getWidth()/2 - gameObject.getWidth()/2,gameObject.getY());
				
				Camera.setLookAt(gameObject.getX(), gameObject.getY()+0.4f);
			} 
		} else if (collider.compareTag("enemy") || collider.compareTag("hazard")){
			gameObject.getParticleEmitterList().get(0).addParticleExplosion(90,0.01f);
			gameObject.setInactive(true);
		} else if (collider.compareTag("moving")){
			
			
			if ((gameObject.getY() -upSpeed - gameObject.getHeight()*0.3f) > collider.getY() + collider.getHeight()/2){
				//collision is below
				grounded = true;
				doubleJumped = false;
				float deltaX = 0;
				if (lastColliderX != 0){
					deltaX = collider.getX() - lastColliderX;
				}
				lastColliderX = collider.getX();
				
				gameObject.setPosition(gameObject.getX() + deltaX, collider.getY()+collider.getHeight()/2 +gameObject.getHeight()/2);

				
				Camera.setLookAt(gameObject.getX(), gameObject.getY()+0.4f);
				
				
			} else if ((gameObject.getY() -upSpeed + gameObject.getHeight()*0.3) < collider.getY() - collider.getHeight()/2){
				//collision is above 
				gameObject.setPosition(gameObject.getX(), collider.getY()-collider.getHeight()/2 -gameObject.getHeight()/2);
				
				upSpeed = 0;
				Camera.setLookAt(gameObject.getX(), gameObject.getY()+0.4f);
				
			} else if ((gameObject.getX() - speed - gameObject.getWidth()*0.3f) > collider.getX() + collider.getWidth()/2){
				//collision is to the right
				gameObject.setPosition(collider.getX()+ collider.getWidth()/2 + gameObject.getWidth()/2,gameObject.getY());
				
				Camera.setLookAt(gameObject.getX(), gameObject.getY()+0.4f);
				
			} else if ((gameObject.getX() - speed + gameObject.getWidth()*0.3f) < collider.getX() - collider.getWidth()/2){
				//collision is to the left
				gameObject.setPosition(collider.getX()- collider.getWidth()/2 - gameObject.getWidth()/2,gameObject.getY());
				
				Camera.setLookAt(gameObject.getX(), gameObject.getY()+0.4f);
			} 
		}
		
	}

	
	
	
	
	
	
	
}
