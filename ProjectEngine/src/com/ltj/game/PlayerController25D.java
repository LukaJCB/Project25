package com.ltj.game;

import android.view.MotionEvent;

import com.ltj.android.engine.MotionInput;
import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.GameObject;
import com.ltj.shared.engine.SheetSpriteModeS;
public class PlayerController25D extends Behaviour<SheetSpriteModeS> {

	private float xMovement,yMovement;
	private float lastX,lastY;
	@Override
	public void start() {
		gameObject.setTexture(0, 0);
		gameObject.scale(0.5f, 0.5f);
	}

	@Override
	public void update(){
		
		if (MotionInput.isActive()){
			if (Math.abs(lastX - MotionInput.getX()) > Math.abs(lastY - MotionInput.getY())){
				gameObject.rotate((lastX - MotionInput.getX())*0.01f);
			} else {
				float speed = lastY - MotionInput.getY();
				setMovement(speed * (float)-Math.sin(Math.toRadians(gameObject.getRotation())), 
						speed * (float)Math.cos(Math.toRadians(gameObject.getRotation())));
			}
			
			if(MotionInput.getEvent().getAction() == MotionEvent.ACTION_DOWN){

				lastX = MotionInput.getX();
				lastY = MotionInput.getY();

			}
		}
		
		gameObject.translate(xMovement, yMovement);

		
		Camera.setRotateAround(gameObject.getX(), gameObject.getY(),gameObject.getRotation());
	}
	@Override
	public void onCollision(GameObject collider){
		if (collider.compareTag("enemy"))
		gameObject.translate(-xMovement, -yMovement);
	}

	
	public void setMovement(float dx, float dy){
		xMovement = dx*0.0002f;
		yMovement = dy*0.0002f;
	}
}
