package com.ltj.shared.engine.primitives;

import java.util.ArrayList;

import com.ltj.shared.engine.Collider;
import com.ltj.shared.engine.RenderObject;

public class SpatialHashMap {

	private ArrayList<RenderObject>[][] map;
	private float cellHeight, cellWidth;
	private float gridX, gridY;
	
	
	@SuppressWarnings("unchecked")
	public SpatialHashMap(int cols, int rows, float width, float height, float gridX,float gridY){
		this.gridX = gridX - width/2;
		this.gridY = gridY - height/2;
		cellHeight = height/rows;
		cellWidth = width/cols;
		map = new ArrayList[cols][rows];
		for (int i = 0;i < cols;i++){
			for (int j = 0; j < rows;j++){
				map[i][j] = new ArrayList<RenderObject>();
			}
		}
	}
	public void insert(RenderObject sprite){
		if (!sprite.isControllerDisabled() && sprite.getColliders() != null){
			for (Collider c : sprite.getColliders()){
				int bottom = clampY((int) ((c.getBottom(sprite.getY(), sprite.getHeight())-gridY)/cellHeight));
				int top = clampY((int) ((c.getTop(sprite.getY(), sprite.getHeight())-gridY)/cellHeight));
				boolean topBot = (top == bottom); 

				int left = clampX((int) ((c.getLeft(sprite.getX(), sprite.getWidth())-gridX)/cellWidth));
				int right = clampX((int) ((c.getRight(sprite.getX(), sprite.getWidth())-gridX)/cellWidth));	
				
				boolean rightLeft = (right == left); 

				if (rightLeft){
					if (topBot){
						map[left][bottom].add(sprite);
					} else {
						for (int i = bottom; i <= top;i++){
							map[left][i].add(sprite);
						}
					}
				} else {
					if (topBot){
						for (int i = left; i <= right;i++){
							map[i][bottom].add(sprite);
						}
					} else {
						for (int i = left; i <= right;i++){
							for (int j = bottom; j <= top;j++){
								map[i][j].add(sprite);
							}
						}
					}
				}
			}
		}
	}
	
	
	
	private int clampX(int value){
		if (value < 0){
			return 0;
		}
		if (value >= map.length){
			return map.length-1;
		}
		return value;
	}
	
	private int clampY(int value){
		if (value < 0){
			return 0;
		}
		if (value >= map[0].length){
			return map[0].length-1;
		}
		return value;
	}
	
	public void collideAll(){
		for (ArrayList<RenderObject>[] allArrays : map){
			for(ArrayList<RenderObject> allObjects : allArrays){
				for (int i = 0;i < allObjects.size(); i++){
					for (int j = i+1; j < allObjects.size(); j++){
						allObjects.get(i).checkCollision(allObjects.get(j));
					}
				}
			}
		}
	}
	
	public void clear() {
		for (ArrayList<RenderObject>[] allArrays : map){
			for(ArrayList<RenderObject> allObjects : allArrays){
				allObjects.clear();
			}
		}
		
	}
}
