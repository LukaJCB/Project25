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
	void onCollision(GameObject collider);
	void onChildCollision(GameObject child, GameObject collider);
	void addChild(RenderObject child);
	
	
}
