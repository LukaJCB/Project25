package com.ltj.android.engine;

import android.view.MotionEvent;

public class MotionInput {
	private MotionInput(){}
	private static MotionEvent EVENT;
	public static void setEvent(MotionEvent event){
		EVENT = event;
	}
	
	public static MotionEvent getEvent(){
		return EVENT;
	}

	public static float getX() {
		return EVENT.getX();
	}

	public static float getY() {
		return EVENT.getY();
	}
	
	public static boolean isActive(){
		return (EVENT != null && (EVENT.getPointerCount() > 2 || EVENT.getAction() != MotionEvent.ACTION_UP));
	}
}
