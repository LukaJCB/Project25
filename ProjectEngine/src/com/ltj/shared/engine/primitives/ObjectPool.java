package com.ltj.shared.engine.primitives;

import com.ltj.shared.engine.Engine;
import com.ltj.shared.engine.RenderObject;
import com.ltj.shared.engine.Sprite;

public class ObjectPool {
	private RenderObject[] pooledObjects;
	private int count,id;
	
	public ObjectPool(int count, RenderObject obj){
		this.id = obj.getId();
		this.count = count;
		pooledObjects = new RenderObject[count];
		obj.setInactive(true);
		pooledObjects[0] = obj;
		for (int i = 1; i < pooledObjects.length; i++){
			pooledObjects[i] = obj.cloneObject();
			pooledObjects[i].setInactive(true);
		}
	}
	
	public ObjectPool(int id, int count){
		this.id = id;
		pooledObjects = new RenderObject[count];
		for (int i = 0; i < pooledObjects.length; i++){
			pooledObjects[i] = Engine.getAllObjects().get(id + i);
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

	public int getCount() {
		return count;
	}

	public int getId() {
		return id;
	}
	
	public String toJSON(){
		return "\"id\":" + id + ",\"count\":" + count;
	}

	
}
