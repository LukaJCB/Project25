package com.ltj.shared.engine;





public class EmptyObject extends AbstractSprite {

	public EmptyObject(){
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
	
	public String toJSON(){
		return "{ \"type\":\"" + getClass().getSimpleName() + "\", "+ super.toJSON() + "}";
	}
	
	

	
}
