package com.ltj.shared.engine;





public class EmptyObject extends AbstractSprite {

	public EmptyObject(){
		super(null);
		this.renderer = new EmptyRenderer();
	}

	@Override
	public RenderObject cloneObject() {
		EmptyObject o = new EmptyObject();
		finishClone(o);
		return o;
	}

	@Override
	public boolean isLoaded() {
		return true;
	}

	@Override
	public void finishLoading() {
	
		
	}
	
	
	

	
}
