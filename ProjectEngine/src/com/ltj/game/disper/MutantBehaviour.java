package com.ltj.game.disper;

import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Sprite;
import com.ltj.shared.engine.SpriteSheet;

public class MutantBehaviour extends Behaviour<SpriteSheet> {
	
	
	private boolean movingRight, idle;
	private float speed;
	private int count;
	
	@Override
	public void start() {
		speed = 0.12f;
		gameObject.startAnimation("walk");
		movingRight = true;
	}

	@Override
	public void update() {
		if (!idle){
			if (movingRight){
				gameObject.translate(speed, 0);

			} else {
				gameObject.translate(-speed, 0);

			}
		}
		count++;

		if (count > 300){
			if (idle){
				gameObject.startAnimation("walk");
			} else {
				gameObject.startAnimation("idle");
			}
			idle = !idle;
			count = 0;
		}

	}
	
	
	
	@Override
	public void onCollision(Sprite collider) {
		if (collider.compareTag("changeDirection")){
			gameObject.setMirroring(movingRight, false);
			movingRight = !movingRight;
		} else if (!idle && collider.compareTag("player")){
			sendMessage(collider, "die");
		}
	}

}
