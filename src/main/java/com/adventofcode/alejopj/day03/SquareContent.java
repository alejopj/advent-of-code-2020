package com.adventofcode.alejopj.day03;

import com.google.common.collect.ImmutableSet;

public enum SquareContent {
	OPEN("."),
	TREE("#");
	
	private final String content;
	
	private SquareContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	public static SquareContent of(String content) {
		
		return ImmutableSet.copyOf(SquareContent.values()).stream()
				.filter(squareContent -> squareContent.getContent().equals(content)).findFirst().orElse(null);
	}
}
