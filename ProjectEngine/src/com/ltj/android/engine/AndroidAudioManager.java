package com.ltj.android.engine;

import java.io.IOException;

import android.media.MediaPlayer;

public abstract class AndroidAudioManager {
	private static MediaPlayer player;

	private AndroidAudioManager(){}
	
	public static void addSoundClip(String path){
		if (player == null){
			player = new MediaPlayer();
		}
		try {
			player.setDataSource(path);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		player.prepareAsync();
	}
	
	public static void playSoundClip(){
		player.start();
	}
}
