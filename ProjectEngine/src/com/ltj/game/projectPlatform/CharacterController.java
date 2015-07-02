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
		gravity = 0.016f;
		bullets = new ObjectPool(10, rocket);
		Camera.setDistance(7.2f);
	}

	@Override
	public void update() {
		if (!grounded){
			upSpeed -= gravity;
		} else {
			if (jumping){
				jumping = false;
				grounded = false;
				upSpeed = 0.22f;
			} else {
				upSpeed = 0;
			}
		}
		
		gameObject.translate(speed, upSpeed);
		Camera.setLookAt(gameObject.getX(), gameObject.getY()+0.4f);
		grounded = false;
		
		
	}


	@Override
	public void onKeyInput(KeyEvent e) {
		if (e.getKeyCode() ==KeyEvent.VK_RIGHT){
			speed = 0.14f;
		} else if (e.getKeyCode() ==KeyEvent.VK_LEFT){
			speed = -0.14f;
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
	public void onChildCollision(GameObject child, GameObject collider) {
		if (child.compareTag("bottomCollider")){
			if (collider.compareTag("ground")){
				grounded = true;
				gameObject.setPosition(gameObject.getX(), collider.getY()+collider.getHeight()/2 +gameObject.getHeight()/2);
			} 
		} 
	}
	
	
	
	
}
