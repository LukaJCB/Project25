package com.ltj.shared.engine;

public class Line {

	private float[] point;
	private float[] direction;
	
	public Line(float[] point1	, float[] point2) {
		point = new float[3];
		point[0] = point1[0];
		point[1] = point1[1];
		point[2] = point1[2];
		
		direction = new float[3];
		direction[0] = point2[0] - point1[0];
		direction[1] = point2[1] - point1[1];
		direction[2] = point2[2] - point1[2];
		
	}
	
	public boolean intersectsWith(RenderObject o){
		if (Engine.isModeSeven()){
			if (o.isModeSevenEnabled()){
				//TODO
			} else {
				//TODO
			}
		} else {
			//2d Mode is enabled
			return (point[1] < o.getY() + o.getHeight()/2 &&
					point[1] > o.getY() - o.getHeight()/2 &&
					point[0] < o.getX() + o.getWidth()/2 &&
					point[0] > o.getX() - o.getHeight()/2);
				
			
		}
		return false;
	}
	
}
