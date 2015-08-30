package com.ltj.shared.engine;

public class Line {

	private float[] pointFrom;
	private float[] pointTo;
	private float[] intersectionPoint;
	
	public Line(float[] point1	, float[] point2) {
		pointFrom = new float[3];

		pointFrom[0] = point1[0];
		pointFrom[1] = point1[1];
		pointFrom[2] = point1[2];
		
		pointTo = new float[3];

		pointTo[0] = point2[0];
		pointTo[1] = point2[1];
		pointTo[2] = point2[2];
		

		intersectionPoint = new float[2];
		
	}
	
	public boolean intersectsWith(RenderObject o){
		
		if (o.isModeSevenEnabled()){
			//TODO
			intersectXYPlane();
			return (intersectionPoint[1] < o.getY() + o.getHeight()/2 &&
					intersectionPoint[1] > o.getY() - o.getHeight()/2 &&
					intersectionPoint[0] < o.getX() + o.getWidth()/2 &&
					intersectionPoint[0] > o.getX() - o.getWidth()/2);
		} else {
			intersectXYPlane();
			return (intersectionPoint[1] < o.getY() + o.getHeight()/2 &&
					intersectionPoint[1] > o.getY() - o.getHeight()/2 &&
					intersectionPoint[0] < o.getX() + o.getWidth()/2 &&
					intersectionPoint[0] > o.getX() - o.getWidth()/2);
		}

	}

	
	
	public void intersectXYPlane(){
		
		double r = - pointFrom[2] / pointTo[2];
		
		intersectionPoint[0] = (float) ((r* pointTo[0] + pointFrom[0])/(r+1));
		intersectionPoint[1] = (float) ((r* pointTo[1] + pointFrom[1])/(r+1));
		
	}
	
	public float[] getIntersection(){
		return intersectionPoint;
	}
}
