package com.ltj.game.projectPlatform;

import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.GameObject;

public class BulletController extends Behaviour<GameObject> {

	private int counter;
	@Override
	public void start() {
		counter = 0;
	}

	@Override
	public void update() {

		gameObject.translate(0.3f, 0);
		counter++;
		if (counter > 30){
			this.gameObject.setInactive(true);
		}
		
	}

	@Override
	public void onCollision(GameObject collider) {
		if (collider.compareTag("ground")){
			this.gameObject.setInactive(true);
		} else if (collider.compareTag("enemy")){
			collider.setInactive(true);
			gameObject.setInactive(true);
		}
	}

}
