package com.adventofcode.alejopj.day03;

public enum SlopeDirection {
	TOP(-1),
	RIGHT(1),
	BOTTOM(1),
	LEFT(-1);
	
	private final Integer direction;
	
	private SlopeDirection(Integer direction) {
		this.direction = direction;
	}
	
	public Integer getDirection() {
		return direction;
	}
}
