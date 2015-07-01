package com.ltj.shared.engine;




public class EmptyObject extends AbstractSprite {

	public EmptyObject(){
		this.renderer = new EmptyRenderer();
	}

	@Override
	public RenderObject cloneObject() {
		EmptyObject o = new EmptyObject();
		prepareClone(o);
		return o;
	}

	
}
