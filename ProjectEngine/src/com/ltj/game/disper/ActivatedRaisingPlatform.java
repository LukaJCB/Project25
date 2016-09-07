package com.ltj.game.disper;

import com.ltj.shared.engine.Sprite;
import com.ltj.shared.engine.primitives.SaveStateConstants;

public class ActivatedRaisingPlatform extends RaisingPlatform {

	public String identifier;
	private boolean started;

	@Override
	public void start() {
		super.start();
		if (SaveStateConstants.getBool(identifier)){
			started = true;
		}
	}

	@Override
	public void update() {
		if (started){
			super.update();
		}
	}

	@Override
	public void onChildCollision(Sprite child, Sprite collider) {
		if (!started){
			started = true;
			SaveStateConstants.add(identifier, true);

		}
	}	
	
}
