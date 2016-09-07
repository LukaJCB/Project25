package com.ltj.shared.engine.primitives;

public class Position {
	private int x,y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		return result;
	}

	@Override
	public boolean equals(Object o) {
		Position key = (Position) o;
		return x == key.x && y == key.y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}



}
