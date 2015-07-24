package com.ltj.game.disper;

import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Sprite;

public class TriggerBehaviour extends Behaviour<Sprite> {

	@Override
	public void start() {
		
	}

	@Override
	public void update() {
		
		gameObject.getChildList().get(0).setInactive(true);
		
	}

	
	public void onCollision(Sprite collider){
		if (collider.compareTag("player")){
			gameObject.getChildList().get(0).setInactive(false);
			gameObject.setControllerDisabled(true);
			collider.setInactive(true);
		}
	}
}
