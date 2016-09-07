package com.ltj.game.disper;

import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Sprite;
import com.ltj.shared.engine.SpriteSheet;

public class AnimatedRaisingPlatform extends Behaviour<SpriteSheet> {

	private float raiseSpeed;
	private boolean raising;
	private int counter;
	private boolean changeDirection;
	public int delay;
	
	@Override
	public void start() {
		raiseSpeed = 0.05f;
		gameObject.startAnimation("idle");
	}

	@Override
	public void update() {
		if (changeDirection){
			counter++;
			if (counter > delay){
				changeDirection = false;
				counter = 0;
			}
		} else {
			if (raising){
				gameObject.translate(0, raiseSpeed);
			} else {
				gameObject.translate(0, -raiseSpeed);
			}
		}
	}

	@Override
	public void onCollision(Sprite collider) {
		if (collider.compareTag("changeDirection")){
			changeDirection = true;
			raising = !raising;
			if (raising){
				gameObject.translate(0, raiseSpeed);
			} else {
				gameObject.translate(0, -raiseSpeed);
			}
		}
	}
	
	

}
