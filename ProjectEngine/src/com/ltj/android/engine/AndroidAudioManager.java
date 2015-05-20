package com.ltj.android.engine;

import java.io.FileDescriptor;
import java.io.IOException;

import javax.activation.FileDataSource;

import com.ltj.shared.engine.AudioManagable;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class AndroidAudioManager implements AudioManagable, MediaPlayer.OnPreparedListener  {
	private static MediaPlayer player;
	private static boolean prepared;
	@SuppressWarnings("deprecation")
	private static SoundPool pool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);

	@Override
	public void onPrepared(MediaPlayer mp) {
		prepared = true;
		
	}

	
	public void addSoundClip(String path){
		if (player == null){
			player = new MediaPlayer();
		}
		try {
			//FIXME
			FileDescriptor fd = new FileDescriptor();
			player.setDataSource(fd);
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
	
	public void playSoundClip(){
		if(prepared){
			player.start();
		}
	}
	
	public void setLooping(boolean looping){
		player.setLooping(looping);
	}
	
	public int addShortClip(String path){
		return pool.load(path, 1);
	}
	
	public void playShortClip(int id){
		pool.play(id,1,1,1,0,1);
	}
}
