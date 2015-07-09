package com.ltj.java.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.ltj.shared.engine.Engine;

public class JoglUpdater extends Engine implements KeyListener {

	private boolean keyInput;

	public JoglUpdater(boolean key){
		super();
		keyInput = key;
	}
	
	public void keyTyped(KeyEvent e) {
	}
	

	public void keyPressed(KeyEvent e) {
		if (keyInput){
			Engine.onKeyInput(e);
		}
	}

	public void keyReleased(KeyEvent e) {
		if (keyInput){
			Engine.onKeyReleased(e);
		}
	}

}
