package com.ltj.game.projectPlatform;

import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Sprite;

public class MovingPlatform extends Behaviour<Sprite> {

	private boolean movingRight;
	private float speed;
	private float distance;
	private float startPosX;
	
	@Override
	public void start() {
		distance = 8;
		speed = 0.08f;
		startPosX = gameObject.getX();
	}

	@Override
	public void update() {
		if (movingRight){
			gameObject.translate(speed, 0);
			if (gameObject.getX() > startPosX){
				movingRight = false;
			}
		} else {
			gameObject.translate(-speed, 0);
			if (gameObject.getX() < startPosX - distance){
				movingRight = true;
			}
		}
	}
	
	

}
