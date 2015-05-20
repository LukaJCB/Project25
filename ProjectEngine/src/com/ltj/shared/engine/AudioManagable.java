package com.ltj.shared.engine;

public interface AudioManagable {
	void addSoundClip(String path);
	void playSoundClip();
	void setLooping(boolean looping);
	int addShortClip(String path);
	void playShortClip(int id);
}
