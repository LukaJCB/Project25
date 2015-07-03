package com.ltj.game.projectPlatform;

import java.awt.event.KeyEvent;


import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.GameObject;
import com.ltj.shared.engine.RenderObject;
import com.ltj.shared.engine.SheetSprite;
import com.ltj.shared.engine.primitives.ObjectPool;
public class CharacterController extends Behaviour<SheetSprite> {
	
	private float speed, upSpeed;
	private boolean grounded;
	private float gravity;
	private boolean jumping;
	private ObjectPool bullets;
	public RenderObject rocket;
	
	@Override
	public void start() {
		gravity = 0.013f;
		bullets = new ObjectPool(20, rocket);
		Camera.setDistance(7.2f);
	}

	@Override
	public void update() {
		if (jumping){
			jumping = false;
			grounded = false;
			upSpeed = 0.22f;
		}
		if (grounded){
			upSpeed = -gravity;
		} else {
			upSpeed -= gravity;
		}
		
		gameObject.translate(speed, upSpeed);
		Camera.setLookAt(gameObject.getX(), gameObject.getY()+0.4f);
		grounded = false;
		
	}


	@Override
	public void onKeyInput(KeyEvent e) {
		if (e.getKeyCode() ==KeyEvent.VK_RIGHT){
			speed = 0.1f;
		} else if (e.getKeyCode() ==KeyEvent.VK_LEFT){
			speed = -0.1f;
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE){
			jumping = true;
		}
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		if (e.getKeyCode() ==KeyEvent.VK_RIGHT){
			speed = 0;
		} else if (e.getKeyCode() ==KeyEvent.VK_LEFT){
			speed = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE){
			jumping = false;
		} else if (e.getKeyCode() == KeyEvent.VK_SHIFT){
			GameObject rocket = bullets.create();
			
			
			rocket.setPosition(gameObject.getX(), gameObject.getY());
			rocket.setRotation(90);
			rocket.setInactive(false);
		}
	}


	@Override
	public void onCollision(GameObject collider) {
		if (collider.compareTag("ground")){
			if ((gameObject.getY() -upSpeed - gameObject.getHeight()*0.3f) > collider.getY() + collider.getHeight()/2){
				//collision is below
				grounded = true;
				gameObject.setPosition(gameObject.getX(), collider.getY()+collider.getHeight()/2 +gameObject.getHeight()/2);
				
			} else if ((gameObject.getY() -upSpeed + gameObject.getHeight()*0.3) < collider.getY() - collider.getHeight()/2){
				//collision is above 
				gameObject.setPosition(gameObject.getX(), collider.getY()-collider.getHeight()/2 -gameObject.getHeight()/2);
				upSpeed = 0;
				
			} else if ((gameObject.getX() - speed - gameObject.getWidth()*0.3f) > collider.getX() + collider.getWidth()/2){
				//collision is to the right
				speed = 0;
				gameObject.setPosition(collider.getX()+ collider.getWidth()/2 + gameObject.getWidth()/2,gameObject.getY());
				
			} else if ((gameObject.getX() - speed + gameObject.getWidth()*0.3f) < collider.getX() - collider.getWidth()/2){
				//collision is to the left
				speed = 0;
				gameObject.setPosition(collider.getX()- collider.getWidth()/2 - gameObject.getWidth()/2,gameObject.getY());
			} 
		}
		
	}

	
	
	
	
	
	
	
}
