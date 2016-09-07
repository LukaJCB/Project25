package com.ltj.shared.engine;

import com.ltj.android.engine.AndroidAudioManager;
import com.ltj.java.engine.JoglAudioManager;

public class SoundManager {
	private static AudioManagable manager;
	
	public static void initSoundManager(boolean isAndroid){
		if (isAndroid){
			manager = new AndroidAudioManager();
		} else {
			manager = new JoglAudioManager();
		}
	}

	public static void addSoundClip(String path) {
		manager.addSoundClip(path);
	}

	public static void playSoundClip() {
		manager.playSoundClip();
	}

	public static void setLooping(boolean looping) {
		manager.setLooping(looping);
	}

	public static int addShortClip(String path) {
		return manager.addShortClip(path);
	}

	public static void playShortClip(int id) {
		manager.playShortClip(id);
	}
	
}
