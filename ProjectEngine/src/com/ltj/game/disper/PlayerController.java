package com.ltj.game.disper;

import java.awt.event.KeyEvent;
import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.Sprite;
import com.ltj.shared.engine.SpriteSheet;
import com.ltj.shared.engine.primitives.RunTimeGlobals;
public class PlayerController extends Behaviour<SpriteSheet> {
	
	private float speed, upSpeed;
	private boolean grounded;
	private float gravity;
	private boolean jumping;
	private float maxSpeed;
	private float lastColliderX;
	private float lastColliderY;
	private int lastColliderId;
	
	@Override
	public void start() {
		gravity = RunTimeGlobals.getFloat("gravity");
		Camera.setDistance(7f);
		maxSpeed = -0.31f;
	}

	@Override
	public void update() {
		if (jumping){

			jumping = false;
			grounded = false;
			upSpeed = 0.27f;
		}
		if (grounded){
			upSpeed = -gravity*4;
		} else {
			lastColliderX = 0;
			if (upSpeed > maxSpeed){
				upSpeed -= gravity;
			}
		}
	
		gameObject.translate(speed, upSpeed);
		Camera.setLookAt(gameObject.getX(), gameObject.getY()+0.4f);
		grounded = false;
		
	}


	@Override
	public void onKeyInput(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT){
			speed = 0.1f;
			gameObject.setMirroring(false,false);
			gameObject.startAnimation("walk");
			gameObject.getAnimation("walk").setLooping(true);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT){
			speed = -0.1f;
			gameObject.setMirroring(true,false);
			gameObject.startAnimation("walk");
			gameObject.getAnimation("walk").setLooping( true);
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE){
			
			if (grounded){
				jumping = true;
			}
		}
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT){
			speed = 0;
			gameObject.getAnimation("walk").setLooping( false);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT){
			speed = 0;
			gameObject.getAnimation("walk").setLooping( false);
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE){
			jumping = false;
			
		} 
	}


	@Override
	public void onCollision(Sprite collider) {
		if (collider.compareTag("ground")){
			if ((gameObject.getY() -upSpeed - gameObject.getHeight()*0.3f) > collider.getY() + collider.getHeight()/2){
				//collision is below
				grounded = true;
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
			die();
		} else if (collider.compareTag("moving")){
			
			if ((gameObject.getY() -upSpeed - gameObject.getHeight()*0.3f) > collider.getY() + collider.getHeight()/2){
				//collision is below
				grounded = true;
				float deltaX = 0;
				if (lastColliderX != 0 && collider.getId() == lastColliderId){
					deltaX = collider.getX() - lastColliderX;
				}
				lastColliderX = collider.getX();
				lastColliderId = collider.getId();
				
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
		} else if (collider.compareTag("raising")){
			if ((gameObject.getY() -upSpeed - gameObject.getHeight()*0.1f) > collider.getY() + collider.getHeight()*0.1f){
				//collision is below
				grounded = true;
				float deltaY = 0;
				if (lastColliderX != 0){
					deltaY = collider.getY() - lastColliderY;
				}
				lastColliderY = collider.getY();
				upSpeed = 0;
				
				gameObject.setPosition(gameObject.getX(),deltaY+ collider.getY()+collider.getHeight()/2 +gameObject.getHeight()/2);
				
				
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
		} else if (collider.compareTag("trigger")){
			
		}
		
		
	}
	
	public void die(){
		gameObject.getParticleEmitterList().get(0).addParticleExplosion(90,0.01f);
		gameObject.setInactive(true);
	}

	
	
	
	
	
	
	
}
