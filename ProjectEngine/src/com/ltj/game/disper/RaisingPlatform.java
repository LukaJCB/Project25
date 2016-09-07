package com.ltj.game.disper;

import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.SingleSprite;
import com.ltj.shared.engine.Sprite;

public class RaisingPlatform extends Behaviour<SingleSprite> {

	private float raiseSpeed;
	private boolean raising;
	private int counter;
	private boolean changeDirection;
	public int delay;
	
	@Override
	public void start() {
		raiseSpeed = 0.05f;
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
