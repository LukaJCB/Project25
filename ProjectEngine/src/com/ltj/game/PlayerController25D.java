package com.ltj.game;

import android.view.MotionEvent;

import com.ltj.engine.Behaviour;
import com.ltj.engine.MotionInput;
import com.ltj.engine.android.SheetSpriteModeSAndroid;
import com.ltj.engine.primitives.Camera;
import com.ltj.engine.primitives.RenderObject;

public class PlayerController25D extends Behaviour<SheetSpriteModeSAndroid> {

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
	
	public void onCollision(RenderObject collider){
		if (collider.compareTag("Enemy"));
		gameObject.translate(-xMovement, -yMovement);
	}

	
	public void setMovement(float dx, float dy){
		xMovement = dx*0.0002f;
		yMovement = dy*0.0002f;
	}
}
