package com.ltj.game.projectPlatform;

import com.ltj.java.engine.JoglParticleEmitter;
import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Sprite;
import com.ltj.shared.engine.SpriteSheet;
import com.ltj.shared.engine.primitives.Globals;

public class EnemyBehaviour extends Behaviour<SpriteSheet> {

	private float left,right;
	private boolean movingRight;
	private float startPosX;
	private boolean grounded,jumping;
	private float upSpeed,gravity;
	private float speed;
	private int counter;
	private int texCol;
	
	
	
	
	@Override
	public void start() {
		startPosX = gameObject.getX();
		left = -5;
		right = 5;
		speed = 0.06f;
		gameObject.setTexture(1, 3);
		
		gravity = Globals.getFloat("gravity");
	}

	@Override
	public void update() {
		counter++;
		if (counter > 10){
			
			counter = 0;
			if (texCol == 0){
				texCol = 2;
			} else {
				texCol = 0;
			}
			
			if (movingRight){
				gameObject.setTexture(texCol, 1);
			} else {
				gameObject.setTexture(texCol, 3);
			}
		}
		
		if (jumping){
			jumping = false;
			grounded = false;
			upSpeed = 0.22f;
		}
		if (!grounded){
			upSpeed -= gravity;
			if (movingRight){
				gameObject.translate(speed * 0.5f, upSpeed);
			} else {
				gameObject.translate(-speed * 0.5f, upSpeed);
			}
		} else {
			upSpeed = -gravity;
			

			if (movingRight){
				gameObject.translate(speed, upSpeed);
				
				if (gameObject.getX() > startPosX + right){
					movingRight = false;
					gameObject.getChildList().get(0).translate(-gameObject.getChildList().get(0).getWidth(), 0);
				}
			} else {
				
				gameObject.translate(-speed, 0);
				if (gameObject.getX() < startPosX + left){
					movingRight = true;
					
					gameObject.getChildList().get(0).translate(gameObject.getChildList().get(0).getWidth(), 0);
				}
			}
		}
		
	}

	@Override
	public void onChildCollision(Sprite child, Sprite collider) {
		if (collider.compareTag("bullet") && grounded){
			jumping = true;
		}
	}
	
	@Override
	public void onCollision(Sprite collider) {
		if (collider.compareTag("ground")){
			if ((gameObject.getY() -upSpeed - gameObject.getHeight()*0.3f) > collider.getY() + collider.getHeight()/2){
				//collision is below
				grounded = true;
				gameObject.setPosition(gameObject.getX(), collider.getY()+collider.getHeight()/2 +gameObject.getHeight()/2);
			
			} 
		} 
	}
	
	
	

}
