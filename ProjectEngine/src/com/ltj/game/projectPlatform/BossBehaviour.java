package com.ltj.game.projectPlatform;

import com.ltj.java.engine.JoglParticleEmitter;
import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Sprite;
import com.ltj.shared.engine.SpriteSheet;
import com.ltj.shared.engine.primitives.RunTimeGlobals;

public class BossBehaviour extends Behaviour<SpriteSheet> {

	private float left,right;
	private boolean movingRight;
	private float startPosX;
	private boolean grounded,jumping;
	private float upSpeed,gravity;
	private float speed;
	public JoglParticleEmitter pe;
	
	public void setMovingRight(boolean m){
		movingRight = m;
	}
	
	public void setDistance(float f){
		left = f;
		right = -f;
	}
	@Override
	public void start() {
		startPosX = gameObject.getX();
		left = -5;
		right = 5;
		speed = 0.12f;
		gameObject.setTexture(1, 3);
		
		gravity = RunTimeGlobals.getFloat("gravity");
	}

	@Override
	public void update() {
		
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
					gameObject.setTexture(1, 3);
					gameObject.getChildList().get(0).translate(-gameObject.getChildList().get(0).getWidth(), 0);
				}
			} else {
				gameObject.translate(-speed, 0);
				if (gameObject.getX() < startPosX + left){
					movingRight = true;
					gameObject.setTexture(1, 1);
					gameObject.getChildList().get(0).translate(gameObject.getChildList().get(0).getWidth(), 0);
				}
			}
		}
		pe.setPosition(gameObject.getX(), gameObject.getY(), 0);
		
		
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
				pe.setPosition(gameObject.getX(), gameObject.getY(), 0);
			} 
		} else if (collider.compareTag("bullet")){
			pe.addParticleExplosion(50, 0.02f);
		}
	}

}
