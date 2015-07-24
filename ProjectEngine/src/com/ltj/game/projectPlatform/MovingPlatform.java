package com.ltj.game.projectPlatform;

import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Sprite;

public class MovingPlatform extends Behaviour<Sprite> {

	private boolean movingRight;
	private float speed;
	
	@Override
	public void start() {
		speed = 0.08f;
	}

	@Override
	public void update() {
		if (movingRight){
			gameObject.translate(speed, 0);
			
		} else {
			gameObject.translate(-speed, 0);
			
		}
	}

	@Override
	public void onCollision(Sprite collider) {
		if (collider.compareTag("changeDirection")){
			movingRight = !movingRight;
		}
	}
	
	
	
	

}
