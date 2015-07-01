package com.ltj.shared.engine.primitives;


import com.ltj.shared.engine.RenderObject;
import com.ltj.shared.engine.Updater;

public class ObjectPool {
	private RenderObject[] pooledObjects;
	
	public ObjectPool(int count, RenderObject obj){
		pooledObjects = new RenderObject[count];
		for (int i = 0; i < pooledObjects.length; i++){
			pooledObjects[i] = obj.cloneObject();
			pooledObjects[i].setInactive(true);
			Updater.addRenderable((RenderObject) pooledObjects[i]);
		}
	}
	
	public RenderObject create() throws PoolToSmallException{
		for (RenderObject go : pooledObjects){
			if (go.isInactive()){
				go.start();
				return go;
			}
		}
		throw new PoolToSmallException();
	}
	
}
