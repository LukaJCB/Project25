package com.ltj.game;

import android.view.MotionEvent;

import com.ltj.android.engine.AndroidParticleEmitter;
import com.ltj.android.engine.MotionInput;
import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.Sprite;
import com.ltj.shared.engine.Updater;
import com.ltj.shared.engine.primitives.Globals;
public class PlayerController25D extends Behaviour<Sprite> {

	private float xMovement,yMovement;
	private float lastX,lastY;
	private boolean moving;
	private AndroidParticleEmitter pe;
	@Override
	public void start() {
		gameObject.scale(0.5f, 0.5f);
		gameObject.translate(0, -2);
		pe = new AndroidParticleEmitter(100,100, 1.0f,1f,1);
		Updater.addParticleEmitter(pe);
		moving = true;
	}

	@Override
	public void update(){
		
		if (MotionInput.isActive()){
			
			gameObject.rotate((lastX - MotionInput.getX())*0.01f);
			Globals.add("rotation", gameObject.getRotation());

			float speed = lastY - MotionInput.getY();
			setMovement(speed * (float)-Math.sin(Math.toRadians(gameObject.getRotation())), 
					speed * (float)Math.cos(Math.toRadians(gameObject.getRotation())));


			if(MotionInput.getEvent().getAction() == MotionEvent.ACTION_DOWN){

				lastX = MotionInput.getX();
				lastY = MotionInput.getY();

			}
		}
		if (moving){
			gameObject.translate(xMovement, yMovement);
		}
		
		Camera.setRotateAround(gameObject.getX(), gameObject.getY(),gameObject.getRotation());
	}
	@Override
	public void onCollision(Sprite c){
		gameObject.translate(-xMovement, -yMovement);
		moving = false;
		gameObject.setRendererDisabled(true);
		pe.addParticleExplosion(60, 0.007f);
	}

	
	public void setMovement(float dx, float dy){
		xMovement = dx*0.0002f;
		yMovement = dy*0.0002f;
	}
}
