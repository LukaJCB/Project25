package com.ltj.engine;

import android.hardware.SensorEvent;

public class TiltInput {
	
	private static SensorEvent EVENT;

	public static SensorEvent getEvent() {
		return EVENT;
	}

	public static void setEvent(SensorEvent event) {
		EVENT = event;
	}
	
	public static float getX(){
		return EVENT.values[0];
	}
	public static float getY(){
		return EVENT.values[1];
	}
	public static float getZ(){
		return EVENT.values[2];
	}
	
	public static boolean isActive(){
		return EVENT != null ;
	}

	public static void setInactive() {
		EVENT = null;
	}
	
	
	
}
