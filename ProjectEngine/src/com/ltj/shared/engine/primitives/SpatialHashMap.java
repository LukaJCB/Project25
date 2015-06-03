package com.ltj.shared.engine.primitives;

import java.util.ArrayList;
import java.util.List;

import android.util.SparseArray;

import com.ltj.shared.engine.RenderObject;

public class SpatialHashMap {

	private SparseArray<List<RenderObject>> map;
	public SpatialHashMap(int cols, int rows){
		map = new SparseArray<List<RenderObject>>(cols * rows);
		for (int i = 0;i < cols * rows;i++){
			map.append(i, new ArrayList<RenderObject>());
		}
	}
	public void insert(RenderObject sprite){
		List<RenderObject> cellIds = getId(sprite);
	}
	
	private List<RenderObject> getId(RenderObject sprite){
		return null;
		
	}
	
	public void collideAll(){
		
	}
}
