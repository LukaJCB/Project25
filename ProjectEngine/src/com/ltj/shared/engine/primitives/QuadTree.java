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
		float fourthWidth = subWidth/2;
		float fourthHeight = subHeight/2;
		float x = bounds.getX();
		float y = bounds.getY();
		
		nodes[0] = new QuadTree(level+1,new Rectangle(x+fourthWidth,y+fourthHeight,subWidth,subHeight));
		nodes[1] = new QuadTree(level+1, new Rectangle(x-fourthWidth,y+fourthHeight,subWidth,subHeight));
		nodes[2] = new QuadTree(level+1, new Rectangle(x-fourthWidth,y-fourthHeight,subWidth,subHeight));
		nodes[3] = new QuadTree(level+1, new Rectangle(x+fourthWidth,y-fourthHeight,subWidth,subHeight));
	}
	
	private int getIndex(RenderObject sprite){
		float verticalMidpoint = bounds.getX();
		float horizontalMidpoint = bounds.getY();
		
		boolean botQuadrant = (sprite.getY()+ sprite.getHeight()/2 < horizontalMidpoint);
		boolean topQuadrant = (sprite.getY()-sprite.getHeight()/2 > horizontalMidpoint);
		
		if (sprite.getX() +sprite.getWidth()/2 < verticalMidpoint){
			if(botQuadrant){
				return 2;
			} else if (topQuadrant){
				return 1;
			}
		} else if (sprite.getX()-sprite.getWidth()/2 > verticalMidpoint){
			if (botQuadrant){
				return 3;
			} else if (topQuadrant){
				return 0;
			}
		}
		return -1;
	}
	
	public void insert(RenderObject sprite){
		if (nodes[0] != null) {
			float verticalMidpoint = bounds.getX();
			float horizontalMidpoint = bounds.getY();
			
			boolean botQuadrant = (sprite.getY()+ sprite.getHeight()/2 < horizontalMidpoint);
			boolean topQuadrant = (sprite.getY()-sprite.getHeight()/2 > horizontalMidpoint);
			boolean topBot = (!botQuadrant && !topQuadrant);
			
			boolean leftQuadrant = (sprite.getX() +sprite.getWidth()/2 < verticalMidpoint);
			boolean rightQuadrant = (sprite.getX()-sprite.getWidth()/2 > verticalMidpoint);
			boolean rightLeft = (!rightQuadrant && !leftQuadrant);
			
			if (topBot){
				if (rightLeft){
					nodes[0].insert(sprite);
					nodes[1].insert(sprite);
					nodes[2].insert(sprite);
					nodes[3].insert(sprite);
				} else if (rightQuadrant){
					nodes[0].insert(sprite);
					nodes[3].insert(sprite);
				} else if (leftQuadrant) {
					nodes[1].insert(sprite);
					nodes[2].insert(sprite);
				}
			} else if (topQuadrant){
				if (rightLeft){
					nodes[0].insert(sprite);
					nodes[1].insert(sprite);
				} else if (rightQuadrant){
					nodes[0].insert(sprite);
				} else if (leftQuadrant) {
					nodes[1].insert(sprite);
				}
			} else if (botQuadrant) {
				if (rightLeft){
					nodes[2].insert(sprite);
					nodes[3].insert(sprite);
				} else if (rightQuadrant){
					nodes[3].insert(sprite);
				} else if (leftQuadrant){
					nodes[2].insert(sprite);
				}
			}
			

		} else {
			objects.add(sprite);
			if (objects.size() > MAX_OBJECTS && level < MAX_LEVELS) {
				split(); 
				
				for (int i = 0;i < objects.size();i++){
					insert(objects.remove(i));
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
