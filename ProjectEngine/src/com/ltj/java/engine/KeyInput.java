package com.ltj.java.engine;

import java.awt.event.KeyEvent;

public class KeyInput {

	private static KeyEvent[] events = new KeyEvent[5];

	public static void setEvent(KeyEvent e) {
		for (int i = 0;i < events.length;i++){
			if (events[i] == null){
				events[i] = e;
				return;
			}
		}
		
	}
	
	public static boolean compareEvent(int keycode){
		for (int i = 0;i < events.length;i++){
			if (events[i] != null)System.out.println(events[i].getKeyCode()+ " " + i);
			if (events[i] != null  && events[i].getKeyCode() == keycode){
				return true;
			}
		}
		return false;
	}
	
	public static void releaseEvent(KeyEvent e){
		for (int i = 0;i < events.length;i++){
			if (events[i].getKeyCode() == e.getKeyCode()){
				System.out.println("release " + i);
				events[i] = null;
				return;
			}
		}
	}
}
