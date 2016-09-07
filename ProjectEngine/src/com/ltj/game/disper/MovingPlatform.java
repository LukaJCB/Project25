package com.ltj.game.disper;

import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Sprite;
import com.ltj.shared.engine.SpriteSheet;

public class MovingPlatform extends Behaviour<SpriteSheet> {

	public boolean movingRight;
	public float speed;
	private boolean changeDirection;
	private int counter;
	
	@Override
	public void start() {
		if (speed < 0.01f){
			speed = 0.05f;
		}
		gameObject.startAnimation("idle");
	}

	@Override
	public void update() {
		if (changeDirection){
			counter++;
			if (counter > 60){
				changeDirection = false;
				counter = 0;
			}
		} else {
		
			if (movingRight){
				gameObject.translate(speed, 0);
				
			} else {
				gameObject.translate(-speed, 0);
				
			}
		}
	}

	@Override
	public void onCollision(Sprite collider) {
		if (collider.compareTag("changeDirection")){
			changeDirection = true;
			movingRight = !movingRight;
			if (movingRight){
				gameObject.translate(speed, 0);
				
			} else {
				gameObject.translate(-speed, 0);
				
			}
		}
	}
	
	public void changeDirection(){
		movingRight = !movingRight;
	}
	
	
	
	

}
