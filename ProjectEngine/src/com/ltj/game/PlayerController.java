package com.ltj.game;

import android.view.MotionEvent;

import com.ltj.android.engine.MotionInput;
import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.GameObject;
import com.ltj.shared.engine.SheetSpriteModeS;
import com.ltj.shared.engine.SoundManager;

public class PlayerController extends Behaviour<SheetSpriteModeS> {

	
	private float xMovement,yMovement;
	private int direction;
	private float lastX,lastY;
	private int sound;
	@Override
	public void start() {
		gameObject.setTexture(0, 0);
		gameObject.scale(0.5f, 0.5f);
		sound = SoundManager.addShortClip("test.wav");
	}

	@Override
	public void update(){
		
		if (MotionInput.isActive()){
			setMovement(-(lastX - MotionInput.getX()),lastY - MotionInput.getY());
			
			changeDirection();
			if(MotionInput.getEvent().getAction() == MotionEvent.ACTION_DOWN){

				lastX = MotionInput.getX();
				lastY = MotionInput.getY();

			} 
		}
		
		gameObject.translate(xMovement, yMovement);

		
		Camera.setLookAt(gameObject.getX(), gameObject.getY());
	}
	


	public void onCollision(GameObject collider){
		if (collider.compareTag("enemy")){
			gameObject.translate(-xMovement, -yMovement);
			SoundManager.playShortClip(sound);
		}
	}
	private void changeDirection() {

		// yMovement is larger than xMovement
		if (Math.abs(yMovement) >= Math.abs(xMovement)) {
			if (yMovement >= 0) {
				// facing down
				setDirection(0);
			} else {
				// facing up
				setDirection(2);
			}
		} else {
			if (xMovement >= 0) {
				// facing right
				setDirection(3);
			} else {
				// facing left
				setDirection(1);
			}
		}
	}
	public void setDirection(int direction) {
		this.direction = direction;
		gameObject.setTexture(0, direction);
	}
	public int getDirection() {
		return direction;
	}
	
	public void setMovement(float dx, float dy){
		xMovement = dx*0.0002f;
		yMovement = dy*0.0002f;
	}

}
