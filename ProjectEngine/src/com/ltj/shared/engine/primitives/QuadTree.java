package com.ltj.shared.engine.primitives;

import java.util.ArrayList;
import java.util.List;

import com.ltj.shared.engine.RenderObject;


public class QuadTree {
	private final int MAX_OBJECTS = 10;
	private final int MAX_LEVELS = 5;
	
	private int level;
	private List<RenderObject> objects;
	private Rectangle bounds;
	private QuadTree[] nodes;
	
	public QuadTree(int pLevel, Rectangle pBounds){
		this.level = pLevel;
		this.bounds = pBounds;
		objects = new ArrayList<RenderObject>(MAX_OBJECTS+1);
		nodes = new QuadTree[4];
	}
	
	public void clear(){
		objects.clear();
		for (int i = 0; i < nodes.length; i++){
			if(nodes[i] != null){
				nodes[i].clear();
				nodes[i] = null;
			}
		}
	}
	
	private void split(){
		float subWidth = bounds.getWidth()/2;
		float subHeight = bounds.getHeight()/2;
		float x = bounds.getX();
		float y = bounds.getY();
		
		nodes[0] = new QuadTree(level+1,new Rectangle(x+subWidth,y,subWidth,subHeight));
		nodes[1] = new QuadTree(level+1, new Rectangle(x,y,subWidth,subHeight));
		nodes[2] = new QuadTree(level+1, new Rectangle(x,y+subHeight,subWidth,subHeight));
		nodes[3] = new QuadTree(level+1, new Rectangle(x+subWidth,y+subHeight,subWidth,subHeight));
	}
	
	private int getIndex(RenderObject sprite){
		float verticalMidpoint = bounds.getX() +bounds.getWidth()/2;
		float horizontalMidpoint = bounds.getY() + bounds.getHeight()/2;
		
		boolean topQuadrant = (sprite.getY() < horizontalMidpoint &&sprite.getY()+ sprite.getHeight() < horizontalMidpoint);
		boolean bottomQuadrant = (sprite.getY() > horizontalMidpoint);
		
		if (sprite.getX() < verticalMidpoint && sprite.getX() +sprite.getWidth() < verticalMidpoint){
			if(topQuadrant){
				return 1;
			} else if (bottomQuadrant){
				return 2;
			}
		} else if (sprite.getX() > verticalMidpoint){
			if (topQuadrant){
				return 0;
			} else if (bottomQuadrant){
				return 3;
			}
		}
		return -1;
	}
	
	public void insert(RenderObject sprite){
		if (nodes[0] != null) {
			int index = getIndex(sprite);

			if (index != -1) {
				nodes[index].insert(sprite);

				return;
			}
		}

		objects.add(sprite);

		if (objects.size() > MAX_OBJECTS && level < MAX_LEVELS) {
			if (nodes[0] == null) { 
				split(); 
			}

			int i = 0;
			while (i < objects.size()) {
				int index = getIndex(objects.get(i));
				if (index != -1) {
					nodes[index].insert(objects.remove(i));
				}
				else {
					i++;
				}
			}
		}
	}
	
	@Deprecated
	public List<RenderObject> retrieve(List<RenderObject> returnObjects, RenderObject sprite){
		int index = getIndex(sprite);
		if (index != -1 && nodes[0] != null){
			nodes[index].retrieve(returnObjects, sprite);
		}
		returnObjects.addAll(objects);
		
		return returnObjects;
	}
	
	public void collideAll(){
		if (nodes[0] != null){
			nodes[0].collideAll();
			nodes[1].collideAll();
			nodes[2].collideAll();
			nodes[3].collideAll();
		} else {
			for (int i = 0;i < objects.size(); i++){
				for (int j = i+1; j < objects.size(); j++){
					objects.get(i).checkCollision(objects.get(j));
				}
			}
		}
	}
}
