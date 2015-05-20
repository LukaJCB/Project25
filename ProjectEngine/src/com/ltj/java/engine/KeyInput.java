package com.ltj.java.engine;

import java.awt.event.KeyEvent;

public class KeyInput {

	private static KeyEvent event;

	public static void setEvent(KeyEvent e) {
		event = e;
		
	}
	
	public static KeyEvent getEvent(){
		return event;
	}
	
}
