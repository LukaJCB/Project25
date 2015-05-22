package com.ltj.shared.engine;


import java.util.ArrayList;


public interface RenderObject extends GameObject {
	void start();
	void update();
	void render();
	void addBehaviour(Behaviour<? extends GameObject> b);
	void addCollider(Collider c);
	Collider getCollider(int index);
	ArrayList<Collider> getColliders();
	void addChild(RenderObject child);
	void onCollisionEnter(GameObject collider);
	void onCollision(GameObject collider);
	void onCollisionExit(GameObject collider);
	void onChildCollisionEnter(GameObject child, GameObject collider);
	void onChildCollision(GameObject child, GameObject collider);
	void onChildCollisionExit(GameObject child, GameObject collider);
	void clear();
	
	
	
}
