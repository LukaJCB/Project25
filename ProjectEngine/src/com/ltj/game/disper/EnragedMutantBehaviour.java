package com.ltj.game.disper;

import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Sprite;
import com.ltj.shared.engine.SpriteSheet;

public class EnragedMutantBehaviour extends Behaviour<SpriteSheet> {

	private boolean movingRight, enraged;
	private float speed;
	
	
	
	
	@Override
	public void start() {
		speed = 0.07f;
		gameObject.setMirroring(true, false);
	}

	@Override
	public void update() {
		if (enraged){
			gameObject.startAnimation("walk");
			gameObject.getAnimation("walk").setAnimationTime(4);
			
			if (movingRight){
				gameObject.translate(speed, 0);

			} else {
				gameObject.translate(-speed, 0);

			}
			enraged = false;
		} else {
			gameObject.startAnimation("idle");
		}
		
		
	}
	
	public void onChildCollision(Sprite child, Sprite collider){
		if (collider.compareTag("player")){
			enraged = true;
		}
	}

	
	
	@Override
	public void onCollision(Sprite collider) {
		if (collider.compareTag("changeDirection")){
			gameObject.setMirroring(movingRight, false);
			movingRight = !movingRight;
		}
	}
	
	
	

}
