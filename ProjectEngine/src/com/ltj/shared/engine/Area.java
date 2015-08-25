package com.ltj.shared.engine;

import java.util.ArrayList;

import com.ltj.shared.engine.primitives.Rectangle;

public class Area {

	private EmptyObject root;
	public Area(float width,float height){
		root = new EmptyObject();
		root.scale(width, height);
	}
	public void translate(float dx, float dy) {
		root.translate(dx, dy);
	}
	public void setPosition(float x, float y) {
		root.setPosition(x, y);
	}
	public void scale(float sx, float sy) {
		root.scale(sx, sy);
	}
	public float getX() {
		return root.getX();
	}
	public float getY() {
		return root.getY();
	}
	public float getHeight() {
		return root.getHeight();
	}
	public float getWidth() {
		return root.getWidth();
	}
	
	public boolean isInArea(Sprite gm){
		return (root.getY() - root.getHeight()/2 < gm.getY() + gm.getHeight()/2 &&
				root.getY() + root.getHeight()/2 > gm.getY() - gm.getHeight()/2 &&
				root.getX() + root.getWidth()/2 > gm.getX() - gm.getWidth()/2  &&
				root.getX() - root.getWidth()/2 < gm.getX() + gm.getWidth()/2);
		
	}
	
	public boolean isInArea(float x, float y){
		return (root.getY() - root.getHeight()/2 < y &&
				root.getY() + root.getHeight()/2 > y &&
				root.getX() + root.getWidth()/2 > x &&
				root.getX() - root.getWidth()/2 < x);
	}
	
	public void setCollisionZone(){
		Engine.setCollisionZone(new Rectangle(root.getX(), root.getY(), root.getWidth()*3.1f, root.getHeight()*3.1f));
	}
	
	public void addObject(Sprite child){
		child.translate(getX(), getY());
		root.addChild(child);
	}
	
	public void addObjectWorldSpace(Sprite child){
		root.addChild(child);
	}
	
	public void destroy(){
		if (root.getChildList() != null){
			for (Sprite g : root.getChildList()){
				g.destroy();
			}
		}
	}
	
	public void setInactive(boolean inactive){
		if (root.getChildList() != null){
			for (Sprite g : root.getChildList()){
				g.setInactive(true);	
			}
		}
	}
	
	public String toJSON(){
		String json = "[";
		for (Sprite r:root.getChildList()){
			json += r.getId() + ",";
		}
		json = json.substring(0, json.length() - 1);
		json += "]";
		return json;
		
	}
	public ArrayList<Sprite> getObjectList() {
		return root.getChildList();
	}
	
	
	
	
	
	
}
