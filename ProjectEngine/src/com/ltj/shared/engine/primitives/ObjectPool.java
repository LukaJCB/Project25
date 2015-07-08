package com.ltj.shared.engine.primitives;


import com.ltj.shared.engine.RenderObject;
import com.ltj.shared.engine.Sprite;
import com.ltj.shared.engine.Updater;

public class ObjectPool {
	private RenderObject[] pooledObjects;
	
	public ObjectPool(int count, RenderObject obj){
		pooledObjects = new RenderObject[count];
		obj.setInactive(true);
		Updater.addRenderable(obj);
		pooledObjects[0] = obj;
		for (int i = 1; i < pooledObjects.length; i++){
			pooledObjects[i] = obj.cloneObject();
			pooledObjects[i].setInactive(true);
		}
	}
	
	public Sprite create() throws PoolToSmallException{
		for (RenderObject go : pooledObjects){
			if (go.isInactive()){
				go.start();
				return go;
			}
		}
		throw new PoolToSmallException();
	}
	
}
