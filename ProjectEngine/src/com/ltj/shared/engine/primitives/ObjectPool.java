package com.ltj.shared.engine.primitives;


import com.ltj.shared.engine.GameObject;
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
	
	public GameObject create() throws PoolToSmallException{
		for (GameObject go : pooledObjects){
			if (go.isInactive()){
				return go;
			}
		}
		throw new PoolToSmallException();
	}
	
}
