package com.ltj.shared.engine.primitives;

public abstract class Animation {

	public abstract void onStart();
	public abstract void animate();
	public abstract int framesLeft();
}
