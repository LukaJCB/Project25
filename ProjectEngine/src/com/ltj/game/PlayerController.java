package com.ltj.game;

import android.view.MotionEvent;

import com.ltj.android.engine.MotionInput;
import com.ltj.android.engine.SheetSpriteModeSAndroid;
import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.RenderObject;

public class PlayerController extends Behaviour<SheetSpriteModeSAndroid> {

	
	private float xMovement,yMovement;
	private int direction;
	private float lastX,lastY;
	@Override
	public void start() {
		gameObject.setTexture(0, 0);
		gameObject.scale(0.5f, 0.5f);
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
	
	public void onCollision(RenderObject collider){
		if (collider.compareTag("Enemy"));
		gameObject.translate(-xMovement, -yMovement);
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
