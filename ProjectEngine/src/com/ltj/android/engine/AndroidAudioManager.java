package com.ltj.android.engine;

import java.io.IOException;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class AndroidAudioManager implements MediaPlayer.OnPreparedListener {
	private static MediaPlayer player;
	private static boolean prepared;
	@SuppressWarnings("deprecation")
	private static SoundPool pool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);

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
	
	public static void setLooping(boolean looping){
		player.setLooping(looping);
	}
	
	public static void addShortClip(String path,int id){
		pool.load(path, id);
	}
	
	public static void playShortClip(int id){
		pool.play(id,1,1,1,0,1);
	}
}
