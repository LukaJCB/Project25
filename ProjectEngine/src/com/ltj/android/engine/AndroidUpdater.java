package com.ltj.android.engine;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.ltj.shared.engine.Engine;

public class AndroidUpdater extends Engine implements SensorEventListener,OnTouchListener  {


	private boolean motionInput,tiltInput;
	
	public AndroidUpdater(Context c, boolean motion, boolean tilt){
		super();
		tiltInput = tilt;
		motionInput = motion;
		
		if(tiltInput){
			// Set up the accelerometer
			SensorManager sensorManager = (SensorManager) c.getSystemService(Context.SENSOR_SERVICE);
			Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
			//Register Sensor:
			sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
		}
	}
	
	

	@Override
	public boolean onTouch(View v,MotionEvent event) {
		if (motionInput){
			MotionInput.setEvent(event);
			return true;
		}
		return false;
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (tiltInput){
			TiltInput.setEvent(event);
		} else {
			TiltInput.setInactive();
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {}



}
