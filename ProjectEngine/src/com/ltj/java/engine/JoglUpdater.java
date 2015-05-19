package com.ltj.java.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.Updater;

public class JoglUpdater extends Updater implements KeyListener {

	private boolean keyInput;

	public JoglUpdater(boolean key){
		super();
		keyInput = key;
	}
	
	public void keyTyped(KeyEvent e) {
		
		
	}
	

	public void keyPressed(KeyEvent e) {
		if (keyInput){
			KeyInput.setEvent(e);
		}
		if (e.getKeyCode() == KeyEvent.VK_UP){
			Camera.setModeSeven();
		}
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
