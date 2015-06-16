package com.ltj.shared.engine.primitives;

import java.util.ArrayList;
import java.util.List;


import com.ltj.shared.engine.Collider;
import com.ltj.shared.engine.EmptyObject;
import com.ltj.shared.engine.RenderObject;

public class SpatialHashMap {

	private ArrayList<RenderObject>[] map;
	private int columns;
	private float cellHeight, cellWidth;
	private float gridX, gridY;
	
	
	@SuppressWarnings("unchecked")
	public SpatialHashMap(int cols, int rows, float width, float height, float gridX,float gridY){
		this.columns = cols;
		this.gridX = gridX - width/2;
		this.gridY = gridY - height/2;
		cellHeight = height/rows;
		cellWidth = width/cols;
		map = new ArrayList[cols*rows];
		for (int i = 0;i < cols * rows;i++){
			map[i] = new ArrayList<RenderObject>();
		}
	}
	public void insert(RenderObject sprite){
		if (sprite.getColliders() != null){
			for (Collider c : sprite.getColliders()){
				int bottom = (int) ((c.getBottom(sprite.getY(), sprite.getHeight())-gridY)/cellHeight);
				int top = (int) ((c.getTop(sprite.getY(), sprite.getHeight())-gridY)/cellHeight);
				boolean topBot = (top == bottom); 

				int left = (int) ((c.getLeft(sprite.getX(), sprite.getWidth())-gridX)/cellWidth);
				int right = (int) ((c.getRight(sprite.getX(), sprite.getWidth())-gridX)/cellWidth);	
				boolean rightLeft = (right == left); 

				if (rightLeft){
					if (topBot){
						map[getIdInMap(left,bottom)].add(sprite);
					} else {
						map[getIdInMap(left, bottom)].add(sprite);
						map[getIdInMap(left, top)].add(sprite);
					}
				} else {
					if (topBot){
						map[getIdInMap(left,bottom)].add(sprite);
						map[getIdInMap(right, bottom)].add(sprite);
					} else {
						map[getIdInMap(left, bottom)].add(sprite);
						map[getIdInMap(right, bottom)].add(sprite);
						map[getIdInMap(left, top)].add(sprite);
						map[getIdInMap(right, top)].add(sprite);
					}
				}
			}
		}
	}
	
	
	private int getIdInMap(int x, int y){
		return y * columns + x;
	}
	
	public void collideAll(){
		for (ArrayList<RenderObject> allObjects : map){
			for (int i = 0;i < allObjects.size(); i++){
				for (int j = i+1; j < allObjects.size(); j++){
					allObjects.get(i).checkCollision(allObjects.get(j));
				}
			}
		}
	}
	
	public void clear() {
		for (ArrayList<RenderObject> allObjects : map){
			allObjects.clear();
		}
		
	}
}
