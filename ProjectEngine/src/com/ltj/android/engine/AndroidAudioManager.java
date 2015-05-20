package com.ltj.android.engine;

import java.io.IOException;


import com.ltj.shared.engine.AudioManagable;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class AndroidAudioManager implements AudioManagable, MediaPlayer.OnPreparedListener  {
	private MediaPlayer player;
	private boolean prepared;
	private AssetManager assets = AndroidRenderer.context.getAssets();
	@SuppressWarnings("deprecation")
	private SoundPool pool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);

	@Override
	public void onPrepared(MediaPlayer mp) {
		prepared = true;
		
	}

	
	public void addSoundClip(String path){
		if (player == null){
			player = new MediaPlayer();
		}
		try {
			AssetFileDescriptor fd = assets.openFd(path);
			player.setDataSource(fd.getFileDescriptor(),fd.getStartOffset(),fd.getLength());
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
		try {
			return pool.load(assets.openFd(path), 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public void playShortClip(int id){
		pool.play(id,1,1,1,0,1);
	}
}
