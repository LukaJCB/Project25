package com.ltj.android.engine;

import java.io.IOException;

import android.media.MediaPlayer;

public class AndroidAudioManager implements MediaPlayer.OnPreparedListener {
	private static MediaPlayer player;
	private static boolean prepared;

	@Override
	public void onPrepared(MediaPlayer mp) {
		prepared = true;
		
	}

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
		player.setOnPreparedListener(new AndroidAudioManager());
	}
	
	public static void playSoundClip(){
		if(prepared)
		player.start();
	}
}
