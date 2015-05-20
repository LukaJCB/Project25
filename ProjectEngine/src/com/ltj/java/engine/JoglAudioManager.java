package com.ltj.java.engine;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.ltj.shared.engine.AudioManagable;

public class JoglAudioManager implements AudioManagable{

	private Clip[] clips = new Clip[16];
	private int index = 1;
	
	@Override
	public void addSoundClip(String path) {
		addClip(path,0);
	}

	@Override
	public void playSoundClip() {
		clips[0].start();
	}

	@Override
	public void setLooping(boolean looping) {
		if (looping){
			clips[0].loop(Clip.LOOP_CONTINUOUSLY);
		} else {
			clips[0].loop(0);
		}
		
	}

	@Override
	public int addShortClip(String path) {
		return addClip(path,index);
	}

	@Override
	public void playShortClip(int id) {
		clips[id].start();
		
	}

	private int addClip(String path, int id){
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
			clip.open(inputStream);
			clips[id] = clip;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
}
