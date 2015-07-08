package com.ltj.game.projectPlatform;

import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.SimpleSprite;
import com.ltj.shared.engine.Sprite;

public class RaisingPlatform extends Behaviour<SimpleSprite> {

	private float raiseSpeed;
	private boolean raising;
	
	@Override
	public void start() {
		raiseSpeed = 0.08f;
		
	}

	@Override
	public void update() {
		if (raising){
			gameObject.translate(0, raiseSpeed);
		}
	}

	@Override
	public void onCollision(Sprite collider) {
		if (collider.compareTag("hero")){
			raising = true;
		}
	}
	
	

}
